package com.freelance.anantahairstudioadmin.addService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.addService.viewModel.ServicesViewModel;
import com.freelance.anantahairstudioadmin.databinding.ActivityAddServiceBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;

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

        binding.description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s = binding.description.getText().toString();
                binding.count.setText(""+s.length()+"/100");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                servicesViewModel.updateService(id,categoryId,binding.price.getText().toString(),binding.discountPrice.getText().toString(),binding.serviceName.getText().toString(),binding.description.getText().toString());
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
//        binding.category.setText(categoryId);

        if (categoryId.equals("100")) {
            binding.category.setText("Hair cut");
        }
        if (categoryId.equals("101")) {
            binding.category.setText("Shaving");
        }
        if (categoryId.equals("102")) {
            binding.category.setText("D-tan");
        }
        if (categoryId.equals("103")) {
            binding.category.setText("Facial");
        }

        if (categoryId.equals("104")) {
            binding.category.setText("Straightening");
        }
        if (categoryId.equals("105")) {
            binding.category.setText("Pedicure");
        }
        if (categoryId.equals("106")) {
            binding.category.setText("Bride/Groom");
        }
        if (categoryId.equals("107")) {
            binding.category.setText("Manicure");
        }
        if (categoryId.equals("108")) {
            binding.category.setText("Massage");
        }
        if (categoryId.equals("109")) {
            binding.category.setText("Waxing");
        }
        if (categoryId.equals("110")) {
            binding.category.setText("Hair");
        }
        if (categoryId.equals("111")) {
            binding.category.setText("Child mundan");
        }
        if (categoryId.equals("112")) {
            binding.category.setText("Eye brow");
        }

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
        binding.category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    categoryId = "100";
                }
                if (position == 1){
                    categoryId = "101";
                }
                if (position == 2){
                    categoryId = "102";
                }
                if (position == 3){
                    categoryId = "103";
                }
                if (position == 4){
                    categoryId = "104";
                }
                if (position == 5){
                    categoryId = "105";
                }
                if (position == 6){
                    categoryId = "106";
                }
                if (position == 7){
                    categoryId = "107";
                }
                if (position == 8){
                    categoryId = "108";
                }
                if (position == 9){
                    categoryId = "109";
                }
                if (position == 10){
                    categoryId = "110";
                }
                if (position == 11){
                    categoryId = "111";
                }
                if (position == 12){
                    categoryId = "112";
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(AddServiceActivity.this, AllServicesActivity.class));
        finish();
    }
}