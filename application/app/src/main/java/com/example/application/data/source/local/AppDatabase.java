package com.example.application.data.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.application.data.Bug;

@Database(entities = {Bug.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BugsDao bugsDao();
}
