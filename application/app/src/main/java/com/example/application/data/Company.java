package com.example.application.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "companies")
public class Company {
    @SerializedName("id")
    @PrimaryKey
    public final int id;
    
    @SerializedName("name")
    @ColumnInfo(name =  "name")
    public  final String name;

    @SerializedName("shortIntro")
    @ColumnInfo(name = "shortIntro")
    public final String shortIntro;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    public  final String description;

    @SerializedName("contactNumbers")
    @ColumnInfo(name = "contactNumbers")
    public final String contactNumbers;

    @SerializedName("killableBugs")
    @ColumnInfo(name = "killableBugs")
    public final String killableBugs;

    @SerializedName("availableArea")
    @ColumnInfo(name = "availableArea")
    public final String availableArea;

    @SerializedName("availableCounselTime")
    @ColumnInfo(name = "availableCounselTime")
    public final String availableCounselTime;

    @SerializedName("thumbnail")
    @ColumnInfo(name = "thumbnail")
    public final String thumbnail;

    @SerializedName("isCompanyInterested")
    @ColumnInfo(name = "isCompanyInterested")
    public final int isCompanyInterested;

    @SerializedName("numOfInterestedUsers")
    @ColumnInfo(name = "numOfInterestedUsers")
    public final int numOfInterestedUsers;

    public Company(
            int id,
            String name,
            String shortIntro,
            String description,
            String contactNumbers,
            String killableBugs,
            String availableArea,
            String availableCounselTime,
            String thumbnail,
            int isCompanyInterested,
            int numOfInterestedUsers
    ) {
        this.id = id;
        this.name = name;
        this.shortIntro = shortIntro;
        this.description = description;
        this.contactNumbers = contactNumbers;
        this.killableBugs = killableBugs;
        this.availableArea = availableArea;
        this.availableCounselTime = availableCounselTime;
        this.thumbnail = thumbnail;
        this.isCompanyInterested = isCompanyInterested;
        this.numOfInterestedUsers = numOfInterestedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && numOfInterestedUsers == company.numOfInterestedUsers && Objects.equals(name, company.name) && Objects.equals(shortIntro, company.shortIntro) && Objects.equals(description, company.description) && Objects.equals(contactNumbers, company.contactNumbers) && Objects.equals(killableBugs, company.killableBugs) && Objects.equals(availableArea, company.availableArea) && Objects.equals(availableCounselTime, company.availableCounselTime) && Objects.equals(thumbnail, company.thumbnail) && Objects.equals(isCompanyInterested, company.isCompanyInterested);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortIntro, description, contactNumbers, killableBugs, availableArea, availableCounselTime, thumbnail, isCompanyInterested, numOfInterestedUsers);
    }
}
