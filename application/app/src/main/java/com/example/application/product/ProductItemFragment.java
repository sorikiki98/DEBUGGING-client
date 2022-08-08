package com.example.application.product;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.application.R;
import com.example.application.data.Product;
import com.example.application.databinding.FragmentProductItemBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ProductItemFragment extends Fragment implements ProductItemContract.View {
    @Inject
    ProductItemContract.Presenter presenter;

    @Inject
    Context context;

    private FragmentProductItemBinding binding;

    private int productId;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentProductItemBinding.inflate(getLayoutInflater());
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
        setProductId();
    }

    private void bindViews() {
        binding.ivInterest.setOnClickListener(view ->
                presenter.toggleProductInterest(productId));
    }

    private void setProductId() {
        int productId = getArguments().getInt("productId");
        this.productId = productId;
        presenter.setProductId(productId);
    }

    @Override
    public void navigate(int id) {

    }

    @Override
    public void showProduct(Product product) {
        Glide.with(context)
                .load(product.thumbnail)
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(0.25f)
                .into(binding.ivProductThumb);

        binding.tvProductTitle.setText(product.name);
        binding.tvInterest.setText(Integer.toString(product.numOfInterestedUsers));

        if (product.isProductInterested == 1) {
            binding.ivInterest.setImageResource(R.drawable.ic_heart_fill);
        } else {
            binding.ivInterest.setImageResource(R.drawable.ic_heart_line);
        }

//        AppCompatButton[] buttons = product.makeTagButton(context);
//        for (AppCompatButton button : buttons) {
//            binding.tagContainer.addView(button);
//        }

        TextView[] descriptions = product.makeTextView(context);
        for (TextView description : descriptions) {
            binding.productDescriptionContainer.addView(description);
        }
    }


    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }
}

