package com.mhealth.app.data.local;

import androidx.annotation.NonNull;

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

import java.util.Arrays;
import java.util.List;

public final class DatabaseSeeder {

    private DatabaseSeeder() {
    }

    public static void seed(@NonNull CareDao dao) {
        if (dao.getUserCount() > 0 || dao.getPatientCount() > 0) {
            return;
        }

        long now = System.currentTimeMillis();

        PatientProfileEntity saraProfile = new PatientProfileEntity(
                "patient-1",
                "Sara Benali",
                "PAT-4581",
                "CNAS - Premium",
                "doctor-1",
                "Dr. Idris Khellaf"
        );
        PatientProfileEntity ramiProfile = new PatientProfileEntity(
                "patient-2",
                "Rami B.",
                "PAT-1420",
                "CNAS",
                "doctor-1",
                "Dr. Idris Khellaf"
        );
        PatientProfileEntity dalilaProfile = new PatientProfileEntity(
                "patient-3",
                "Dalila M.",
                "PAT-3311",
                "CNAS - Premium",
                "doctor-1",
                "Dr. Idris Khellaf"
        );
        PatientProfileEntity samirProfile = new PatientProfileEntity(
                "patient-4",
                "Samir H.",
                "PAT-9981",
                "CNAS - Essentiel",
                "doctor-1",
                "Dr. Idris Khellaf"
        );

        AppointmentEntity apt1 = new AppointmentEntity(
                "apt-1",
                "patient-1",
                "Sara Benali",
                "doctor-1",
                "Dr. Idris Khellaf",
                "Suivi diabète",
                "21/11/2025",
                "09h00",
                "Clinique Nord",
                "Confirmé",
                now - 1000L,
                "secretary-1"
        );
        AppointmentEntity apt2 = new AppointmentEntity(
                "apt-2",
                "patient-1",
                "Sara Benali",
                "clinic-nord",
                "Clinique Nord",
                "Analyse laboratoire",
                "02/12/2025",
                "11h15",
                "Clinique Nord",
                "Planifié",
                now - 900L,
                "secretary-1"
        );
        AppointmentEntity apt3 = new AppointmentEntity(
                "apt-3",
                "patient-2",
                "Rami B.",
                "doctor-1",
                "Dr. Idris Khellaf",
                "Renouvellement ordonnance",
                "21/11/2025",
                "09h45",
                "Cabinet central",
                "Planifié",
                now - 800L,
                "secretary-1"
        );
        AppointmentEntity apt4 = new AppointmentEntity(
                "apt-4",
                "patient-3",
                "Dalila M.",
                "doctor-1",
                "Dr. Idris Khellaf",
                "Première consultation",
                "22/11/2025",
                "10h15",
                "Cabinet central",
                "À confirmer",
                now - 700L,
                "secretary-1"
        );
        AppointmentEntity apt5 = new AppointmentEntity(
                "apt-5",
                "patient-4",
                "Samir H.",
                "doctor-1",
                "Dr. Idris Khellaf",
                "Suivi annuel",
                "22/11/2025",
                "11h00",
                "Cabinet central",
                "À confirmer",
                now - 600L,
                "secretary-1"
        );

        MedicationEntity med1 = new MedicationEntity("med-1", "patient-1", "Metformine", "850 mg", "2x / jour", "Actif");
        MedicationEntity med2 = new MedicationEntity("med-2", "patient-1", "Vitamine D", "1000 UI", "1x / jour", "Actif");

        MessageEntity patientMsg1 = new MessageEntity(
                "msg-1",
                "patient-1",
                "PATIENT",
                "Dr. Idris Khellaf",
                "Sara Benali",
                "Ajustement traitement",
                "Merci de vérifier votre glycémie avant le prochain rendez-vous.",
                now - 500L
        );
        MessageEntity patientMsg2 = new MessageEntity(
                "msg-2",
                "patient-1",
                "PATIENT",
                "Infirmière Lina",
                "Sara Benali",
                "Préparation analyse",
                "Arrivez 15 min à l'avance pour l'enregistrement.",
                now - 400L
        );
        MessageEntity doctorMsg1 = new MessageEntity(
                "msg-3",
                "doctor-1",
                "DOCTOR_INBOX",
                "Sara Benali",
                "Dr. Idris Khellaf",
                "Question traitement",
                "Dois-je prolonger l'antibiotique ?",
                now - 300L
        );
        MessageEntity doctorMsg2 = new MessageEntity(
                "msg-4",
                "doctor-1",
                "DOCTOR_INBOX",
                "Secrétariat",
                "Dr. Idris Khellaf",
                "Patient urgent",
                "Besoin de confirmation pour créneau 14h00.",
                now - 200L
        );

        NotificationEntity notif1 = new NotificationEntity("not-1", "patient-1", "Rappel : prise de Metformine à 20h00", now - 150L);
        NotificationEntity notif2 = new NotificationEntity("not-2", "patient-1", "Dossier partagé avec la clinique nord", now - 120L);

        MedicalResultEntity result1 = new MedicalResultEntity("res-1", "doctor-1", "Laboratoire", "HbA1c à 7.2%", "Nouveau", now - 110L);
        MedicalResultEntity result2 = new MedicalResultEntity("res-2", "doctor-1", "Imagerie", "Échographie abdominale - RAS", "En cours de validation", now - 100L);

        SecretaryEscalationEntity esc1 = new SecretaryEscalationEntity("esc-1", "secretary-1", "Patient Sara B. demande un créneau avant 15/11.", now - 90L);
        SecretaryEscalationEntity esc2 = new SecretaryEscalationEntity("esc-2", "secretary-1", "Résultat critique reçu pour Rami B.", now - 80L);

        SystemAlertEntity alert1 = new SystemAlertEntity("alert-1", "Serveur FHIR opérationnel", now - 70L);
        SystemAlertEntity alert2 = new SystemAlertEntity("alert-2", "Dernière sauvegarde: 03h12", now - 60L);

        AuditEntryEntity audit1 = new AuditEntryEntity("audit-1", "11:02 Lamia R. a modifié les droits du Dr. Idris K.", now - 50L);
        AuditEntryEntity audit2 = new AuditEntryEntity("audit-2", "09:42 Nouvelle inscription patient - Sara B.", now - 40L);

        List<UserEntity> users = Arrays.asList(
                new UserEntity("patient-1", "Sara Benali", "patient@mhealth.com", "PATIENT"),
                new UserEntity("doctor-1", "Dr. Idris Khellaf", "doctor@mhealth.com", "DOCTOR"),
                new UserEntity("admin-1", "Lamia Rached", "admin@mhealth.com", "ADMIN"),
                new UserEntity("secretary-1", "Nora Bouzid", "secretary@mhealth.com", "SECRETARY")
        );

        dao.upsertPatientProfiles(Arrays.asList(saraProfile, ramiProfile, dalilaProfile, samirProfile));
        dao.upsertUsers(users);
        dao.upsertAppointments(Arrays.asList(apt1, apt2, apt3, apt4, apt5));
        dao.upsertMedications(Arrays.asList(med1, med2));
        dao.upsertMessages(Arrays.asList(patientMsg1, patientMsg2, doctorMsg1, doctorMsg2));
        dao.upsertNotifications(Arrays.asList(notif1, notif2));
        dao.upsertMedicalResults(Arrays.asList(result1, result2));
        dao.upsertSecretaryEscalations(Arrays.asList(esc1, esc2));
        dao.upsertAlerts(Arrays.asList(alert1, alert2));
        dao.upsertAuditEntries(Arrays.asList(audit1, audit2));
    }
}

