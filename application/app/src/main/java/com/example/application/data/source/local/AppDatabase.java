package com.example.application.data.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.application.data.Bug;
import com.example.application.data.Company;
import com.example.application.data.Product;

@Database(entities = {Bug.class, Company.class, Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BugDao bugsDao();
    public abstract CompanyDao companyDao();
    public abstract ProductDao productDao();
}
