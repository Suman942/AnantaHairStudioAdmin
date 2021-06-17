package com.freelance.anantahairstudioadmin.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.AddServiceActivity;
import com.freelance.anantahairstudioadmin.addService.AllServicesActivity;
import com.freelance.anantahairstudioadmin.adminInfo.AdminInfoActivity;
import com.freelance.anantahairstudioadmin.allBooking.AllBookingsActivity;
import com.freelance.anantahairstudioadmin.acceptedBooking.AcceptedBookingActivity;
import com.freelance.anantahairstudioadmin.databinding.ActivityHomeBinding;
import com.freelance.anantahairstudioadmin.gallery.AllPicsActivity;

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
    }
}