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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.application.R;
import com.example.application.data.Company;
import com.example.application.databinding.FragmentCompanyItemBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CompanyItemFragment extends Fragment implements CompanyItemContract.View {
    @Inject
    CompanyItemContract.Presenter presenter;

    private FragmentCompanyItemBinding binding;

    private int companyId;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCompanyItemBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setCompanyId();
        initViews();
        bindViews();
    }

    private void setCompanyId() {
        companyId = getArguments().getInt("companyId");
        presenter.setCompanyId(companyId);
    }

    private void initViews() {
        presenter.subscribe();
    }

    private void bindViews() {
        binding.btReservation.setOnClickListener(view -> navigate(companyId));
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("companyId", id);
        navController.navigate(R.id.action_companyItemFragment_to_companyReservationFragment, bundle);
    }

    @Override
    public void showCompany(Company company) {
        Glide.with(requireActivity())
                .load(company.thumbnail)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivCompanyThumb);

        binding.tvCompanyName.setText(company.name);
        binding.tvDescription.setText(company.description);
        binding.tvAvailableAreas.setText(company.availableArea);
        binding.tvAvailableCounselTime.setText(company.availableCounselTime);
        binding.tvContactNumbers.setText(company.contactNumbers);
    }

    @Override
    public void showErrorMessage(String message) {

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

