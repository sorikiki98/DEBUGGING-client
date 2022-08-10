package com.example.application.mypage;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.data.MyReservation;
import com.example.application.databinding.ItemMypageReservationBinding;

import java.util.function.Function;

public class MyPageCompanyDetailListAdapter extends ListAdapter<MyReservation, MyPageCompanyDetailListAdapter.ViewHolder> {
    private final Function<Integer, Void> itemClickCallback;

    protected MyPageCompanyDetailListAdapter(@NonNull MyReservationDiffCallback diffCallback, @NonNull Function<Integer, Void> itemClickCallback) {
        super(diffCallback);
        this.itemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMypageReservationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyReservation item = getCurrentList().get(position);
        holder.bind(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMypageReservationBinding binding;

        public ViewHolder(@NonNull ItemMypageReservationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("ResourceAsColor")
        public void bind(MyReservation myReservation) {

            binding.tvProcessState.setText(myReservation.getProcessState());
            binding.tvCompanyName.setText(myReservation.companyName);
            binding.tvBugType.setText(myReservation.bugName);

            switch(myReservation.processState) {
                case(1):
                    binding.titleContainer.setBackgroundResource(R.drawable.bg_mypage_reservation_title_bg);
                    binding.ivIcon.setImageResource(R.drawable.ic_pen_white);
                    binding.tvDateTitle.setText("방문 예정일");
                    binding.tvDate.setText(myReservation.visitDateTime);
                    break;
                case(2):
                    binding.titleContainer.setBackgroundResource(R.drawable.bg_mypage_reservation_title_bg);
                    binding.ivIcon.setImageResource(R.drawable.ic_loader);
                    binding.tvDateTitle.setText("방문 예정일");
                    binding.tvDate.setText(myReservation.visitDateTime);
                    break;
                case(3):
                    binding.titleContainer.setBackgroundResource(R.drawable.bg_mypage_reservation_title_gray_bg);
                    binding.ivIcon.setImageResource(R.drawable.ic_check_circle_white);
                    binding.tvDateTitle.setText("방문 일자");
                    binding.tvDate.setText(myReservation.reservationDateTime);
                    break;
                default:
                    binding.titleContainer.setBackgroundResource(R.drawable.bg_mypage_reservation_title_bg);
                    binding.ivIcon.setImageResource(R.drawable.ic_clipboard);
                    binding.tvDateTitle.setText("예약 일자");
                    binding.tvDate.setText(myReservation.reservationDateTime);
                    break;
           }

           binding.getRoot().setOnClickListener(view -> itemClickCallback.apply(myReservation.reservationId));
        }
    }
}