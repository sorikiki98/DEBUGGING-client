package com.example.application.bug;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.application.data.Bug;
import com.example.application.databinding.FragmentSearchBinding;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class BugSearchFragment extends Fragment implements BugSearchContract.View {
    @Inject
    BugSearchContract.Presenter presenter;

    private FragmentSearchBinding binding;

    private final BugSearchAdapter adapter = new BugSearchAdapter(new BugDiffCallback(), (bugId) ->
    {
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
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        bindViews();
    }

    private void initViews() {
        binding.rvFilteredList.setAdapter(adapter);
        binding.rvFilteredList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void bindViews() {
        binding.searchEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.filterBugs(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void showFilteredBugs(List<Bug> bugs) {
        adapter.submitList(bugs);
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("bugId", id);
        navController.navigate(R.id.action_bugSearchFragment_to_bugItemFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
