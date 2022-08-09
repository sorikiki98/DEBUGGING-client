package com.example.application.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.application.data.UserLogIn;
import com.example.application.databinding.ActivityLoginBinding;
import com.example.application.home.HomeActivity;
import com.example.application.register.RegisterActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    @Inject
    LoginContract.Presenter presenter;

    @Inject
    Context context;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bindViews();
    }

    private void bindViews() {
        binding.btnLogin.setOnClickListener(view -> {
            String userName = binding.loginId.getText().toString();
            String password = binding.loginPw.getText().toString();

            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show();
            } else presenter.login(new UserLogIn(userName, password));
        });

        binding.btnRegister.setOnClickListener(view -> startActivity(new Intent(context, RegisterActivity.class)));
    }

    @Override
    public void processLoginSuccess() {
        navigate(0);
    }

    @Override
    public void processLoginFail() {
        Toast.makeText(context, "잘못된 정보를 입력했습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigate(int id) {
        Intent intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        presenter.unsubscribe();
    }
}
