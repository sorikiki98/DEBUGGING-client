package com.example.application.mypage;

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

import com.example.application.R;
import com.example.application.data.MyProduct;
import com.example.application.databinding.FragmentMypageProductInterestListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MyPageProductInterestListFragment extends Fragment implements MyPageProductInterestListContract.View {
    @Inject
    MyPageProductInterestListContract.Presenter presenter;

    private FragmentMypageProductInterestListBinding binding;

    private MyPageProductInterestListAdapter adapter = new MyPageProductInterestListAdapter(new MyProductDiffCallback(), (productId) -> {
        navigate(productId);
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
        binding = FragmentMypageProductInterestListBinding.inflate(getLayoutInflater());
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
        binding.swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshMyPageProductInterest());
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("productId", id);
        navController.navigate(R.id.action_myPageProductInterestListFragment_to_productItemFragment, bundle);
    }

    @Override
    public void showMyPageProductInterest(List<MyProduct> myProductList) {
        binding.progressBar.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
        adapter.submitList(myProductList);
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

class GridLayoutSpacingDecoration extends RecyclerView.ItemDecoration {
    private int halfSpace = 24;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        parent.setClipToPadding(true);
        outRect.set(halfSpace, halfSpace, halfSpace, halfSpace);
    }
}
