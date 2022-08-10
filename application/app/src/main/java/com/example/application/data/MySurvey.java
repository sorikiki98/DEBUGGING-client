package com.example.application.data;
public class MySurvey {
    public final int surveyId;

    public final int userId;

    public final String surveyDate;

    public final int bugId;

    public final String bugName;

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
