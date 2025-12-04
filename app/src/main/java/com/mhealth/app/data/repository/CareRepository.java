package com.mhealth.app.data.repository;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.mhealth.app.data.local.dao.CareDao;
import com.mhealth.app.data.local.entity.AppointmentEntity;
import com.mhealth.app.data.local.entity.AuditEntryEntity;
import com.mhealth.app.data.local.entity.MedicalResultEntity;
import com.mhealth.app.data.local.entity.MedicationEntity;
import com.mhealth.app.data.local.entity.MessageEntity;
import com.mhealth.app.data.local.entity.NotificationEntity;
import com.mhealth.app.data.local.entity.PatientProfileEntity;
import com.mhealth.app.data.local.entity.SecretaryEscalationEntity;
import com.mhealth.app.data.local.entity.SystemAlertEntity;
import com.mhealth.app.data.local.entity.UserEntity;
import com.mhealth.app.data.model.Appointment;
import com.mhealth.app.data.model.MedicalResult;
import com.mhealth.app.data.model.Medication;
import com.mhealth.app.data.model.Message;
import com.mhealth.app.data.model.PatientProfile;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.model.UserRole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class CareRepository {

    private static final String SCOPE_PATIENT = "PATIENT";
    private static final String SCOPE_DOCTOR_INBOX = "DOCTOR_INBOX";

    private final CareDao careDao;
    private final ExecutorService ioExecutor;

    public CareRepository(CareDao careDao, ExecutorService ioExecutor) {
        this.careDao = careDao;
        this.ioExecutor = ioExecutor;
    }

    // region Patient
    public LiveData<PatientProfile> getPatientProfile(String patientId) {
        return Transformations.map(careDao.observePatientProfile(patientId), entity -> entity != null ? entity.toModel() : null);
    }

    public LiveData<List<Appointment>> getAppointmentsForPatient(String patientId) {
        return Transformations.map(careDao.observeAppointmentsForPatient(patientId), this::mapAppointments);
    }

    public LiveData<List<Medication>> getMedicationsForPatient(String patientId) {
        return Transformations.map(careDao.observeMedicationsForPatient(patientId), this::mapMedications);
    }

    public LiveData<List<Message>> getMessagesForUser(String patientId) {
        return Transformations.map(careDao.observeMessages(patientId, SCOPE_PATIENT), this::mapMessages);
    }

    public LiveData<List<String>> getNotificationsForPatient(String patientId) {
        return Transformations.map(careDao.observeNotifications(patientId), this::mapNotificationStrings);
    }
    // endregion

    // region Doctor
    public LiveData<List<PatientProfile>> getPatientsForDoctor(String doctorId) {
        return Transformations.map(careDao.observePatientsForDoctor(doctorId), this::mapPatientProfiles);
    }

    public LiveData<List<Appointment>> getAppointmentsForDoctor(String doctorId) {
        return Transformations.map(careDao.observeAppointmentsForDoctor(doctorId), this::mapAppointments);
    }

    public LiveData<List<Message>> getDoctorInbox(String doctorId) {
        return Transformations.map(careDao.observeMessages(doctorId, SCOPE_DOCTOR_INBOX), this::mapMessages);
    }

    public LiveData<List<MedicalResult>> getMedicalResults(String doctorId) {
        return Transformations.map(careDao.observeMedicalResults(doctorId), this::mapMedicalResults);
    }
    // endregion

    // region Admin
    public LiveData<List<User>> getAllUsers() {
        return Transformations.map(careDao.observeUsers(), this::mapUsers);
    }

    public LiveData<List<String>> getSystemAlerts() {
        return Transformations.map(careDao.observeAlerts(), this::mapAlertStrings);
    }

    public LiveData<List<String>> getAuditLog() {
        return Transformations.map(careDao.observeAuditEntries(), this::mapAuditStrings);
    }
    // endregion

    // region Secretary
    public LiveData<List<Appointment>> getSecretaryAppointments(String secretaryId) {
        return Transformations.map(careDao.observeSecretaryAppointments(secretaryId), this::mapAppointments);
    }

    public LiveData<List<String>> getSecretaryEscalations(String secretaryId) {
        return Transformations.map(careDao.observeSecretaryEscalations(secretaryId), this::mapEscalationStrings);
    }

    public LiveData<List<String>> getSecretaryPatients(String secretaryId) {
        return careDao.observePatientSummaries();
    }
    // endregion

    // region Actions
    public String requestAppointment(String patientId, String reason, String preferredDate, String preferredDoctor) {
        ioExecutor.execute(() -> {
            PatientProfileEntity profile = careDao.getPatientProfileNow(patientId);
            String patientName = profile != null ? profile.getFullName() : "Patient";
            String doctorName = TextUtils.isEmpty(preferredDoctor)
                    ? (profile != null ? profile.getPrimaryDoctorName() : "Médecin traitant")
                    : preferredDoctor;
            String doctorId = profile != null ? profile.getPrimaryDoctorId() : "doctor-1";

            String appointmentReason = TextUtils.isEmpty(reason) ? "Consultation" : reason;
            String appointmentDate = TextUtils.isEmpty(preferredDate) ? "Date à confirmer" : preferredDate;
            AppointmentEntity entity = new AppointmentEntity(
                    generateId("apt"),
                    patientId,
                    patientName,
                    doctorId,
                    doctorName,
                    appointmentReason,
                    appointmentDate,
                    "À confirmer",
                    "Téléconsultation",
                    "En revue",
                    System.currentTimeMillis(),
                    "secretary-1"
            );
            careDao.upsertAppointment(entity);
        });
        return "Demande envoyée au secrétariat.";
    }

    public String renewPrescription(String patientId, String medication, String dosage, String comment) {
        ioExecutor.execute(() -> {
            MedicationEntity entity = new MedicationEntity(
                    generateId("med"),
                    patientId,
                    TextUtils.isEmpty(medication) ? "Traitement à confirmer" : medication,
                    TextUtils.isEmpty(dosage) ? "Posologie à confirmer" : dosage,
                    TextUtils.isEmpty(comment) ? "En attente de validation" : comment,
                    "En cours"
            );
            careDao.upsertMedication(entity);
        });
        return "Demande de renouvellement envoyée au médecin.";
    }

    public String sendMessageToDoctor(String patientId, String subject, String body) {
        ioExecutor.execute(() -> {
            PatientProfileEntity profile = careDao.getPatientProfileNow(patientId);
            String patientName = profile != null ? profile.getFullName() : "Patient";
            String doctorName = profile != null ? profile.getPrimaryDoctorName() : "Médecin traitant";
            String doctorId = profile != null ? profile.getPrimaryDoctorId() : "doctor-1";

            MessageEntity patientCopy = new MessageEntity(
                    generateId("msg"),
                    patientId,
                    SCOPE_PATIENT,
                    doctorName,
                    patientName,
                    TextUtils.isEmpty(subject) ? "Message patient" : subject,
                    TextUtils.isEmpty(body) ? "Voir dossier patient." : body,
                    System.currentTimeMillis()
            );
            MessageEntity doctorCopy = new MessageEntity(
                    generateId("msg"),
                    doctorId,
                    SCOPE_DOCTOR_INBOX,
                    patientName,
                    doctorName,
                    TextUtils.isEmpty(subject) ? "Message patient" : subject,
                    TextUtils.isEmpty(body) ? "Voir dossier patient." : body,
                    System.currentTimeMillis()
            );
            careDao.upsertMessage(patientCopy);
            careDao.upsertMessage(doctorCopy);
        });
        return "Message envoyé au médecin traitant.";
    }

    public String updateDoctorAvailability(String doctorId) {
        return "Disponibilités du " + doctorId + " sauvegardées";
    }

    public String commentMedicalResult(String doctorId) {
        return "Commentaire ajouté au dossier patient";
    }

    public String replyToPatient(String doctorId) {
        return "Réponse envoyée au patient depuis " + doctorId;
    }

    public String createOrUpdateUser(String displayName, String email, UserRole role) {
        if (TextUtils.isEmpty(displayName) || TextUtils.isEmpty(email)) {
            return "Renseignez le nom et l'email.";
        }
        ioExecutor.execute(() -> {
            UserEntity entity = new UserEntity(
                    role.name().toLowerCase() + "-" + System.currentTimeMillis(),
                    displayName,
                    email,
                    role.name()
            );
            careDao.upsertUser(entity);
        });
        return "Compte " + displayName + " créé avec rôle " + role.name();
    }

    public String addSystemAlert(String alert) {
        if (TextUtils.isEmpty(alert)) {
            return "Message d'alerte requis.";
        }
        ioExecutor.execute(() -> careDao.upsertAlert(new SystemAlertEntity(generateId("alert"), alert, System.currentTimeMillis())));
        return "Alerte ajoutée.";
    }

    public String logAuditEntry(String entry) {
        if (TextUtils.isEmpty(entry)) {
            return "Description d'audit requise.";
        }
        ioExecutor.execute(() -> careDao.upsertAuditEntry(new AuditEntryEntity(generateId("audit"), entry, System.currentTimeMillis())));
        return "Entrée d'audit enregistrée.";
    }

    public String confirmAppointment(String secretaryId) {
        return "Rendez-vous confirmé par " + secretaryId;
    }

    public String updatePatientFile(String secretaryId) {
        return "Dossier patient mis à jour";
    }

    public String escalateMessage(String secretaryId) {
        ioExecutor.execute(() -> careDao.upsertSecretaryEscalation(new SecretaryEscalationEntity(
                generateId("esc"),
                secretaryId,
                "Message urgent transmis au médecin",
                System.currentTimeMillis()
        )));
        return "Message urgent transmis au médecin";
    }
    // endregion

    private List<Appointment> mapAppointments(List<AppointmentEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Appointment> appointments = new ArrayList<>();
        for (AppointmentEntity entity : entities) {
            appointments.add(entity.toModel());
        }
        return appointments;
    }

    private List<Medication> mapMedications(List<MedicationEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Medication> medications = new ArrayList<>();
        for (MedicationEntity entity : entities) {
            medications.add(entity.toModel());
        }
        return medications;
    }

    private List<Message> mapMessages(List<MessageEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Message> messages = new ArrayList<>();
        for (MessageEntity entity : entities) {
            messages.add(entity.toModel());
        }
        return messages;
    }

    private List<String> mapNotificationStrings(List<NotificationEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> notifications = new ArrayList<>();
        for (NotificationEntity entity : entities) {
            notifications.add(entity.getContent());
        }
        return notifications;
    }

    private List<PatientProfile> mapPatientProfiles(List<PatientProfileEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<PatientProfile> profiles = new ArrayList<>();
        for (PatientProfileEntity entity : entities) {
            profiles.add(entity.toModel());
        }
        return profiles;
    }

    private List<MedicalResult> mapMedicalResults(List<MedicalResultEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<MedicalResult> results = new ArrayList<>();
        for (MedicalResultEntity entity : entities) {
            results.add(entity.toModel());
        }
        return results;
    }

    private List<User> mapUsers(List<UserEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<User> users = new ArrayList<>();
        for (UserEntity entity : entities) {
            users.add(entity.toModel());
        }
        return users;
    }

    private List<String> mapAlertStrings(List<SystemAlertEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> alerts = new ArrayList<>();
        for (SystemAlertEntity entity : entities) {
            alerts.add(entity.getContent());
        }
        return alerts;
    }

    private List<String> mapAuditStrings(List<AuditEntryEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> audits = new ArrayList<>();
        for (AuditEntryEntity entity : entities) {
            audits.add(entity.getContent());
        }
        return audits;
    }

    private List<String> mapEscalationStrings(List<SecretaryEscalationEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> values = new ArrayList<>();
        for (SecretaryEscalationEntity entity : entities) {
            values.add(entity.getContent());
        }
        return values;
    }

    private String generateId(String prefix) {
        return prefix + "-" + UUID.randomUUID();
    }
}

