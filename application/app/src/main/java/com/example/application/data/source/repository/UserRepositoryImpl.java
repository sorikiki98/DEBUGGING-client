package com.example.application.data.source.repository;

import com.example.application.PreferencesManager;
import com.example.application.data.MyProduct;
import com.example.application.data.MyReservation;
import com.example.application.data.MySurvey;
import com.example.application.data.RegistrationForm;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.UserLogIn;
import com.example.application.data.source.remote.UserRemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

public class UserRepositoryImpl implements UserRepository {
    private User cachedUserInfo = null;

    private final UserRemoteDataSource userRemoteDataSource;

    private final PreferencesManager preferencesManager;

    @Inject
    UserRepositoryImpl(UserRemoteDataSource userRemoteDataSource, PreferencesManager preferencesManager) {
        this.userRemoteDataSource = userRemoteDataSource;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Maybe<UserAuthentication> login(UserLogIn userInput) {
        return setUserAuthentication(userRemoteDataSource.login(userInput));
    }

    @Override
    public Maybe<UserAuthentication> signup(RegistrationForm registrationForm) {
        return setUserAuthentication(userRemoteDataSource.signup(registrationForm));
    }

    private Maybe<UserAuthentication> setUserAuthentication(Maybe<UserAuthentication> authentication) {
        return authentication
                .flatMap((UserAuthentication userAuthentication) -> {
                    preferencesManager.clearAuthToken();
                    preferencesManager.saveUserName(userAuthentication.getUserName());
                    preferencesManager.saveAuthToken(userAuthentication.getToken());
                    return Maybe.just(userAuthentication);
                });
    }

    @Override
    public Maybe<User> loadUserInformation(boolean isFirstLoad) {
        if (cachedUserInfo != null && isFirstLoad) {
            return Maybe.just(cachedUserInfo);
        }

        return userRemoteDataSource.getUserInformation()
                .flatMap((User userInfo) -> {
                    refreshCache(userInfo);
                    return Maybe.just(userInfo);
                });
    }

    @Override
    public Completable delete() {
        return userRemoteDataSource.delete()
                .doOnComplete(this::clearUserInfo);
    }

    @Override
    public Completable logout() {
        return Completable.complete()
                .doOnComplete(this::clearUserInfo);
    }

    private void clearUserInfo() {
        preferencesManager.clearAuthToken();
        cachedUserInfo = null;
    }

    @Override
    public void refreshCache(User userInfo) {
        cachedUserInfo = userInfo;
    }

    @Override
    public String getUserName() {
        return preferencesManager.getUserName();
    }

    @Override
    public Maybe<Integer> getAccumulatedNumOfUsages(boolean isFirstLoad) {
        return loadUserInformation(isFirstLoad).flatMap((User user) -> Maybe.just(user.accumulatedNumOfUsages));
    }

    @Override
    public Maybe<List<MySurvey>> getMySurveyList(boolean isFirstLoad) {
        return loadUserInformation(isFirstLoad).flatMap((User user) -> Maybe.just(user.surveyList));
    }

    @Override
    public Maybe<List<MyReservation>> getMyReservationList(boolean isFirstLoad) {
        return loadUserInformation(isFirstLoad).flatMap((User user) -> Maybe.just(user.reservationList));
    }

    @Override
    public Maybe<List<MyProduct>> getMyProductList(boolean isFirstLoad) {
        return loadUserInformation(isFirstLoad).flatMap((User user) -> Maybe.just(user.productList));
    }
}
