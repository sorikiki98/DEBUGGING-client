package com.example.application.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.application.bug.BugActivity;
import com.example.application.company.CompanyActivity;
import com.example.application.databinding.FragmentHomeBinding;
import com.example.application.product.ProductActivity;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
    }

    private void bindViews() {
        binding.bugMenuButton.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), BugActivity.class);
            startActivity(intent);
        });

        binding.productMenu.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), ProductActivity.class);
            startActivity(intent);
        });

        binding.reservationMenu.setOnClickListener(view -> {
            Intent intent = new Intent(requireActivity(), CompanyActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
