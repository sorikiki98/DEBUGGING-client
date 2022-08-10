package com.example.application.data;

public class MyReservation {
    public final int reservationId;

    public final int userId;

    public final int processState;

    public final String companyName;

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
}
