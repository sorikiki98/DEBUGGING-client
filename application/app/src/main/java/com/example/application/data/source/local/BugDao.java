package com.example.application.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.application.data.Bug;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface BugDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBugs(List<Bug> bugs);

    @Query("SELECT * FROM bugs")
    public Flowable<List<Bug>> getBugs();

    @Query("SELECT * FROM bugs WHERE id = :bugId")
    public Flowable<Optional<Bug>> getBug(int bugId);
}