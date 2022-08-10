package com.example.application.data;

import com.google.gson.annotations.SerializedName;

public class Reservation {
    @SerializedName("id") //
    public final int reservationId;

    @SerializedName("userId") //
    public final int userId;

    @SerializedName("companyId") //
    public final int companyId;

    @SerializedName("bugName") //
    public final String bugName;

    @SerializedName("firstFoundDate") //
    public final String firstFoundDate;

    @SerializedName("firstFoundPlace") //
    public final String firstFoundPlace;

    @SerializedName("wantedDate") //
    public final String wantedDate;

    @SerializedName("wantedTime") //
    public final String wantedTime;

    @SerializedName("hasBugBeenShown") //
    public final int hasBugBeenShown;

    @SerializedName("reservationDateTime") //
    public final String reservationDateTime;

    @SerializedName("extraMessage") //
    public final String extraMessage;

    @SerializedName("processState") //
    public final int processState;

    @SerializedName("engineerName") //
    public final String engineerName;

    @SerializedName("engineerContactNumbers") //
    public final String engineerContactNumbers;

    @SerializedName("expectedEstimate") //
    public final String expectedEstimate;

    @SerializedName("visitDateTime") //
    public final String visitDateTime;

    @SerializedName("userName") //
    public final String userName;

    @SerializedName("userContactNumbers") //
    public final String userContactNumbers;

    @SerializedName("userEmail") //
    public final String userEmail;

    @SerializedName("userAddress") //
    public final String userAddress;

    @SerializedName("sizeOfHouse") //
    public final Double sizeOfHouse;

    @SerializedName("numOfRooms") //
    public final int numOfRooms;

    @SerializedName("companyName") //
    public final String companyName;

    @SerializedName("shortIntro") //
    public final String shortIntro;

    @SerializedName("description") //
    public final String description;

    @SerializedName("companyContactNumbers") //
    public final String companyContactNumbers;

    @SerializedName("killableBugs") //
    public final String killableBugs;

    @SerializedName("availableArea") //
    public final String availableArea;

    @SerializedName("availableCounselTime") //
    public final String availableCounselTime;

    @SerializedName("thumbnail") //
    public final String thumbnail;

    public Reservation(
            int reservationId,
            int userId,
            int companyId,
            String bugName,
            String firstFoundDate,
            String firstFoundPlace,
            String wantedDate,
            String wantedTime,
            int hasBugBeenShown,
            String reservationDateTime,
            String extraMessage,
            int processState,
            String engineerName,
            String engineerContactNumbers,
            String expectedEstimate,
            String visitDateTime,
            String userName,
            String userContactNumbers,
            String userEmail,
            String userAddress,
            Double sizeOfHouse,
            int numOfRooms,
            String companyName,
            String shortIntro,
            String description,
            String companyContactNumbers,
            String killableBugs,
            String availableArea,
            String availableCounselTime,
            String thumbnail
    ) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.companyId = companyId;
        this.bugName = bugName;
        this.firstFoundDate = firstFoundDate;
        this.firstFoundPlace = firstFoundPlace;
        this.wantedDate = wantedDate;
        this.wantedTime = wantedTime;
        this.hasBugBeenShown = hasBugBeenShown;
        this.reservationDateTime = reservationDateTime;
        this.extraMessage = extraMessage;
        this.processState = processState;
        this.engineerName = engineerName;
        this.engineerContactNumbers = engineerContactNumbers;
        this.expectedEstimate = expectedEstimate;
        this.visitDateTime = visitDateTime;
        this.userName = userName;
        this.userContactNumbers = userContactNumbers;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.sizeOfHouse = sizeOfHouse;
        this.numOfRooms = numOfRooms;
        this.companyName = companyName;
        this.shortIntro = shortIntro;
        this.description = description;
        this.companyContactNumbers = companyContactNumbers;
        this.killableBugs = killableBugs;
        this.availableArea = availableArea;
        this.availableCounselTime = availableCounselTime;
        this.thumbnail = thumbnail;
    }


    public String getProcessState() {
        switch (this.processState) {
            case (3):
                return "케어 종료";
            case (2):
                return "방문예약 접수완료";
            case (1):
                return "업체 확인중";
            default:
                return "사용자 예약접수";
        }
    }

    public String getHasBugBeenShown() {
        if (this.hasBugBeenShown == 1) {
            return "있음";
        }
        else return "없음";
    }
}
