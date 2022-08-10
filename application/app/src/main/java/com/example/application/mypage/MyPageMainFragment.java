package com.example.application.mypage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.application.R;
import com.example.application.data.MyProduct;
import com.example.application.data.MySurvey;
import com.example.application.data.User;
import com.example.application.databinding.FragmentMypageMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MyPageMainFragment extends Fragment implements MyPageMainContract.View {
    @Inject
    MyPageMainContract.Presenter presenter;

    private FragmentMypageMainBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMypageMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.subscribe();
        initViews();
        bindViews();
    }

    private void initViews() {
        binding.contentContainer.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.surveyContainer.setVisibility(View.GONE);
        binding.bugContainer1.setVisibility(View.GONE);
        binding.bugContainer2.setVisibility(View.GONE);
        binding.productContainer.setVisibility(View.GONE);
        binding.productContainer1.setVisibility(View.GONE);
        binding.productContainer2.setVisibility(View.GONE);
        binding.reservationContainer.setVisibility(View.GONE);
        binding.companyContainer1.setVisibility(View.GONE);
        binding.companyContainer2.setVisibility(View.GONE);
    }

    private void bindViews() {
        binding.tvMoreCompany.setOnClickListener(view -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_myPageMainFragment_to_myPageCompanyDetailListFragment);
        });
    }

    @Override
    public void navigate(int id) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUserInfo(User user) {
        binding.contentContainer.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.GONE);

        binding.tvName.setText(user.userName + " 님");
        binding.tvUsecompany.setText("누적 업체 이용 " + user.accumulatedNumOfUsages + "건");
        binding.tvAddress.setText(user.address);
        binding.tvArea.setText(user.sizeOfHouse + "평");
        binding.tvCompany.setText(user.numOfInterestedCompanies + "개");

        user.surveyList.sort((s1, s2) -> s2.surveyId - s1.surveyId);
        if (user.surveyList.get(0) != null) {
            binding.surveyContainer.setVisibility(View.GONE);
            binding.bugContainer1.setVisibility(View.VISIBLE);
            binding.tvBugName1.setText(user.surveyList.get(0).bugName);
            binding.tvDate1.setText(user.surveyList.get(0).surveyDate);
        }
        if (user.surveyList.get(1) != null) {
            binding.bugContainer2.setVisibility(View.VISIBLE);
            binding.tvBugName2.setText(user.surveyList.get(1).bugName);
            binding.tvDate2.setText(user.surveyList.get(1).surveyDate);
        }


        user.productList.sort((p1, p2) -> p2.productInterestId - p1.productInterestId);
        if (user.productList.get(0) != null) {
            binding.productContainer.setVisibility(View.VISIBLE);
            binding.productContainer1.setVisibility(View.VISIBLE);
            binding.tvProductName1.setText(user.productList.get(0).productName);
        }
        if (user.productList.get(1) != null) {
            binding.productContainer2.setVisibility(View.VISIBLE);
            binding.tvProductName2.setText(user.productList.get(1).productName);
        }

        user.reservationList.sort((r1, r2) -> r2.reservationId - r1.reservationId);
        if (user.reservationList.size() >= 1) {
            binding.reservationContainer.setVisibility(View.VISIBLE);
            binding.companyContainer1.setVisibility(View.VISIBLE);
            binding.tvCompanyName1.setText(user.reservationList.get(0).companyName);
            binding.tvProcessState1.setText(user.reservationList.get(0).getProcessState());
        }
        if (user.reservationList.size() >= 2) {
            binding.companyContainer2.setVisibility(View.VISIBLE);
            binding.tvCompanyName2.setText(user.reservationList.get(1).companyName);
            binding.tvProcessState2.setText(user.reservationList.get(1).getProcessState());
        }
    }

    @Override
    public void showErrorMessage(String message) {
        binding.contentContainer.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.VISIBLE);

    }
}
