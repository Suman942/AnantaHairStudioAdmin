package com.freelance.anantaadmin.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantaadmin.R;
import com.freelance.anantaadmin.acceptedBooking.BookingHistoryActivity;
import com.freelance.anantaadmin.addService.AllServicesActivity;
import com.freelance.anantaadmin.adminInfo.AdminInfoActivity;
import com.freelance.anantaadmin.allBooking.AllBookingsActivity;
import com.freelance.anantaadmin.acceptedBooking.AcceptedBookingActivity;
import com.freelance.anantaadmin.databinding.ActivityHomeBinding;
import com.freelance.anantaadmin.gallery.AllPicsActivity;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
      clickView();
    }

    private void clickView() {
        binding.allBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AllBookingsActivity.class));
                finish();
            }
        });
        binding.acceptedBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AcceptedBookingActivity.class));
                finish();
            }
        });

        binding.editService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AllServicesActivity.class));
                finish();
            }
        });

        binding.adminAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AdminInfoActivity.class));
                finish();
            }
        });

        binding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AllPicsActivity.class));
                finish();
            }
        });

        binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BookingHistoryActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}