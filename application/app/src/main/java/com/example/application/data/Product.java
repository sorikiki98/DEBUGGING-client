package com.example.application.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "products")
public class Product {
    @PrimaryKey
    @SerializedName("id")
    public final int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    public final String name;

    @ColumnInfo(name = "type")
    @SerializedName("type")
    public final String type;

    @ColumnInfo(name = "shortIntro")
    @SerializedName("shortIntro")
    public final String shortIntro;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    public final String description;

    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    public final String thumbnail;

    @ColumnInfo(name = "isProductInterested")
    @SerializedName("isProductInterested")
    public final int isProductInterested;

    @ColumnInfo(name = "numOfInterestedUsers")
    @SerializedName("numOfInterestedUsers")
    public final int numOfInterestedUsers;

    public Product(int id, String name, String type, String shortIntro, String description, String thumbnail, int isProductInterested, int numOfInterestedUsers) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.shortIntro = shortIntro;
        this.description = description;
        this.thumbnail = thumbnail;
        this.isProductInterested = isProductInterested;
        this.numOfInterestedUsers = numOfInterestedUsers;
    }
}
