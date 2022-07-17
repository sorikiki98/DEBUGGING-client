package com.example.application.data.response;

public final class Bug {
    private final int id;
    private final String name;
    private final String appearance;
    private final String movement;
    private final String habitat;
    private final String color;
    private final String surveyResult;

    public Bug(
            int id,
            String name,
            String appearance,
            String movement,
            String habitat,
            String color,
            String surveyResult) {
        this.id = id;
        this.name = name;
        this.appearance = appearance;
        this.movement = movement;
        this.habitat = habitat;
        this.color = color;
        this.surveyResult = surveyResult;
    }

}
