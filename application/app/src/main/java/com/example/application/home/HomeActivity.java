package com.example.application.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.application.R;
import com.example.application.bug.BugActivity;
import com.example.application.company.CompanyActivity;
import com.example.application.databinding.ActivityHomeBinding;
import com.example.application.login.LoginActivity;
import com.example.application.mypage.MyPageActivity;
import com.example.application.product.ProductActivity;
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
    HomeContract.Presenter presenter;

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
        setUserInfo();
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

    private void setUserInfo() {
        presenter.setUserName();
        presenter.setAccumulatedNumberOfUsages();
    }

    private void bindViews() {
        binding.navView.setNavigationItemSelectedListener(this::handleMenuItemClick);
        binding.tvLogOut.setOnClickListener((view) -> popUpLogOutWindow());
        binding.tvSignOut.setOnClickListener((view) -> presenter.signOut());
        binding.myPageIcon.setOnClickListener((view) -> startActivity(new Intent(this, MyPageActivity.class)));
    }

    @Override
    public void navigate(int id) {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUserName(String userName) {
        TextView userNameTextView = binding.navView.getHeaderView(0).findViewById(R.id.tv_profile_name);
        userNameTextView.setText(userName + " 님");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showAccumulatedNumberOfUsages(int total) {
        TextView usagesTextView = binding.navView.getHeaderView(0).findViewById(R.id.tv_num_of_acccumulated_usages);
        usagesTextView.setText("누적 업체 이용 " + total + "건");
    }

    private boolean handleMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.bugActivity):
                startActivity(new Intent(this, BugActivity.class));
                finish();
                return true;
            case (R.id.companyActivity):
                startActivity(new Intent(this, CompanyActivity.class));
                finish();
                return true;
            case (R.id.productActivity):
                startActivity(new Intent(this, ProductActivity.class));
                finish();
                return true;
            case (R.id.myPageActivity):
                startActivity(new Intent(this, MyPageActivity.class));
                return true;
            default:
                return false;
        }
    }

    private void popUpLogOutWindow() {
        binding.navView.setVisibility(View.INVISIBLE);
        if (!HomeActivity.this.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("로그아웃")
                    .setMessage("로그아웃 하시겠습니까?")
                    .setPositiveButton("네", (dialogInterface, i) -> presenter.logout())
                    .setNegativeButton("아니오", (dialogInterface, i) -> {
                        binding.navView.setVisibility(View.VISIBLE);
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void goBackToHomeScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}