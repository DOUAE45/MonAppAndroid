package com.mhealth.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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

import java.util.List;

@Dao
public interface CareDao {

    // Patients
    @Query("SELECT * FROM patient_profiles WHERE id = :patientId LIMIT 1")
    LiveData<PatientProfileEntity> observePatientProfile(String patientId);

    @Query("SELECT * FROM patient_profiles WHERE id = :patientId LIMIT 1")
    PatientProfileEntity getPatientProfileNow(String patientId);

    @Query("SELECT * FROM patient_profiles WHERE primary_doctor_id = :doctorId")
    LiveData<List<PatientProfileEntity>> observePatientsForDoctor(String doctorId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertPatientProfiles(List<PatientProfileEntity> entities);

    // Appointments
    @Query("SELECT * FROM appointments WHERE patient_id = :patientId ORDER BY created_at DESC")
    LiveData<List<AppointmentEntity>> observeAppointmentsForPatient(String patientId);

    @Query("SELECT * FROM appointments WHERE doctor_id = :doctorId ORDER BY created_at DESC")
    LiveData<List<AppointmentEntity>> observeAppointmentsForDoctor(String doctorId);

    @Query("SELECT * FROM appointments WHERE owner_secretary_id = :secretaryId ORDER BY created_at DESC")
    LiveData<List<AppointmentEntity>> observeSecretaryAppointments(String secretaryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAppointments(List<AppointmentEntity> entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAppointment(AppointmentEntity entity);

    // Medications
    @Query("SELECT * FROM medications WHERE patient_id = :patientId")
    LiveData<List<MedicationEntity>> observeMedicationsForPatient(String patientId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertMedication(MedicationEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertMedications(List<MedicationEntity> entities);

    // Messages
    @Query("SELECT * FROM messages WHERE owner_id = :ownerId AND scope = :scope ORDER BY timestamp DESC")
    LiveData<List<MessageEntity>> observeMessages(String ownerId, String scope);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertMessage(MessageEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertMessages(List<MessageEntity> entities);

    // Notifications
    @Query("SELECT * FROM patient_notifications WHERE patient_id = :patientId ORDER BY timestamp DESC")
    LiveData<List<NotificationEntity>> observeNotifications(String patientId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertNotification(NotificationEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertNotifications(List<NotificationEntity> entities);

    // Medical results
    @Query("SELECT * FROM medical_results WHERE doctor_id = :doctorId ORDER BY created_at DESC")
    LiveData<List<MedicalResultEntity>> observeMedicalResults(String doctorId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertMedicalResults(List<MedicalResultEntity> entities);

    // Users / Admin data
    @Query("SELECT * FROM users ORDER BY display_name ASC")
    LiveData<List<UserEntity>> observeUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertUser(UserEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertUsers(List<UserEntity> entities);

    @Query("SELECT * FROM system_alerts ORDER BY timestamp DESC")
    LiveData<List<SystemAlertEntity>> observeAlerts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAlert(SystemAlertEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAlerts(List<SystemAlertEntity> entities);

    @Query("SELECT * FROM audit_entries ORDER BY timestamp DESC")
    LiveData<List<AuditEntryEntity>> observeAuditEntries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAuditEntry(AuditEntryEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAuditEntries(List<AuditEntryEntity> entities);

    // Secretary escalations
    @Query("SELECT * FROM secretary_escalations WHERE secretary_id = :secretaryId ORDER BY timestamp DESC")
    LiveData<List<SecretaryEscalationEntity>> observeSecretaryEscalations(String secretaryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertSecretaryEscalation(SecretaryEscalationEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertSecretaryEscalations(List<SecretaryEscalationEntity> entities);

    // Shared helpers
    @Query("SELECT full_name || ' • ID ' || medical_id FROM patient_profiles ORDER BY full_name ASC")
    LiveData<List<String>> observePatientSummaries();

    @Query("SELECT COUNT(*) FROM users")
    int getUserCount();

    @Query("SELECT COUNT(*) FROM patient_profiles")
    int getPatientCount();
}

