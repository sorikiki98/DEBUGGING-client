package com.example.application.company;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.application.R;
import com.example.application.data.Company;
import com.example.application.databinding.FragmentBugListBinding;
import com.example.application.databinding.FragmentCompanyListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CompanyListFragment extends Fragment implements CompanyListContract.View {
    @Inject
    CompanyListContract.Presenter presenter;

    private FragmentCompanyListBinding binding;

    private final CompanyListAdapter companyListAdapter = new CompanyListAdapter(
            new CompanyDiffCallback(),
            (companyId) -> {
                navigate(companyId);
                return null;
            },
            (companyId) -> {
                presenter.toggleCompanyInterest(companyId);
                return null;
            }
    );

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCompanyListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        bindViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    private void initViews() {
        binding.rvCompanyList.setAdapter(companyListAdapter);
        binding.rvCompanyList.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void bindViews() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshCompanies());
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("companyId", id);
        navController.navigate(R.id.action_companyListFragment_to_companyItemFragment, bundle);
    }

    @Override
    public void showCompanies(List<Company> companies) {
        if (!companies.isEmpty()) {
            companyListAdapter.submitList(companies);
            binding.progressBar.setVisibility(View.GONE);
        } else showErrorMessage("목록이 비어있습니다.");
    }

    @Override
    public void showErrorMessage(String message) {
        binding.errorMessage.setVisibility(View.VISIBLE);
        binding.errorMessage.setText(message);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void undoRefreshLoading() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        presenter.unsubscribe();
    }
}

