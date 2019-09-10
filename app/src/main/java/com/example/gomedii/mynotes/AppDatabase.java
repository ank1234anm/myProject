package com.example.gomedii.mynotes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by GOMEDII on 3/22/2018.
 */
@Database(entities = {User.class},version=1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UserDao userDao();
}
