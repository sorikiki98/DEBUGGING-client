package com.example.application.data.source.repository;

import android.content.SharedPreferences;

import com.example.application.PreferencesManager;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.remote.UserLogIn;
import com.example.application.data.source.remote.UserRemoteDataSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

public class UserRepositoryImpl implements UserRepository {
    private User cachedUserInfo = null;
    private boolean isCacheDirty = false;

    private final UserRemoteDataSource userRemoteDataSource;

    private final PreferencesManager preferencesManager;

    @Inject
    UserRepositoryImpl(UserRemoteDataSource userRemoteDataSource, PreferencesManager preferencesManager) {
        this.userRemoteDataSource = userRemoteDataSource;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Maybe<UserAuthentication> login(UserLogIn userInput) {
        return userRemoteDataSource.login(userInput)
                .flatMap((UserAuthentication userAuthentication) -> {
                    preferencesManager.saveUserName(userAuthentication.getUserName());
                    preferencesManager.saveAuthToken(userAuthentication.getToken());
                    return Maybe.just(userAuthentication);
                });
    }

    @Override
    public Maybe<User> loadUserInformation() {
        if (cachedUserInfo != null && !isCacheDirty) {
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
    public void logout() {
        clearUserInfo();
    }

    private void clearUserInfo() {
        preferencesManager.clearAuthToken();
        cachedUserInfo = null;
    }

    @Override
    public void refreshMyPage() {
        isCacheDirty = true;
    }

    @Override
    public void refreshCache(User userInfo) {
        cachedUserInfo = userInfo;
    }
}