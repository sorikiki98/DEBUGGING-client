package com.example.application.bug;

import androidx.fragment.app.Fragment;

import com.example.application.data.Bug;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class BugsSearchFragment extends Fragment implements BugsListContract.View {
    @Inject
    BugsListContract.Presenter presenter;


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
