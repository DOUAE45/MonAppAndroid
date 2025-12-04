package com.mhealth.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
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
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CareDao_Impl implements CareDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PatientProfileEntity> __insertionAdapterOfPatientProfileEntity;

  private final EntityInsertionAdapter<AppointmentEntity> __insertionAdapterOfAppointmentEntity;

  private final EntityInsertionAdapter<MedicationEntity> __insertionAdapterOfMedicationEntity;

  private final EntityInsertionAdapter<MessageEntity> __insertionAdapterOfMessageEntity;

  private final EntityInsertionAdapter<NotificationEntity> __insertionAdapterOfNotificationEntity;

  private final EntityInsertionAdapter<MedicalResultEntity> __insertionAdapterOfMedicalResultEntity;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final EntityInsertionAdapter<SystemAlertEntity> __insertionAdapterOfSystemAlertEntity;

  private final EntityInsertionAdapter<AuditEntryEntity> __insertionAdapterOfAuditEntryEntity;

  private final EntityInsertionAdapter<SecretaryEscalationEntity> __insertionAdapterOfSecretaryEscalationEntity;

  public CareDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPatientProfileEntity = new EntityInsertionAdapter<PatientProfileEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `patient_profiles` (`id`,`full_name`,`medical_id`,`insurance`,`primary_doctor_id`,`primary_doctor_name`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final PatientProfileEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getFullName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFullName());
        }
        if (entity.getMedicalId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMedicalId());
        }
        if (entity.getInsurance() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getInsurance());
        }
        if (entity.getPrimaryDoctorId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPrimaryDoctorId());
        }
        if (entity.getPrimaryDoctorName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPrimaryDoctorName());
        }
      }
    };
    this.__insertionAdapterOfAppointmentEntity = new EntityInsertionAdapter<AppointmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `appointments` (`id`,`patient_id`,`patient_name`,`doctor_id`,`doctor_name`,`reason`,`date_label`,`time_label`,`location`,`status`,`created_at`,`owner_secretary_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final AppointmentEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getPatientId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPatientId());
        }
        if (entity.getPatientName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPatientName());
        }
        if (entity.getDoctorId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDoctorId());
        }
        if (entity.getDoctorName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDoctorName());
        }
        if (entity.getReason() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getReason());
        }
        if (entity.getDateLabel() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDateLabel());
        }
        if (entity.getTimeLabel() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getTimeLabel());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getLocation());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getStatus());
        }
        statement.bindLong(11, entity.getCreatedAt());
        if (entity.getOwnerSecretaryId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getOwnerSecretaryId());
        }
      }
    };
    this.__insertionAdapterOfMedicationEntity = new EntityInsertionAdapter<MedicationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `medications` (`id`,`patient_id`,`name`,`dosage`,`frequency`,`status`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final MedicationEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getPatientId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPatientId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getDosage() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDosage());
        }
        if (entity.getFrequency() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFrequency());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
      }
    };
    this.__insertionAdapterOfMessageEntity = new EntityInsertionAdapter<MessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `messages` (`id`,`owner_id`,`scope`,`sender_name`,`receiver_name`,`subject`,`body`,`timestamp`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final MessageEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getOwnerId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOwnerId());
        }
        if (entity.getScope() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getScope());
        }
        if (entity.getSenderName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSenderName());
        }
        if (entity.getReceiverName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getReceiverName());
        }
        if (entity.getSubject() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSubject());
        }
        if (entity.getBody() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBody());
        }
        statement.bindLong(8, entity.getTimestamp());
      }
    };
    this.__insertionAdapterOfNotificationEntity = new EntityInsertionAdapter<NotificationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `patient_notifications` (`id`,`patient_id`,`content`,`timestamp`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final NotificationEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getPatientId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPatientId());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContent());
        }
        statement.bindLong(4, entity.getTimestamp());
      }
    };
    this.__insertionAdapterOfMedicalResultEntity = new EntityInsertionAdapter<MedicalResultEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `medical_results` (`id`,`doctor_id`,`type`,`summary`,`status`,`created_at`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final MedicalResultEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getDoctorId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDoctorId());
        }
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getType());
        }
        if (entity.getSummary() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSummary());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getStatus());
        }
        statement.bindLong(6, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`display_name`,`email`,`role`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getDisplayName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDisplayName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        if (entity.getRole() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRole());
        }
      }
    };
    this.__insertionAdapterOfSystemAlertEntity = new EntityInsertionAdapter<SystemAlertEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `system_alerts` (`id`,`content`,`timestamp`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final SystemAlertEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        statement.bindLong(3, entity.getTimestamp());
      }
    };
    this.__insertionAdapterOfAuditEntryEntity = new EntityInsertionAdapter<AuditEntryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `audit_entries` (`id`,`content`,`timestamp`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final AuditEntryEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        statement.bindLong(3, entity.getTimestamp());
      }
    };
    this.__insertionAdapterOfSecretaryEscalationEntity = new EntityInsertionAdapter<SecretaryEscalationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `secretary_escalations` (`id`,`secretary_id`,`content`,`timestamp`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final SecretaryEscalationEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getSecretaryId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSecretaryId());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContent());
        }
        statement.bindLong(4, entity.getTimestamp());
      }
    };
  }

  @Override
  public void upsertPatientProfiles(final List<PatientProfileEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPatientProfileEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertAppointments(final List<AppointmentEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAppointmentEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertAppointment(final AppointmentEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAppointmentEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertMedication(final MedicationEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMedicationEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertMedications(final List<MedicationEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMedicationEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertMessage(final MessageEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessageEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertMessages(final List<MessageEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessageEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertNotification(final NotificationEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotificationEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertNotifications(final List<NotificationEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotificationEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertMedicalResults(final List<MedicalResultEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMedicalResultEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertUser(final UserEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertUsers(final List<UserEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertAlert(final SystemAlertEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSystemAlertEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertAlerts(final List<SystemAlertEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSystemAlertEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertAuditEntry(final AuditEntryEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAuditEntryEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertAuditEntries(final List<AuditEntryEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAuditEntryEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertSecretaryEscalation(final SecretaryEscalationEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSecretaryEscalationEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upsertSecretaryEscalations(final List<SecretaryEscalationEntity> entities) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSecretaryEscalationEntity.insert(entities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<PatientProfileEntity> observePatientProfile(final String patientId) {
    final String _sql = "SELECT * FROM patient_profiles WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (patientId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, patientId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"patient_profiles"}, false, new Callable<PatientProfileEntity>() {
      @Override
      @Nullable
      public PatientProfileEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
          final int _cursorIndexOfMedicalId = CursorUtil.getColumnIndexOrThrow(_cursor, "medical_id");
          final int _cursorIndexOfInsurance = CursorUtil.getColumnIndexOrThrow(_cursor, "insurance");
          final int _cursorIndexOfPrimaryDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "primary_doctor_id");
          final int _cursorIndexOfPrimaryDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "primary_doctor_name");
          final PatientProfileEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpFullName;
            if (_cursor.isNull(_cursorIndexOfFullName)) {
              _tmpFullName = null;
            } else {
              _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            }
            final String _tmpMedicalId;
            if (_cursor.isNull(_cursorIndexOfMedicalId)) {
              _tmpMedicalId = null;
            } else {
              _tmpMedicalId = _cursor.getString(_cursorIndexOfMedicalId);
            }
            final String _tmpInsurance;
            if (_cursor.isNull(_cursorIndexOfInsurance)) {
              _tmpInsurance = null;
            } else {
              _tmpInsurance = _cursor.getString(_cursorIndexOfInsurance);
            }
            final String _tmpPrimaryDoctorId;
            if (_cursor.isNull(_cursorIndexOfPrimaryDoctorId)) {
              _tmpPrimaryDoctorId = null;
            } else {
              _tmpPrimaryDoctorId = _cursor.getString(_cursorIndexOfPrimaryDoctorId);
            }
            final String _tmpPrimaryDoctorName;
            if (_cursor.isNull(_cursorIndexOfPrimaryDoctorName)) {
              _tmpPrimaryDoctorName = null;
            } else {
              _tmpPrimaryDoctorName = _cursor.getString(_cursorIndexOfPrimaryDoctorName);
            }
            _result = new PatientProfileEntity(_tmpId,_tmpFullName,_tmpMedicalId,_tmpInsurance,_tmpPrimaryDoctorId,_tmpPrimaryDoctorName);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public PatientProfileEntity getPatientProfileNow(final String patientId) {
    final String _sql = "SELECT * FROM patient_profiles WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (patientId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, patientId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
      final int _cursorIndexOfMedicalId = CursorUtil.getColumnIndexOrThrow(_cursor, "medical_id");
      final int _cursorIndexOfInsurance = CursorUtil.getColumnIndexOrThrow(_cursor, "insurance");
      final int _cursorIndexOfPrimaryDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "primary_doctor_id");
      final int _cursorIndexOfPrimaryDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "primary_doctor_name");
      final PatientProfileEntity _result;
      if (_cursor.moveToFirst()) {
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        final String _tmpMedicalId;
        if (_cursor.isNull(_cursorIndexOfMedicalId)) {
          _tmpMedicalId = null;
        } else {
          _tmpMedicalId = _cursor.getString(_cursorIndexOfMedicalId);
        }
        final String _tmpInsurance;
        if (_cursor.isNull(_cursorIndexOfInsurance)) {
          _tmpInsurance = null;
        } else {
          _tmpInsurance = _cursor.getString(_cursorIndexOfInsurance);
        }
        final String _tmpPrimaryDoctorId;
        if (_cursor.isNull(_cursorIndexOfPrimaryDoctorId)) {
          _tmpPrimaryDoctorId = null;
        } else {
          _tmpPrimaryDoctorId = _cursor.getString(_cursorIndexOfPrimaryDoctorId);
        }
        final String _tmpPrimaryDoctorName;
        if (_cursor.isNull(_cursorIndexOfPrimaryDoctorName)) {
          _tmpPrimaryDoctorName = null;
        } else {
          _tmpPrimaryDoctorName = _cursor.getString(_cursorIndexOfPrimaryDoctorName);
        }
        _result = new PatientProfileEntity(_tmpId,_tmpFullName,_tmpMedicalId,_tmpInsurance,_tmpPrimaryDoctorId,_tmpPrimaryDoctorName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<PatientProfileEntity>> observePatientsForDoctor(final String doctorId) {
    final String _sql = "SELECT * FROM patient_profiles WHERE primary_doctor_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (doctorId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, doctorId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"patient_profiles"}, false, new Callable<List<PatientProfileEntity>>() {
      @Override
      @Nullable
      public List<PatientProfileEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
          final int _cursorIndexOfMedicalId = CursorUtil.getColumnIndexOrThrow(_cursor, "medical_id");
          final int _cursorIndexOfInsurance = CursorUtil.getColumnIndexOrThrow(_cursor, "insurance");
          final int _cursorIndexOfPrimaryDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "primary_doctor_id");
          final int _cursorIndexOfPrimaryDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "primary_doctor_name");
          final List<PatientProfileEntity> _result = new ArrayList<PatientProfileEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PatientProfileEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpFullName;
            if (_cursor.isNull(_cursorIndexOfFullName)) {
              _tmpFullName = null;
            } else {
              _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            }
            final String _tmpMedicalId;
            if (_cursor.isNull(_cursorIndexOfMedicalId)) {
              _tmpMedicalId = null;
            } else {
              _tmpMedicalId = _cursor.getString(_cursorIndexOfMedicalId);
            }
            final String _tmpInsurance;
            if (_cursor.isNull(_cursorIndexOfInsurance)) {
              _tmpInsurance = null;
            } else {
              _tmpInsurance = _cursor.getString(_cursorIndexOfInsurance);
            }
            final String _tmpPrimaryDoctorId;
            if (_cursor.isNull(_cursorIndexOfPrimaryDoctorId)) {
              _tmpPrimaryDoctorId = null;
            } else {
              _tmpPrimaryDoctorId = _cursor.getString(_cursorIndexOfPrimaryDoctorId);
            }
            final String _tmpPrimaryDoctorName;
            if (_cursor.isNull(_cursorIndexOfPrimaryDoctorName)) {
              _tmpPrimaryDoctorName = null;
            } else {
              _tmpPrimaryDoctorName = _cursor.getString(_cursorIndexOfPrimaryDoctorName);
            }
            _item = new PatientProfileEntity(_tmpId,_tmpFullName,_tmpMedicalId,_tmpInsurance,_tmpPrimaryDoctorId,_tmpPrimaryDoctorName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<AppointmentEntity>> observeAppointmentsForPatient(final String patientId) {
    final String _sql = "SELECT * FROM appointments WHERE patient_id = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (patientId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, patientId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"appointments"}, false, new Callable<List<AppointmentEntity>>() {
      @Override
      @Nullable
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_id");
          final int _cursorIndexOfPatientName = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_name");
          final int _cursorIndexOfDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "doctor_id");
          final int _cursorIndexOfDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "doctor_name");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfDateLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "date_label");
          final int _cursorIndexOfTimeLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "time_label");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfOwnerSecretaryId = CursorUtil.getColumnIndexOrThrow(_cursor, "owner_secretary_id");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
            }
            final String _tmpPatientName;
            if (_cursor.isNull(_cursorIndexOfPatientName)) {
              _tmpPatientName = null;
            } else {
              _tmpPatientName = _cursor.getString(_cursorIndexOfPatientName);
            }
            final String _tmpDoctorId;
            if (_cursor.isNull(_cursorIndexOfDoctorId)) {
              _tmpDoctorId = null;
            } else {
              _tmpDoctorId = _cursor.getString(_cursorIndexOfDoctorId);
            }
            final String _tmpDoctorName;
            if (_cursor.isNull(_cursorIndexOfDoctorName)) {
              _tmpDoctorName = null;
            } else {
              _tmpDoctorName = _cursor.getString(_cursorIndexOfDoctorName);
            }
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            final String _tmpDateLabel;
            if (_cursor.isNull(_cursorIndexOfDateLabel)) {
              _tmpDateLabel = null;
            } else {
              _tmpDateLabel = _cursor.getString(_cursorIndexOfDateLabel);
            }
            final String _tmpTimeLabel;
            if (_cursor.isNull(_cursorIndexOfTimeLabel)) {
              _tmpTimeLabel = null;
            } else {
              _tmpTimeLabel = _cursor.getString(_cursorIndexOfTimeLabel);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpOwnerSecretaryId;
            if (_cursor.isNull(_cursorIndexOfOwnerSecretaryId)) {
              _tmpOwnerSecretaryId = null;
            } else {
              _tmpOwnerSecretaryId = _cursor.getString(_cursorIndexOfOwnerSecretaryId);
            }
            _item = new AppointmentEntity(_tmpId,_tmpPatientId,_tmpPatientName,_tmpDoctorId,_tmpDoctorName,_tmpReason,_tmpDateLabel,_tmpTimeLabel,_tmpLocation,_tmpStatus,_tmpCreatedAt,_tmpOwnerSecretaryId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<AppointmentEntity>> observeAppointmentsForDoctor(final String doctorId) {
    final String _sql = "SELECT * FROM appointments WHERE doctor_id = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (doctorId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, doctorId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"appointments"}, false, new Callable<List<AppointmentEntity>>() {
      @Override
      @Nullable
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_id");
          final int _cursorIndexOfPatientName = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_name");
          final int _cursorIndexOfDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "doctor_id");
          final int _cursorIndexOfDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "doctor_name");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfDateLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "date_label");
          final int _cursorIndexOfTimeLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "time_label");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfOwnerSecretaryId = CursorUtil.getColumnIndexOrThrow(_cursor, "owner_secretary_id");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
            }
            final String _tmpPatientName;
            if (_cursor.isNull(_cursorIndexOfPatientName)) {
              _tmpPatientName = null;
            } else {
              _tmpPatientName = _cursor.getString(_cursorIndexOfPatientName);
            }
            final String _tmpDoctorId;
            if (_cursor.isNull(_cursorIndexOfDoctorId)) {
              _tmpDoctorId = null;
            } else {
              _tmpDoctorId = _cursor.getString(_cursorIndexOfDoctorId);
            }
            final String _tmpDoctorName;
            if (_cursor.isNull(_cursorIndexOfDoctorName)) {
              _tmpDoctorName = null;
            } else {
              _tmpDoctorName = _cursor.getString(_cursorIndexOfDoctorName);
            }
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            final String _tmpDateLabel;
            if (_cursor.isNull(_cursorIndexOfDateLabel)) {
              _tmpDateLabel = null;
            } else {
              _tmpDateLabel = _cursor.getString(_cursorIndexOfDateLabel);
            }
            final String _tmpTimeLabel;
            if (_cursor.isNull(_cursorIndexOfTimeLabel)) {
              _tmpTimeLabel = null;
            } else {
              _tmpTimeLabel = _cursor.getString(_cursorIndexOfTimeLabel);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpOwnerSecretaryId;
            if (_cursor.isNull(_cursorIndexOfOwnerSecretaryId)) {
              _tmpOwnerSecretaryId = null;
            } else {
              _tmpOwnerSecretaryId = _cursor.getString(_cursorIndexOfOwnerSecretaryId);
            }
            _item = new AppointmentEntity(_tmpId,_tmpPatientId,_tmpPatientName,_tmpDoctorId,_tmpDoctorName,_tmpReason,_tmpDateLabel,_tmpTimeLabel,_tmpLocation,_tmpStatus,_tmpCreatedAt,_tmpOwnerSecretaryId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<AppointmentEntity>> observeSecretaryAppointments(final String secretaryId) {
    final String _sql = "SELECT * FROM appointments WHERE owner_secretary_id = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (secretaryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, secretaryId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"appointments"}, false, new Callable<List<AppointmentEntity>>() {
      @Override
      @Nullable
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_id");
          final int _cursorIndexOfPatientName = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_name");
          final int _cursorIndexOfDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "doctor_id");
          final int _cursorIndexOfDoctorName = CursorUtil.getColumnIndexOrThrow(_cursor, "doctor_name");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfDateLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "date_label");
          final int _cursorIndexOfTimeLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "time_label");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfOwnerSecretaryId = CursorUtil.getColumnIndexOrThrow(_cursor, "owner_secretary_id");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
            }
            final String _tmpPatientName;
            if (_cursor.isNull(_cursorIndexOfPatientName)) {
              _tmpPatientName = null;
            } else {
              _tmpPatientName = _cursor.getString(_cursorIndexOfPatientName);
            }
            final String _tmpDoctorId;
            if (_cursor.isNull(_cursorIndexOfDoctorId)) {
              _tmpDoctorId = null;
            } else {
              _tmpDoctorId = _cursor.getString(_cursorIndexOfDoctorId);
            }
            final String _tmpDoctorName;
            if (_cursor.isNull(_cursorIndexOfDoctorName)) {
              _tmpDoctorName = null;
            } else {
              _tmpDoctorName = _cursor.getString(_cursorIndexOfDoctorName);
            }
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            final String _tmpDateLabel;
            if (_cursor.isNull(_cursorIndexOfDateLabel)) {
              _tmpDateLabel = null;
            } else {
              _tmpDateLabel = _cursor.getString(_cursorIndexOfDateLabel);
            }
            final String _tmpTimeLabel;
            if (_cursor.isNull(_cursorIndexOfTimeLabel)) {
              _tmpTimeLabel = null;
            } else {
              _tmpTimeLabel = _cursor.getString(_cursorIndexOfTimeLabel);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final String _tmpOwnerSecretaryId;
            if (_cursor.isNull(_cursorIndexOfOwnerSecretaryId)) {
              _tmpOwnerSecretaryId = null;
            } else {
              _tmpOwnerSecretaryId = _cursor.getString(_cursorIndexOfOwnerSecretaryId);
            }
            _item = new AppointmentEntity(_tmpId,_tmpPatientId,_tmpPatientName,_tmpDoctorId,_tmpDoctorName,_tmpReason,_tmpDateLabel,_tmpTimeLabel,_tmpLocation,_tmpStatus,_tmpCreatedAt,_tmpOwnerSecretaryId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<MedicationEntity>> observeMedicationsForPatient(final String patientId) {
    final String _sql = "SELECT * FROM medications WHERE patient_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (patientId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, patientId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"medications"}, false, new Callable<List<MedicationEntity>>() {
      @Override
      @Nullable
      public List<MedicationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDosage = CursorUtil.getColumnIndexOrThrow(_cursor, "dosage");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<MedicationEntity> _result = new ArrayList<MedicationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicationEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDosage;
            if (_cursor.isNull(_cursorIndexOfDosage)) {
              _tmpDosage = null;
            } else {
              _tmpDosage = _cursor.getString(_cursorIndexOfDosage);
            }
            final String _tmpFrequency;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmpFrequency = null;
            } else {
              _tmpFrequency = _cursor.getString(_cursorIndexOfFrequency);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new MedicationEntity(_tmpId,_tmpPatientId,_tmpName,_tmpDosage,_tmpFrequency,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<MessageEntity>> observeMessages(final String ownerId, final String scope) {
    final String _sql = "SELECT * FROM messages WHERE owner_id = ? AND scope = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (ownerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ownerId);
    }
    _argIndex = 2;
    if (scope == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, scope);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"messages"}, false, new Callable<List<MessageEntity>>() {
      @Override
      @Nullable
      public List<MessageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "owner_id");
          final int _cursorIndexOfScope = CursorUtil.getColumnIndexOrThrow(_cursor, "scope");
          final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
          final int _cursorIndexOfReceiverName = CursorUtil.getColumnIndexOrThrow(_cursor, "receiver_name");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<MessageEntity> _result = new ArrayList<MessageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MessageEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpOwnerId;
            if (_cursor.isNull(_cursorIndexOfOwnerId)) {
              _tmpOwnerId = null;
            } else {
              _tmpOwnerId = _cursor.getString(_cursorIndexOfOwnerId);
            }
            final String _tmpScope;
            if (_cursor.isNull(_cursorIndexOfScope)) {
              _tmpScope = null;
            } else {
              _tmpScope = _cursor.getString(_cursorIndexOfScope);
            }
            final String _tmpSenderName;
            if (_cursor.isNull(_cursorIndexOfSenderName)) {
              _tmpSenderName = null;
            } else {
              _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
            }
            final String _tmpReceiverName;
            if (_cursor.isNull(_cursorIndexOfReceiverName)) {
              _tmpReceiverName = null;
            } else {
              _tmpReceiverName = _cursor.getString(_cursorIndexOfReceiverName);
            }
            final String _tmpSubject;
            if (_cursor.isNull(_cursorIndexOfSubject)) {
              _tmpSubject = null;
            } else {
              _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            }
            final String _tmpBody;
            if (_cursor.isNull(_cursorIndexOfBody)) {
              _tmpBody = null;
            } else {
              _tmpBody = _cursor.getString(_cursorIndexOfBody);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new MessageEntity(_tmpId,_tmpOwnerId,_tmpScope,_tmpSenderName,_tmpReceiverName,_tmpSubject,_tmpBody,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<NotificationEntity>> observeNotifications(final String patientId) {
    final String _sql = "SELECT * FROM patient_notifications WHERE patient_id = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (patientId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, patientId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"patient_notifications"}, false, new Callable<List<NotificationEntity>>() {
      @Override
      @Nullable
      public List<NotificationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatientId = CursorUtil.getColumnIndexOrThrow(_cursor, "patient_id");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<NotificationEntity> _result = new ArrayList<NotificationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final NotificationEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPatientId;
            if (_cursor.isNull(_cursorIndexOfPatientId)) {
              _tmpPatientId = null;
            } else {
              _tmpPatientId = _cursor.getString(_cursorIndexOfPatientId);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new NotificationEntity(_tmpId,_tmpPatientId,_tmpContent,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<MedicalResultEntity>> observeMedicalResults(final String doctorId) {
    final String _sql = "SELECT * FROM medical_results WHERE doctor_id = ? ORDER BY created_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (doctorId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, doctorId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"medical_results"}, false, new Callable<List<MedicalResultEntity>>() {
      @Override
      @Nullable
      public List<MedicalResultEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDoctorId = CursorUtil.getColumnIndexOrThrow(_cursor, "doctor_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "summary");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final List<MedicalResultEntity> _result = new ArrayList<MedicalResultEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MedicalResultEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpDoctorId;
            if (_cursor.isNull(_cursorIndexOfDoctorId)) {
              _tmpDoctorId = null;
            } else {
              _tmpDoctorId = _cursor.getString(_cursorIndexOfDoctorId);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpSummary;
            if (_cursor.isNull(_cursorIndexOfSummary)) {
              _tmpSummary = null;
            } else {
              _tmpSummary = _cursor.getString(_cursorIndexOfSummary);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MedicalResultEntity(_tmpId,_tmpDoctorId,_tmpType,_tmpSummary,_tmpStatus,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<UserEntity>> observeUsers() {
    final String _sql = "SELECT * FROM users ORDER BY display_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"users"}, false, new Callable<List<UserEntity>>() {
      @Override
      @Nullable
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "display_name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpDisplayName;
            if (_cursor.isNull(_cursorIndexOfDisplayName)) {
              _tmpDisplayName = null;
            } else {
              _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpRole;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmpRole = null;
            } else {
              _tmpRole = _cursor.getString(_cursorIndexOfRole);
            }
            _item = new UserEntity(_tmpId,_tmpDisplayName,_tmpEmail,_tmpRole);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SystemAlertEntity>> observeAlerts() {
    final String _sql = "SELECT * FROM system_alerts ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"system_alerts"}, false, new Callable<List<SystemAlertEntity>>() {
      @Override
      @Nullable
      public List<SystemAlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<SystemAlertEntity> _result = new ArrayList<SystemAlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SystemAlertEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new SystemAlertEntity(_tmpId,_tmpContent,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<AuditEntryEntity>> observeAuditEntries() {
    final String _sql = "SELECT * FROM audit_entries ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"audit_entries"}, false, new Callable<List<AuditEntryEntity>>() {
      @Override
      @Nullable
      public List<AuditEntryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<AuditEntryEntity> _result = new ArrayList<AuditEntryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AuditEntryEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new AuditEntryEntity(_tmpId,_tmpContent,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SecretaryEscalationEntity>> observeSecretaryEscalations(
      final String secretaryId) {
    final String _sql = "SELECT * FROM secretary_escalations WHERE secretary_id = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (secretaryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, secretaryId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"secretary_escalations"}, false, new Callable<List<SecretaryEscalationEntity>>() {
      @Override
      @Nullable
      public List<SecretaryEscalationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSecretaryId = CursorUtil.getColumnIndexOrThrow(_cursor, "secretary_id");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<SecretaryEscalationEntity> _result = new ArrayList<SecretaryEscalationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SecretaryEscalationEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSecretaryId;
            if (_cursor.isNull(_cursorIndexOfSecretaryId)) {
              _tmpSecretaryId = null;
            } else {
              _tmpSecretaryId = _cursor.getString(_cursorIndexOfSecretaryId);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new SecretaryEscalationEntity(_tmpId,_tmpSecretaryId,_tmpContent,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<String>> observePatientSummaries() {
    final String _sql = "SELECT full_name || ' • ID ' || medical_id FROM patient_profiles ORDER BY full_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"patient_profiles"}, false, new Callable<List<String>>() {
      @Override
      @Nullable
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            final String _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(0);
            }
            _item = _tmp;
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int getUserCount() {
    final String _sql = "SELECT COUNT(*) FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getPatientCount() {
    final String _sql = "SELECT COUNT(*) FROM patient_profiles";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
