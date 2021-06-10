package com.freelance.anantahairstudioadmin.allBooking.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.AllBookingsActivity;
import com.freelance.anantahairstudioadmin.databinding.ActivityAcceptedBookingBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;

public class AcceptedBookingActivity extends AppCompatActivity {

    ActivityAcceptedBookingBinding binding;
    AllBookingAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accepted_booking);
        binding.bookingRecyclerView.setEmptyView(binding.emptyText);
        initialise();
    }

    private void initialise() {
        bookingAdapter = new AllBookingAdapter(this);
        binding.bookingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.bookingRecyclerView.setAdapter(bookingAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AcceptedBookingActivity.this, HomeActivity.class));
        finish();
    }
}