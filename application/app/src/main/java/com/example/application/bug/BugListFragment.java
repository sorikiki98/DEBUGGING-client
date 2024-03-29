package com.example.application.bug;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.application.GridLayoutSpacingDecoration;
import com.example.application.R;
import com.example.application.data.Bug;
import com.example.application.databinding.FragmentBugListBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class BugListFragment extends Fragment implements BugListContract.View {
    private FragmentBugListBinding binding;

    private BugListAdapter adapter = new BugListAdapter(new BugDiffCallback(), (bugId) -> {
        navigate(bugId);
        return null;
    });

    @Inject
    BugListContract.Presenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentBugListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        bindViews();
    }

    private void initViews() {
        binding.rvBugList.setAdapter(adapter);
        binding.rvBugList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvBugList.addItemDecoration(new GridLayoutSpacingDecoration());

        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void bindViews() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshBugs());
    }

    @Override
    public void showBugs(List<Bug> bugs) {
        if (!bugs.isEmpty()) {
            adapter.submitList(bugs);
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
    public void navigate(int bugId) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("bugId", bugId);
        navController.navigate(R.id.action_bugListFragment_to_bugItemFragment, bundle);
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