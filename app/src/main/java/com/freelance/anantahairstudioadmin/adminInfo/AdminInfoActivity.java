package com.freelance.anantahairstudioadmin.adminInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.databinding.ActivityAdminInfoBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;

public class AdminInfoActivity extends AppCompatActivity {
    ActivityAdminInfoBinding binding;
    AdminInfoViewModel adminInfoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_admin_info);
       adminInfoViewModel = new ViewModelProvider(this).get(AdminInfoViewModel.class);
       clickView();
       observer();
    }

    private void observer() {
        adminInfoViewModel.updateAdminInfoLiveData().observe(this, new Observer<UpdateServiceResponse>() {
            @Override
            public void onChanged(UpdateServiceResponse updateServiceResponse) {
                if (updateServiceResponse.getData() != null){
                    Toast.makeText(AdminInfoActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminInfoActivity.this, HomeActivity.class));
                    finish();
                }
            }
        });
    }

    private void clickView() {
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminInfoViewModel.updateAdminInfo("","","",binding.phone.getText().toString(),
                        binding.watzapp.getText().toString(),binding.email.getText().toString());
            }
        });
    }
}