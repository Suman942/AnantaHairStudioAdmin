package com.freelance.anantahairstudioadmin.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
      clickView();
    }

    private void clickView() {
    }
}