package com.example.application.data;
public class MySurvey {
    private final int surveyId;

    private final int userId;

    private final String surveyDate;

    private final int bugId;

    private final String bugName;

    public MySurvey(
            int surveyId,
            int userId,
            String surveyDate,
            int bugId,
            String bugName
    ) {
        this.surveyId = surveyId;
        this.userId = userId;
        this.surveyDate = surveyDate;
        this.bugId = bugId;
        this.bugName = bugName;
    }

}
