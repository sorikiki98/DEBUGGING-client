package com.example.application.bug;

import androidx.fragment.app.Fragment;

import com.example.application.data.Bug;

import java.util.List;

import javax.inject.Inject;

public class BugSearchFragment extends Fragment implements BugListContract.View {
    @Inject
    BugListContract.Presenter presenter;


    @Override
    public void showBugs(List<Bug> bugs) {

    }

    @Override
    public void showErrorMessage(String message) {

    }


    @Override
    public void navigate(int id) {

    }
}
