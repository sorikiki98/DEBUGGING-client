package com.example.application.data;

import android.content.Context;
import android.util.TypedValue;

import com.example.application.R;

import java.util.Objects;

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

    public int convertToDrawable() {
        switch (this.bugId) {
            case (1):
                return R.drawable.ic_silverfish;
            case (2):
                return R.drawable.ic_dust;
            case (3):
                return R.drawable.ic_cockroach;
            case (4):
                return R.drawable.ic_butterfly;
            case (5):
                return R.drawable.ic_rice;
            case (6):
                return R.drawable.ic_greema;
            case (7):
                return R.drawable.ic_bedbug;
            default:
                return R.drawable.ic_mosquito;
        }
    }

    public int convertToPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getDrawableDp(this.bugId), context.getResources().getDisplayMetrics());
    }

    private float getDrawableDp(int bugId) {
        switch (bugId) {
            case (1):
                return 10F;
            case (2):
            case (5):
                return 12F;
            case (3):
            case (4):
            case (7):
                return 16F;
            default:
                return 14F;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySurvey mySurvey = (MySurvey) o;
        return surveyId == mySurvey.surveyId && userId == mySurvey.userId && bugId == mySurvey.bugId && Objects.equals(surveyDate, mySurvey.surveyDate) && Objects.equals(bugName, mySurvey.bugName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveyId, userId, surveyDate, bugId, bugName);
    }
}
