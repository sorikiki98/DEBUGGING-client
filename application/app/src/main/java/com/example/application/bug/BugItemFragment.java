package com.example.application.bug;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.application.R;
import com.example.application.data.Bug;
import com.example.application.databinding.FragmentBugItemBinding;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class BugItemFragment extends Fragment implements BugItemContract.View{
    private FragmentBugItemBinding binding;

    @Inject
    BugItemContract.Presenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentBugItemBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setBugId();
        initViews();
        bindViews();
    }

    private void setBugId() {
        presenter.setBugId(getArguments().getInt("bugId"));
    }

    private void initViews() {
        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.contentContainer.setVisibility(View.GONE);
        presenter.subscribe();
    }

    private void bindViews() {
        binding.closeBtn.setOnClickListener(view -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });
    }

    @Override
    public void showBug(Bug bug) {
        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        binding.contentContainer.setVisibility(View.VISIBLE);

        binding.tvBugName.setText(bug.getName());
        binding.tvBugAppearance.setText(bug.getAppearance());
        binding.tvBugColor.setText(bug.getColor());
        binding.tvBugHabitat.setText(bug.getHabitat());
        binding.tvBugMovement.setText(bug.getMovement());
        binding.ivBugThumb.setImageResource(bug.convertToDrawable());

        int padding = bug.convertToPadding(requireActivity());
        binding.ivBugThumb.setPadding(padding, padding, padding, padding);

        TextView[] traits = bug.convertToSurveyResultSentences(requireActivity());
        for (TextView tv: traits) {
            binding.traitDescriptionScrollView.addView(tv);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        binding.errorMessage.setVisibility(View.VISIBLE);
        binding.errorMessage.setText(message);
        binding.progressBar.setVisibility(View.GONE);
        binding.contentContainer.setVisibility(View.GONE);
    }


    @Override
    public void navigate(int id) {}

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
