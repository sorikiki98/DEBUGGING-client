package com.example.application.data.source.repository;

import com.example.application.data.MyProduct;
import com.example.application.data.MyReservation;
import com.example.application.data.MySurvey;
import com.example.application.data.RegistrationForm;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.UserLogIn;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

public interface UserRepository {
    Maybe<UserAuthentication> login(UserLogIn userInput);

    Maybe<UserAuthentication> signup(RegistrationForm registrationForm);

    Maybe<User> loadUserInformation();

    Completable delete();

    Completable logout();

    void refreshMyPage();

    void refreshCache(User userInfo);

    String getUserName();

    Maybe<Integer> getAccumulatedNumOfUsages();

    Maybe<List<MySurvey>> getMySurveyList();

    Maybe<List<MyReservation>> getMyReservationList();

    Maybe<List<MyProduct>> getMyProductList();

    String getAuthToken();
}
