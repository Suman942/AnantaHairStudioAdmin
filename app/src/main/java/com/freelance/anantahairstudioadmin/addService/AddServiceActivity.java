package com.freelance.anantahairstudioadmin.addService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.addService.viewModel.ServicesViewModel;
import com.freelance.anantahairstudioadmin.databinding.ActivityAddServiceBinding;

public class AddServiceActivity extends AppCompatActivity {

    ActivityAddServiceBinding binding;
    String serviceName, serviceImg, price, discountedPrice, id, categoryId, info;
    ServicesViewModel servicesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_service);
        servicesViewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
        getIntentData();
        clickView();
        observer();

    }

    private void observer() {
        servicesViewModel.updateServiceLiveData().observe(this, new Observer<UpdateServiceResponse>() {
            @Override
            public void onChanged(UpdateServiceResponse updateServiceResponse) {
                Toast.makeText(AddServiceActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                binding.update.setEnabled(true);
                startActivity(new Intent(AddServiceActivity.this,AllServicesActivity.class));
                finish();
            }
        });
    }

    private void clickView() {
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicesViewModel.updateService(id,binding.category.getText().toString(),binding.price.getText().toString(),binding.discountPrice.getText().toString(),binding.serviceName.getText().toString(),binding.description.getText().toString());
                binding.update.setEnabled(false);
            }
        });
    }

    private void getIntentData() {
        serviceName = getIntent().getStringExtra("serviceName");
        serviceImg = getIntent().getStringExtra("serviceImg");
        price = getIntent().getStringExtra("price");
        discountedPrice = getIntent().getStringExtra("discountedPrice");
        id = getIntent().getStringExtra("id");
        categoryId = getIntent().getStringExtra("categoryId");
        info = getIntent().getStringExtra("info");

        binding.serviceName.setText(serviceName);
        binding.price.setText(price);
        binding.discountPrice.setText(discountedPrice);
        binding.description.setText(info);
        binding.category.setText(categoryId);
//        if (categoryId.equals("1")){
//            binding.category.setText("Haircut");
//        }
//
//        if (categoryId.equals("2")){
//            binding.category.setText("Shaving");
//        }
//        if (categoryId.equals("9")){
//            binding.category.setText("Straightening");
//        }
//        if (categoryId.equals("8")){
//            binding.category.setText("Facial");
//        }
//        if (categoryId.equals("10")){
//            binding.category.setText("Manicure");
//        }
//        if (categoryId.equals("11")){
//            binding.category.setText("Pedicure");
//        }
//        if (categoryId.equals("3")){
//            binding.category.setText("Pedicure");
//        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Category.categoryId);
        binding.category.setAdapter(adapter);
        binding.category.setThreshold(1);
        binding.category.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                binding.category.showDropDown();
                return true;
            }
        });
    }
}