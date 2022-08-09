package com.example.application.data;

import com.google.gson.annotations.SerializedName;

public class RegistrationForm {
    @SerializedName("userName")
    public final String username;

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

    public RegistrationForm(
            String username,
            String password,
            String name,
            String contactNumbers,
            String email,
            String address,
            Double sizeOfHouse,
            int numOfRooms
    ) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactNumbers = contactNumbers;
        this.email = email;
        this.address = address;
        this.sizeOfHouse = sizeOfHouse;
        this.numOfRooms = numOfRooms;
    }
}
