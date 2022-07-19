package com.example.application.bug;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;
import javax.inject.Named;

public class BugsSearchFragment extends Fragment implements BugsContract.View{
    @Named("bug_search")
    @Inject
    BugsContract.Presenter presenter;

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }
}
