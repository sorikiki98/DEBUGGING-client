package com.example.application.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("id")
    private final int id;

    @SerializedName("username")
    private final String userName;

    @SerializedName("password")
    private final String password;

    @SerializedName("name")
    private final String name;

    @SerializedName("contactNumbers")
    private final String contactNumbers;

    @SerializedName("email")
    private final String email;

    @SerializedName("address")
    private final String address;

    @SerializedName("sizeOfHouse")
    private final Double sizeOfHouse;

    @SerializedName("numOfRooms")
    private final int numOfRooms;

    @SerializedName("accumulatedNumOfUsages")
    private final int accumulatedNumOfUsages;

    @SerializedName("numOfInterestedCompanies")
    private final int numOfInterestedCompanies;

    @SerializedName("surveyList")
    private final List<MySurvey> surveyList;

    @SerializedName("productList")
    private final List<MyProduct> productList;

    @SerializedName("reservationList")
    private final List<MyReservation> reservationList;

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

    public int getAccumulatedNumOfUsages() {
        return this.accumulatedNumOfUsages;
    }

}
