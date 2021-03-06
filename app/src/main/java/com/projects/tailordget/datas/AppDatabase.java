package com.projects.tailordget.datas;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.projects.tailordget.interfaces.OrderDAO;
import com.projects.tailordget.interfaces.ProfileDao;
import com.projects.tailordget.interfaces.StatusDAO;
import com.projects.tailordget.interfaces.TypeDAO;
import com.projects.tailordget.utilities.DateConverter;

@Database(entities = {Profile.class, Order.class, Type.class, Status.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "tailordgetDB";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        // Queries should be done in a separate thread to avoid locking the UI
                        // We will allow this ONLY TEMPORALLY to see that our DB is working
//                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract ProfileDao profileDao();
    public abstract OrderDAO orderDao();
    public abstract TypeDAO typeDao();
    public abstract StatusDAO statusDao();
}
