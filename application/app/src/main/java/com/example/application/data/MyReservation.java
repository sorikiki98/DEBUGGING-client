package com.example.application.data;

import java.util.Objects;

public class MyReservation {
    public final int reservationId;

    public final int userId;

    public final int processState;

    public final String companyName;

    public final String bugName;

    public final String reservationDateTime;

    public final String visitDateTime;

    MyReservation(
            int reservationId,
            int userId,
            int processState,
            String bugName,
            String companyName,
            String reservationDateTime,
            String visitDateTime
    ) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.processState = processState;
        this.bugName = bugName;
        this.companyName = companyName;
        this.reservationDateTime = reservationDateTime;
        this.visitDateTime = visitDateTime;
    }

    public String getProcessState() {
        switch(this.processState) {
            case(3):
                return "케어 종료";
            case(2):
                return "방문예약 접수완료";
            case(1):
                return "업체 확인중";
            default:
                return "사용자 예약접수";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyReservation that = (MyReservation) o;
        return reservationId == that.reservationId && userId == that.userId && processState == that.processState && Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, userId, processState, companyName);
    }
}