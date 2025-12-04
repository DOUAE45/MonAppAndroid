package com.mhealth.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.mhealth.app.data.local.dao.CareDao;
import com.mhealth.app.data.local.dao.CareDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MHealthDatabase_Impl extends MHealthDatabase {
  private volatile CareDao _careDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `patient_profiles` (`id` TEXT NOT NULL, `full_name` TEXT, `medical_id` TEXT, `insurance` TEXT, `primary_doctor_id` TEXT, `primary_doctor_name` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `appointments` (`id` TEXT NOT NULL, `patient_id` TEXT, `patient_name` TEXT, `doctor_id` TEXT, `doctor_name` TEXT, `reason` TEXT, `date_label` TEXT, `time_label` TEXT, `location` TEXT, `status` TEXT, `created_at` INTEGER NOT NULL, `owner_secretary_id` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `medications` (`id` TEXT NOT NULL, `patient_id` TEXT, `name` TEXT, `dosage` TEXT, `frequency` TEXT, `status` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `messages` (`id` TEXT NOT NULL, `owner_id` TEXT, `scope` TEXT, `sender_name` TEXT, `receiver_name` TEXT, `subject` TEXT, `body` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `patient_notifications` (`id` TEXT NOT NULL, `patient_id` TEXT, `content` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `medical_results` (`id` TEXT NOT NULL, `doctor_id` TEXT, `type` TEXT, `summary` TEXT, `status` TEXT, `created_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `display_name` TEXT, `email` TEXT, `role` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `system_alerts` (`id` TEXT NOT NULL, `content` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `audit_entries` (`id` TEXT NOT NULL, `content` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `secretary_escalations` (`id` TEXT NOT NULL, `secretary_id` TEXT, `content` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2b5f6897f09a1ec162f9ae2f9f0b1b1c')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `patient_profiles`");
        db.execSQL("DROP TABLE IF EXISTS `appointments`");
        db.execSQL("DROP TABLE IF EXISTS `medications`");
        db.execSQL("DROP TABLE IF EXISTS `messages`");
        db.execSQL("DROP TABLE IF EXISTS `patient_notifications`");
        db.execSQL("DROP TABLE IF EXISTS `medical_results`");
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `system_alerts`");
        db.execSQL("DROP TABLE IF EXISTS `audit_entries`");
        db.execSQL("DROP TABLE IF EXISTS `secretary_escalations`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPatientProfiles = new HashMap<String, TableInfo.Column>(6);
        _columnsPatientProfiles.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientProfiles.put("full_name", new TableInfo.Column("full_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientProfiles.put("medical_id", new TableInfo.Column("medical_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientProfiles.put("insurance", new TableInfo.Column("insurance", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientProfiles.put("primary_doctor_id", new TableInfo.Column("primary_doctor_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientProfiles.put("primary_doctor_name", new TableInfo.Column("primary_doctor_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPatientProfiles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPatientProfiles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPatientProfiles = new TableInfo("patient_profiles", _columnsPatientProfiles, _foreignKeysPatientProfiles, _indicesPatientProfiles);
        final TableInfo _existingPatientProfiles = TableInfo.read(db, "patient_profiles");
        if (!_infoPatientProfiles.equals(_existingPatientProfiles)) {
          return new RoomOpenHelper.ValidationResult(false, "patient_profiles(com.mhealth.app.data.local.entity.PatientProfileEntity).\n"
                  + " Expected:\n" + _infoPatientProfiles + "\n"
                  + " Found:\n" + _existingPatientProfiles);
        }
        final HashMap<String, TableInfo.Column> _columnsAppointments = new HashMap<String, TableInfo.Column>(12);
        _columnsAppointments.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("patient_id", new TableInfo.Column("patient_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("patient_name", new TableInfo.Column("patient_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("doctor_id", new TableInfo.Column("doctor_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("doctor_name", new TableInfo.Column("doctor_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("reason", new TableInfo.Column("reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("date_label", new TableInfo.Column("date_label", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("time_label", new TableInfo.Column("time_label", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("owner_secretary_id", new TableInfo.Column("owner_secretary_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppointments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppointments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppointments = new TableInfo("appointments", _columnsAppointments, _foreignKeysAppointments, _indicesAppointments);
        final TableInfo _existingAppointments = TableInfo.read(db, "appointments");
        if (!_infoAppointments.equals(_existingAppointments)) {
          return new RoomOpenHelper.ValidationResult(false, "appointments(com.mhealth.app.data.local.entity.AppointmentEntity).\n"
                  + " Expected:\n" + _infoAppointments + "\n"
                  + " Found:\n" + _existingAppointments);
        }
        final HashMap<String, TableInfo.Column> _columnsMedications = new HashMap<String, TableInfo.Column>(6);
        _columnsMedications.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedications.put("patient_id", new TableInfo.Column("patient_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedications.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedications.put("dosage", new TableInfo.Column("dosage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedications.put("frequency", new TableInfo.Column("frequency", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedications.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMedications = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMedications = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMedications = new TableInfo("medications", _columnsMedications, _foreignKeysMedications, _indicesMedications);
        final TableInfo _existingMedications = TableInfo.read(db, "medications");
        if (!_infoMedications.equals(_existingMedications)) {
          return new RoomOpenHelper.ValidationResult(false, "medications(com.mhealth.app.data.local.entity.MedicationEntity).\n"
                  + " Expected:\n" + _infoMedications + "\n"
                  + " Found:\n" + _existingMedications);
        }
        final HashMap<String, TableInfo.Column> _columnsMessages = new HashMap<String, TableInfo.Column>(8);
        _columnsMessages.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("owner_id", new TableInfo.Column("owner_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("scope", new TableInfo.Column("scope", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("receiver_name", new TableInfo.Column("receiver_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("subject", new TableInfo.Column("subject", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("body", new TableInfo.Column("body", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessages.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMessages = new TableInfo("messages", _columnsMessages, _foreignKeysMessages, _indicesMessages);
        final TableInfo _existingMessages = TableInfo.read(db, "messages");
        if (!_infoMessages.equals(_existingMessages)) {
          return new RoomOpenHelper.ValidationResult(false, "messages(com.mhealth.app.data.local.entity.MessageEntity).\n"
                  + " Expected:\n" + _infoMessages + "\n"
                  + " Found:\n" + _existingMessages);
        }
        final HashMap<String, TableInfo.Column> _columnsPatientNotifications = new HashMap<String, TableInfo.Column>(4);
        _columnsPatientNotifications.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientNotifications.put("patient_id", new TableInfo.Column("patient_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientNotifications.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPatientNotifications.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPatientNotifications = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPatientNotifications = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPatientNotifications = new TableInfo("patient_notifications", _columnsPatientNotifications, _foreignKeysPatientNotifications, _indicesPatientNotifications);
        final TableInfo _existingPatientNotifications = TableInfo.read(db, "patient_notifications");
        if (!_infoPatientNotifications.equals(_existingPatientNotifications)) {
          return new RoomOpenHelper.ValidationResult(false, "patient_notifications(com.mhealth.app.data.local.entity.NotificationEntity).\n"
                  + " Expected:\n" + _infoPatientNotifications + "\n"
                  + " Found:\n" + _existingPatientNotifications);
        }
        final HashMap<String, TableInfo.Column> _columnsMedicalResults = new HashMap<String, TableInfo.Column>(6);
        _columnsMedicalResults.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicalResults.put("doctor_id", new TableInfo.Column("doctor_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicalResults.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicalResults.put("summary", new TableInfo.Column("summary", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicalResults.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedicalResults.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMedicalResults = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMedicalResults = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMedicalResults = new TableInfo("medical_results", _columnsMedicalResults, _foreignKeysMedicalResults, _indicesMedicalResults);
        final TableInfo _existingMedicalResults = TableInfo.read(db, "medical_results");
        if (!_infoMedicalResults.equals(_existingMedicalResults)) {
          return new RoomOpenHelper.ValidationResult(false, "medical_results(com.mhealth.app.data.local.entity.MedicalResultEntity).\n"
                  + " Expected:\n" + _infoMedicalResults + "\n"
                  + " Found:\n" + _existingMedicalResults);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(4);
        _columnsUsers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("display_name", new TableInfo.Column("display_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("role", new TableInfo.Column("role", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.mhealth.app.data.local.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsSystemAlerts = new HashMap<String, TableInfo.Column>(3);
        _columnsSystemAlerts.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSystemAlerts.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSystemAlerts.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSystemAlerts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSystemAlerts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSystemAlerts = new TableInfo("system_alerts", _columnsSystemAlerts, _foreignKeysSystemAlerts, _indicesSystemAlerts);
        final TableInfo _existingSystemAlerts = TableInfo.read(db, "system_alerts");
        if (!_infoSystemAlerts.equals(_existingSystemAlerts)) {
          return new RoomOpenHelper.ValidationResult(false, "system_alerts(com.mhealth.app.data.local.entity.SystemAlertEntity).\n"
                  + " Expected:\n" + _infoSystemAlerts + "\n"
                  + " Found:\n" + _existingSystemAlerts);
        }
        final HashMap<String, TableInfo.Column> _columnsAuditEntries = new HashMap<String, TableInfo.Column>(3);
        _columnsAuditEntries.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditEntries.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAuditEntries.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAuditEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAuditEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAuditEntries = new TableInfo("audit_entries", _columnsAuditEntries, _foreignKeysAuditEntries, _indicesAuditEntries);
        final TableInfo _existingAuditEntries = TableInfo.read(db, "audit_entries");
        if (!_infoAuditEntries.equals(_existingAuditEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "audit_entries(com.mhealth.app.data.local.entity.AuditEntryEntity).\n"
                  + " Expected:\n" + _infoAuditEntries + "\n"
                  + " Found:\n" + _existingAuditEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsSecretaryEscalations = new HashMap<String, TableInfo.Column>(4);
        _columnsSecretaryEscalations.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecretaryEscalations.put("secretary_id", new TableInfo.Column("secretary_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecretaryEscalations.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSecretaryEscalations.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSecretaryEscalations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSecretaryEscalations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSecretaryEscalations = new TableInfo("secretary_escalations", _columnsSecretaryEscalations, _foreignKeysSecretaryEscalations, _indicesSecretaryEscalations);
        final TableInfo _existingSecretaryEscalations = TableInfo.read(db, "secretary_escalations");
        if (!_infoSecretaryEscalations.equals(_existingSecretaryEscalations)) {
          return new RoomOpenHelper.ValidationResult(false, "secretary_escalations(com.mhealth.app.data.local.entity.SecretaryEscalationEntity).\n"
                  + " Expected:\n" + _infoSecretaryEscalations + "\n"
                  + " Found:\n" + _existingSecretaryEscalations);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "2b5f6897f09a1ec162f9ae2f9f0b1b1c", "dfb1fa94e51eaab557ac77414bffffe4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "patient_profiles","appointments","medications","messages","patient_notifications","medical_results","users","system_alerts","audit_entries","secretary_escalations");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `patient_profiles`");
      _db.execSQL("DELETE FROM `appointments`");
      _db.execSQL("DELETE FROM `medications`");
      _db.execSQL("DELETE FROM `messages`");
      _db.execSQL("DELETE FROM `patient_notifications`");
      _db.execSQL("DELETE FROM `medical_results`");
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `system_alerts`");
      _db.execSQL("DELETE FROM `audit_entries`");
      _db.execSQL("DELETE FROM `secretary_escalations`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CareDao.class, CareDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CareDao careDao() {
    if (_careDao != null) {
      return _careDao;
    } else {
      synchronized(this) {
        if(_careDao == null) {
          _careDao = new CareDao_Impl(this);
        }
        return _careDao;
      }
    }
  }
}
