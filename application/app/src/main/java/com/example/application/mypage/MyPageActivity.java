package com.example.application.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.application.R;
import com.example.application.databinding.ActivityMypageBinding;
import com.example.application.home.HomeActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class MyPageActivity extends AppCompatActivity implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Inject
    Context context;

    private ActivityMypageBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = ActivityMypageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        bindViews();
    }

    private void initViews() {
        setNavController();
    }

    private void bindViews() {
        binding.toolBarHomeIcon.setOnClickListener(view -> {
            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setNavController() {
        Toolbar toolBar = binding.toolBar;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(navController.getGraph())
                .setFallbackOnNavigateUpListener(this::onSupportNavigateUp)
                .build();
        NavigationUI.setupWithNavController(toolBar, navController, configuration);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
