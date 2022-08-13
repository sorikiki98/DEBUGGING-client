package com.example.application.mypage;

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

import com.example.application.GridLayoutSpacingDecoration;
import com.example.application.R;
import com.example.application.data.MySurvey;
import com.example.application.databinding.FragmentMypageSurveyDetailListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MyPageSurveyDetailListFragment extends Fragment implements MyPageSurveyDetailListContract.View {
    @Inject
    MyPageSurveyDetailListContract.Presenter presenter;

    private FragmentMypageSurveyDetailListBinding binding;

    private final MyPageSurveyDetailListAdapter adapter = new MyPageSurveyDetailListAdapter(new MySurveyDiffCallback(), (bugId) -> {
        navigate(bugId);
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
        binding = FragmentMypageSurveyDetailListBinding.inflate(getLayoutInflater());
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
        binding.recyclerView.addItemDecoration(new GridLayoutSpacingDecoration());
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);

        presenter.subscribe();
    }

    private void bindViews() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshMyPageSurveyList());
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("bugId", id);
        navController.navigate(R.id.action_myPageSurveyDetailListFragment_to_bugItemFragment, bundle);
    }

    @Override
    public void showMyPageSurveyList(List<MySurvey> mySurveyList) {
        binding.progressBar.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);

        adapter.submitList(mySurveyList);
    }

    @Override
    public void showUserName(String userName) {
        binding.tvUserName.setText(userName);
    }

    @Override
    public void showErrorMessage(String message) {
        binding.progressBar.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);

        binding.errorMessage.setText(message);
    }

    @Override
    public void undoRefreshLoading() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }
}
