package com.example.application.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("id")
    public final int id;

    @SerializedName("userName")
    public final String userName;

    @SerializedName("password")
    public final String password;

    @SerializedName("name")
    public final String name;

    @SerializedName("contactNumbers")
    public final String contactNumbers;

    @SerializedName("email")
    public final String email;

    @SerializedName("address")
    public final String address;

    @SerializedName("sizeOfHouse")
    public final Double sizeOfHouse;

    @SerializedName("numOfRooms")
    public final int numOfRooms;

    @SerializedName("accumulatedNumOfUsages")
    public final int accumulatedNumOfUsages;

    @SerializedName("numOfInterestedCompanies")
    public final int numOfInterestedCompanies;

    @SerializedName("surveyList")
    public final List<MySurvey> surveyList;

    @SerializedName("productList")
    public final List<MyProduct> productList;

    @SerializedName("reservationList")
    public final List<MyReservation> reservationList;

    public User(
            int id,
            String userName,
            String password,
            String name,
            String contactNumbers,
            String email,
            String address,
            Double sizeOfHouse,
            int numOfRooms,
            int accumulatedNumOfUsages,
            int numOfInterestedCompanies,
            List<MySurvey> surveyList,
            List<MyProduct> productList,
            List<MyReservation> reservationList
    ) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.contactNumbers = contactNumbers;
        this.email = email;
        this.address = address;
        this.sizeOfHouse = sizeOfHouse;
        this.numOfRooms = numOfRooms;
        this.accumulatedNumOfUsages = accumulatedNumOfUsages;
        this.numOfInterestedCompanies = numOfInterestedCompanies;
        this.surveyList = surveyList;
        this.productList = productList;
        this.reservationList = reservationList;
    }
}
