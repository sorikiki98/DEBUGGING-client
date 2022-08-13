package com.example.application.company;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.application.R;
import com.example.application.data.Company;
import com.example.application.data.Reservation;
import com.example.application.data.ReservationForm;
import com.example.application.data.User;
import com.example.application.databinding.FragmentCompanyReservationBinding;
import com.example.application.databinding.FragmentCompanyReservationCheckBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CompanyReservationCheckFragment extends Fragment implements CompanyReservationCheckContract.View {
    @Inject
    CompanyReservationCheckContract.Presenter presenter;

    private FragmentCompanyReservationCheckBinding binding;

    private int companyId;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCompanyReservationCheckBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setCompanyId();
        initViews();
        bindViews();
    }

    private void setCompanyId() {
        this.companyId = getArguments().getInt("companyId");
        presenter.setCompanyId(companyId);
    }

    private void initViews() {
        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.topContainer.setVisibility(View.VISIBLE);
        binding.bottomContainer.setVisibility(View.VISIBLE);
        binding.divider.setVisibility(View.GONE);
        presenter.subscribe();
    }

    private void bindViews() {
        binding.btnSubmit.setOnClickListener(view -> presenter.reserve(companyId));

        binding.btnAmend.setOnClickListener(view -> {
                    NavController navController = NavHostFragment.findNavController(this);
                    Bundle bundle = new Bundle();
                    bundle.putInt("companyId", companyId);
                    navController.navigate(R.id.action_companyReservationCheckFragment_to_companyReservationFragment, bundle);
                }
        );
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("reservationId", id);
        navController.navigate(R.id.action_companyReservationCheckFragment_to_companyReservationCompletedFragment, bundle);
    }


    @Override
    public void showLoadingErrorMessage(String message) {
        binding.topContainer.setVisibility(View.GONE);
        binding.bottomContainer.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.VISIBLE);
        binding.errorMessage.setText(message);
        binding.progressBar.setVisibility(View.GONE);
        binding.divider.setVisibility(View.GONE);
    }

    @Override
    public void showReservationInformation(ReservationForm reservation) {
        binding.topContainer.setVisibility(View.VISIBLE);
        binding.bottomContainer.setVisibility(View.VISIBLE);
        binding.divider.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.tvVisitDate.setText(reservation.wantedDate);
        binding.tvVisitTime.setText(reservation.wantedTime);
        binding.tvBug.setText(reservation.bugName);
        binding.tvFindDate.setText(reservation.firstFoundDate);
        binding.tvFindSpot.setText(reservation.firstFoundPlace);
        String hasBugBeenShown;
        if (reservation.hasBugBeenShown == 1) hasBugBeenShown = "있음";
        else hasBugBeenShown = "없음";
        binding.tvBugExperience.setText(hasBugBeenShown);
        binding.tvAdditionalContent.setText(reservation.extraMessage);
    }

    @Override
    public void showCompanyInformation(Company company) {
        binding.tvCompanyName.setText(company.name);
    }

    @Override
    public void showUserInformation(User user) {
        binding.tvApplicant.setText(user.userName);
        binding.tvContact.setText(user.contactNumbers);
        binding.tvVisitAddress.setText(user.address);
        binding.tvRoomCount.setText(String.valueOf(user.numOfRooms));
    }

    @Override
    public void toastReservationErrorMessage(String message) {
        Toast.makeText(requireActivity(), "예약에 실패하였습니다", Toast.LENGTH_LONG).show();
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
