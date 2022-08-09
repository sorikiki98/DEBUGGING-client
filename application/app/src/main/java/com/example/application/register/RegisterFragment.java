package com.example.application.register;

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
import com.example.application.data.RegistrationForm;
import com.example.application.databinding.FragmentRegisterBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RegisterFragment extends Fragment implements RegisterContract.View {
    @Inject
    RegisterContract.Presenter presenter;

    @Inject
    Context context;

    private FragmentRegisterBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews();
    }

    private void bindViews() {
        binding.btnRegister.setOnClickListener(view -> {
            String username = binding.etId.getText().toString();
            String name = binding.etName.getText().toString();
            String password = binding.etPw.getText().toString();
            String passwordCheck = binding.etPwCheck.getText().toString();
            String contactNumbers = binding.etContactNumbers.getText().toString();
            String email = binding.etEmail.getText().toString();
            String address = binding.etAddress.getText().toString();
            String detailAddress = binding.etDetailAddress.getText().toString();
            String sizeOfHouse = binding.etSpace.getText().toString();
            String numOfRooms = binding.etRoom.getText().toString();

            if (username.isEmpty() || name.isEmpty() || password.isEmpty() || passwordCheck.isEmpty() || contactNumbers.isEmpty() || email.isEmpty()) {
                Toast.makeText(context, "필요한 정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(passwordCheck)) {
                Toast.makeText(context, "비밀 번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            } else {
                presenter.register(new RegistrationForm(
                        username,
                        password,
                        name,
                        contactNumbers,
                        email,
                        address + " " + detailAddress,
                        Double.parseDouble(sizeOfHouse),
                        Integer.parseInt(numOfRooms)
                ));
            }

        });
    }

    @Override
    public void navigate(int id) {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_registerFragment_to_registerFragmentCompleted);
    }

    @Override
    public void toastErrorMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
