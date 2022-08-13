package com.example.application.company;

import android.content.Context;
import android.os.Bundle;
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
import com.example.application.data.ReservationForm;
import com.example.application.data.User;
import com.example.application.databinding.FragmentCompanyReservationBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CompanyReservationFragment extends Fragment implements CompanyReservationContract.View {
    @Inject
    CompanyReservationContract.Presenter presenter;

    FragmentCompanyReservationBinding binding;

    private int companyId;

    private int hasBugBeenShown;
    private String wantedTime;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCompanyReservationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
        bindViews();
    }

    private void initViews() {
        presenter.loadUserInformation();
        setCompanyId();
    }

    private void bindViews() {
        binding.wantedTimeContainer.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case (R.id.rb_9_to_12):
                    wantedTime = binding.rb9To12.getText().toString();
                    break;
                case (R.id.rb_13_to_16):
                    wantedTime = binding.rb13To16.getText().toString();
                    break;
                case (R.id.rb_16_to_19):
                    wantedTime = binding.rb16To19.getText().toString();
                    break;
                case (R.id.rb_19_to_22):
                    wantedTime = binding.rb19To22.getText().toString();
                    break;
                default:
                    wantedTime = binding.rbAnytime.getText().toString();
            }
        });

        binding.hasBugBeenShownContainer.setOnCheckedChangeListener((radioGroup, i) -> {
            if (radioGroup.getCheckedRadioButtonId() == R.id.rb_yes) {
                hasBugBeenShown = 1;
            } else {
                hasBugBeenShown = 0;
            }
        });

        binding.btnSubmit.setOnClickListener(view -> {
            String bugName = binding.etBug.getText().toString();
            String firstFoundDate = binding.etFindDate.getText().toString();
            String firstFoundPlace = binding.etFindSpot.getText().toString();
            String wantedStartDate = binding.tvWantedStartYear.getText().toString() + "년 " + binding.tvWantedStartMonth.getText().toString() + "월 " + binding.tvWantedStartDay.getText().toString() + "일";
            String wantedEndDate = binding.tvWantedEndYear.getText().toString() + "년 " + binding.tvWantedEndMonth.getText().toString() + "월 " + binding.tvWantedEndDay.getText().toString() + "일";
            String wantedDate = wantedStartDate + "-" + wantedEndDate;

            Date date = new Date(System.currentTimeMillis());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ko", "KR"));
            String reservationDateTime = dateFormat.format(date);
            String extraMessage = binding.etExtraMessage.getText().toString();

            if (bugName.isEmpty() || firstFoundDate.isEmpty() || firstFoundPlace.isEmpty()) {
                Toast.makeText(requireActivity(), "필수 입력 내용을 모두 작성해주세요", Toast.LENGTH_SHORT).show();
            } else {
                presenter.storeReservationForm(new ReservationForm(
                        bugName,
                        firstFoundDate,
                        firstFoundPlace,
                        wantedDate,
                        wantedTime,
                        hasBugBeenShown,
                        reservationDateTime,
                        extraMessage
                ));
            }

        });
    }

    @Override
    public void showUserInformation(User user) {
        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일", new Locale("ko", "KR"));
        binding.tvDate.setText(dateFormat.format(date));
        binding.tvApplicant.setText(user.userName);
        binding.tvContact.setText(user.contactNumbers);
        binding.tvVisitAddress.setText(user.address);
        binding.tvRoomCount.setText(String.valueOf(user.numOfRooms));
    }

    private void setCompanyId() {
        this.companyId = getArguments().getInt("companyId");
        presenter.setCompanyId(companyId);
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        Bundle bundle = new Bundle();
        bundle.putInt("companyId", id);
        navController.navigate(R.id.action_companyReservationFragment_to_companyReservationCheckFragment, bundle);
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
