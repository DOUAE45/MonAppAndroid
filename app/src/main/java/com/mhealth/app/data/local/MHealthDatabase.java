package com.mhealth.app.data.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

@Database(
        entities = {
                PatientProfileEntity.class,
                AppointmentEntity.class,
                MedicationEntity.class,
                MessageEntity.class,
                NotificationEntity.class,
                MedicalResultEntity.class,
                UserEntity.class,
                SystemAlertEntity.class,
                AuditEntryEntity.class,
                SecretaryEscalationEntity.class
        },
        version = 1,
        exportSchema = false
)
public abstract class MHealthDatabase extends RoomDatabase {

    public abstract CareDao careDao();

    public static MHealthDatabase build(@NonNull Context context) {
        return Room.databaseBuilder(context, MHealthDatabase.class, "mhealth.db")
                .fallbackToDestructiveMigration()
                .build();
    }
}

