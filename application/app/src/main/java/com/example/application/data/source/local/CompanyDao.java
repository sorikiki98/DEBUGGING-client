package com.example.application.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.application.data.Bug;
import com.example.application.data.Company;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCompanies(List<Company> companies);

    @Query("SELECT * FROM companies")
    public Flowable<List<Company>> getCompanies();

    @Query("SELECT * FROM companies WHERE id=:companyId")
    public Flowable<Optional<Company>> getCompany(int companyId);

    @Query("UPDATE companies SET isCompanyInterested=1, numOfInterestedUsers=numOfInterestedUsers+1 WHERE id=:companyId")
    public Completable addCompanyInterest(int companyId);

    @Query("UPDATE companies SET isCompanyInterested=0, numOfInterestedUsers=numOfInterestedUsers-1 WHERE id=:companyId")
    public Completable removeCompanyInterest(int companyId);
}
