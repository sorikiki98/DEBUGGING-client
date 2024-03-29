package com.example.application.mypage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.application.LinearLayoutSpacingDecoration;
import com.example.application.R;
import com.example.application.data.MyReservation;
import com.example.application.databinding.FragmentMypageCompanyDetailListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MyPageCompanyDetailListFragment extends Fragment implements MyPageCompanyDetailListContract.View {
    @Inject
    MyPageCompanyDetailListContract.Presenter presenter;

    private FragmentMypageCompanyDetailListBinding binding;

    private MyPageCompanyDetailListAdapter adapter = new MyPageCompanyDetailListAdapter(new MyReservationDiffCallback(), (reservationId) -> {
        navigate(reservationId);
        return null;
    });

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMypageCompanyDetailListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        bindViews();
    }

    private void initViews() {
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new LinearLayoutSpacingDecoration());

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);

        presenter.subscribe();
    }

    private void bindViews() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshReservedCompanies());
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("reservationId", id);
        navController.navigate(R.id.action_myPageCompanyDetailListFragment_to_myPageCompanyDetailItemFragment, bundle);
    }

    @Override
    public void showReservedCompanies(List<MyReservation> reservation) {
        binding.recyclerView.setVisibility(View.VISIBLE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        adapter.submitList(reservation);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUserName(String userName) {
        binding.tvUserName.setText(userName);
    }

    @Override
    public void showErrorMessage(String message) {
        binding.recyclerView.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void undoRefreshLoading() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}