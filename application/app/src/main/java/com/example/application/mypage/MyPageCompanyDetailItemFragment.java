package com.example.application.mypage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.application.R;
import com.example.application.data.Reservation;
import com.example.application.databinding.FragmentMypageCompanyDetailItemBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MyPageCompanyDetailItemFragment extends Fragment implements MyPageCompanyDetailItemContract.View {
    @Inject
    MyPageCompanyDetailItemContract.Presenter presenter;

    @Inject
    Context context;

    private FragmentMypageCompanyDetailItemBinding binding;

    private int reservationId;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMypageCompanyDetailItemBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setReservationId();
        initViews();
        presenter.subscribe();
    }

    private void setReservationId() {
        reservationId = getArguments().getInt("reservationId");
        presenter.setReservationId(reservationId);
    }

    private void initViews() {
        binding.contentContainer.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigate(int id) {

    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void showReservationInformation(Reservation reservation) {
        binding.contentContainer.setVisibility(View.VISIBLE);
        binding.errorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);

        binding.tvName.setText("현재 " + reservation.userName + " 님은");
        binding.tvState.setText(reservation.getProcessState() + " 상태입니다");


        binding.ivState0.setImageResource(R.drawable.oval_r12_green);
        binding.ivState1.setImageResource(R.drawable.oval_r12_grey);
        binding.ivState2.setImageResource(R.drawable.oval_r12_grey);
        binding.ivState3.setImageResource(R.drawable.oval_r12_grey);

        binding.process0Container.setVisibility(View.VISIBLE);
        binding.process1Container.setVisibility(View.GONE);
        binding.process2Container.setVisibility(View.GONE);

        binding.tvState0ReserveDateTime.setText(reservation.reservationDateTime);
        binding.tvState0CompanyName.setText(reservation.companyName);
        binding.tvState0ReserveDate.setText(reservation.reservationDateTime);
        String visitDateTime = reservation.visitDateTime;
        if (visitDateTime != null) {
            binding.tvState0VisitDate.setText(reservation.visitDateTime.split(" ")[0]);
            binding.tvState0VisitTime.setText(reservation.visitDateTime.split(" ")[1]);
        }
        binding.tvState0Applicant.setText(reservation.userName);
        binding.tvState0ContactNumbers.setText(reservation.userContactNumbers);
        binding.tvState0Address.setText(reservation.userAddress);
        binding.tvState0BugName.setText(reservation.bugName);
        binding.tvState0FoundDate.setText(reservation.firstFoundDate);
        binding.tvState0FoundPlace.setText(reservation.firstFoundPlace);
        binding.tvState0NumOfRooms.setText(Integer.toString(reservation.numOfRooms));
        binding.tvState0HasBugBeenShown.setText(reservation.getHasBugBeenShown());
        binding.tvState0ExtraMessage.setText(reservation.extraMessage);

        if (reservation.processState >= 1) {
            binding.ivState0.setImageResource(R.drawable.oval_r12_grey);
            binding.ivState1.setImageResource(R.drawable.oval_r12_green);

            binding.process1Container.setVisibility(View.VISIBLE);

            binding.tvState1EngineerName.setText(reservation.engineerName);
            binding.tvState1EngineerContactNumbers.setText(reservation.engineerContactNumbers);
            binding.tvState1ExpectedEstimate.setText(reservation.expectedEstimate);
            binding.tvState1VisitDateTime.setText(reservation.visitDateTime);
        }
        if (reservation.processState >= 2) {
            binding.ivState1.setImageResource(R.drawable.oval_r12_grey);
            binding.ivState2.setImageResource(R.drawable.oval_r12_green);

            binding.process2Container.setVisibility(View.VISIBLE);

            binding.tvState2CompanyName.setText(reservation.companyName);
            binding.tvState2BugType.setText(reservation.bugName);
            binding.tvState2VisitDateTime.setText(reservation.visitDateTime);
            binding.tvState2Address.setText(reservation.userAddress);
            binding.tvState2EngineerName.setText(reservation.engineerName);
            binding.tvState2ContactNumbers.setText(reservation.engineerContactNumbers);
            binding.tvState2ExpectedEstimate.setText(reservation.expectedEstimate);
        }
        if (reservation.processState >= 3) {
            binding.ivState2.setImageResource(R.drawable.oval_r12_grey);
            binding.ivState3.setImageResource(R.drawable.oval_r12_green);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        binding.contentContainer.setVisibility(View.GONE);
        binding.errorMessage.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorMessage.setText(message);
    }
}
