package com.example.application.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.application.R;
import com.example.application.bug.BugsActivity;
import com.example.application.data.source.remote.BugsService;
import com.example.application.data.source.remote.UsersService;
import com.example.application.PreferencesManager;
import com.example.application.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class HomeActivity extends AppCompatActivity implements HasAndroidInjector, HomeContract.View {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    private ActivityHomeBinding binding;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        bindViews();
    }

    private void initViews() {
        setNavController();
        setNavigationDrawer();
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

    private void setNavigationDrawer() {
        NavigationView navView = binding.navView;
        View header = navView.inflateHeaderView(R.layout.main_menu_header);
        ((TextView) header.findViewById(R.id.tv_profile_name)).setText("김다솔 님");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(navView.getLayoutParams().width, ViewGroup.LayoutParams.WRAP_CONTENT);
        binding.navView.getHeaderView(0).setLayoutParams(params);
    }

    private void bindViews() {
        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case (R.id.bugActivity):
                    Intent intent = new Intent(context, BugsActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public void navigate(int id) {

    }

    @Override
    public void showUserName(String userName) {

    }

    @Override
    public void showAccumulatedNumberOfUsages(String total) {

    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}