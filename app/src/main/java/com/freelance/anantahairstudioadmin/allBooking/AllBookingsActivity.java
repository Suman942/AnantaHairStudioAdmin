package com.freelance.anantahairstudioadmin.allBooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.adapter.AllBookingAdapter;
import com.freelance.anantahairstudioadmin.databinding.ActivityAllBookingsBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;

public class AllBookingsActivity extends AppCompatActivity {

    ActivityAllBookingsBinding binding;
    AllBookingAdapter bookingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_all_bookings);

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
        startActivity(new Intent(AllBookingsActivity.this, HomeActivity.class));
        finish();
    }
}