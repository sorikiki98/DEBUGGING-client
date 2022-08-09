package com.example.application.data.source;

import com.example.application.data.RegistrationForm;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.UserLogIn;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

public interface UserDataSource {
    Maybe<UserAuthentication> login(UserLogIn userInput);

    Maybe<UserAuthentication> signup(RegistrationForm registrationForm);

    Completable delete();

    Maybe<User> getUserInformation();
}
