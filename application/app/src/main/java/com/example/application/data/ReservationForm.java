package com.example.application.data;

import com.google.gson.annotations.SerializedName;

public class ReservationForm {
    @SerializedName("bugName")
    public final String bugName;

    @SerializedName("firstFoundDate")
    public final String firstFoundDate;

    @SerializedName("firstFoundPlace")
    public final String firstFoundPlace;

    @SerializedName("wantedDate")
    public final String wantedDate;

    @SerializedName("wantedTime")
    public final String wantedTime;

    @SerializedName("hasBugBeenShown")
    public final int hasBugBeenShown;

    @SerializedName("reservationDateTime")
    public final String reservationDateTime;

    @SerializedName("extraMessage")
    public final String extraMessage;

    public ReservationForm(
            String bugName,
            String firstFoundDate,
            String firstFoundPlace,
            String wantedDate,
            String wantedTime,
            int hasBugBeenShown,
            String reservationDateTime,
            String extraMessage
    ) {
        this.bugName = bugName;
        this.firstFoundDate = firstFoundDate;
        this.firstFoundPlace = firstFoundPlace;
        this.wantedDate = wantedDate;
        this.wantedTime = wantedTime;
        this.hasBugBeenShown = hasBugBeenShown;
        this.reservationDateTime = reservationDateTime;
        this.extraMessage = extraMessage;
    }
}
