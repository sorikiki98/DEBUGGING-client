package com.example.application.product;

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

import com.example.application.R;
import com.example.application.company.CompanyDiffCallback;
import com.example.application.company.CompanyListAdapter;
import com.example.application.data.Product;
import com.example.application.databinding.FragmentProductListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ProductListFragment extends Fragment implements ProductListContract.View {
    @Inject
    ProductListContract.Presenter presenter;

    private FragmentProductListBinding binding;

    private final ProductListAdapter productListAdapter = new ProductListAdapter(
            new ProductDiffCallback(),
            (productId) -> {
                navigate(productId);
                return null;
            },
            (productId) -> {
                presenter.toggleProductInterest(productId);
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
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    private void initViews() {
        binding.rvProductList.setAdapter(productListAdapter);
        binding.rvProductList.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("productId", id);
        navController.navigate(R.id.action_productListFragment_to_productItemFragment, bundle);
    }

    @Override
    public void showProducts(List<Product> products) {
        if (!products.isEmpty()) {
            productListAdapter.submitList(products);
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        presenter.unsubscribe();
    }
}
