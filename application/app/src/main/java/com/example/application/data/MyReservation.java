package com.example.application.data;

public class MyReservation {
    private final int reservationId;

    private final int userId;

    private final int processState;

    private final String companyName;

    MyReservation(
            int reservationId,
            int userId,
            int processState,
            String companyName
    ) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.processState = processState;
        this.companyName = companyName;
    }
}
