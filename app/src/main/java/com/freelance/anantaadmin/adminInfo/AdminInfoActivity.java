package com.freelance.anantaadmin.adminInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantaadmin.R;
import com.freelance.anantaadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantaadmin.databinding.ActivityAdminInfoBinding;
import com.freelance.anantaadmin.home.HomeActivity;
import com.freelance.anantaadmin.utils.PrefManager;

public class AdminInfoActivity extends AppCompatActivity {
    ActivityAdminInfoBinding binding;
    AdminInfoViewModel adminInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_info);
        adminInfoViewModel = new ViewModelProvider(this).get(AdminInfoViewModel.class);
        clickView();
        observer();
        fetchData();
    }

    private void fetchData() {
        adminInfoViewModel.getAdminDetails(PrefManager.getInstance().getString(R.string.authToken));
    }

    private void observer() {
        adminInfoViewModel.updateAdminInfoLiveData().observe(this, new Observer<UpdateServiceResponse>() {
            @Override
            public void onChanged(UpdateServiceResponse updateServiceResponse) {
                if (updateServiceResponse.getData() != null) {
                    Toast.makeText(AdminInfoActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(AdminInfoActivity.this, HomeActivity.class));
//                    finish();
                    binding.update.setEnabled(true);
                    adminInfoViewModel.getAdminDetails(PrefManager.getInstance().getString(R.string.authToken));

                }
            }
        });

        adminInfoViewModel.getAdminDetailsLiveData().observe(this, new Observer<ContactUpdateResponse>() {
            @Override
            public void onChanged(ContactUpdateResponse contactUpdateResponse) {
                try {
                    if (!contactUpdateResponse.getData().getBusinessInfo().getPhone().isEmpty()) {
                        binding.phone.setText(contactUpdateResponse.getData().getBusinessInfo().getPhone());
                    }
                    else {
                        binding.phone.setText("N/A");
                    }
                    if (!contactUpdateResponse.getData().getBusinessInfo().getWhatsapp().isEmpty()) {
                        binding.watzapp.setText(contactUpdateResponse.getData().getBusinessInfo().getWhatsapp());
                    }
                    else {
                        binding.watzapp.setText("N/A");
                    }
                    if (!contactUpdateResponse.getData().getBusinessInfo().getEmail().isEmpty()) {
                        binding.email.setText(contactUpdateResponse.getData().getBusinessInfo().getEmail());
                    }
                    else {
                        binding.email.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getFacebook().isEmpty()) {
                        binding.facebook.setText(contactUpdateResponse.getData().getBusinessInfo().getFacebook());
                    }
                    else {
                        binding.facebook.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getInstagram().isEmpty()) {
                        binding.Instagram.setText(contactUpdateResponse.getData().getBusinessInfo().getInstagram());
                    }
                    else {
                        binding.Instagram.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getYoutube().isEmpty()) {
                        binding.youtube.setText(contactUpdateResponse.getData().getBusinessInfo().getYoutube());
                    }
                    else {
                        binding.youtube.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getWebsite().isEmpty()) {
                        binding.website.setText(contactUpdateResponse.getData().getBusinessInfo().getWebsite());
                    }
                    else {
                        binding.website.setText("N/A");
                    }
                    binding.loader.setVisibility(View.GONE);
                } catch (Exception e) {
                    binding.loader.setVisibility(View.GONE);
                }
            }
        });
    }

    private void clickView() {
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.phone.getText().toString().isEmpty() && binding.watzapp.getText().toString().isEmpty() && binding.email.getText().toString().isEmpty()
                        && binding.facebook.getText().toString().isEmpty() && binding.Instagram.getText().toString().isEmpty() && binding.youtube.getText().toString().isEmpty()
                        && binding.website.getText().toString().isEmpty()){
                    Toast.makeText(AdminInfoActivity.this, "Required fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    adminInfoViewModel.updateAdminInfo("", "", "", binding.phone.getText().toString(),
                            binding.watzapp.getText().toString(), binding.email.getText().toString(), binding.facebook.getText().toString(),
                            binding.Instagram.getText().toString(), binding.youtube.getText().toString(), binding.website.getText().toString());

                    binding.update.setEnabled(false);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminInfoActivity.this, HomeActivity.class));
        finish();
    }
}