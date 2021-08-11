package com.freelance.anantaadmin.addService;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.freelance.anantaadmin.R;
import com.freelance.anantaadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantaadmin.addService.viewModel.ServicesViewModel;
import com.freelance.anantaadmin.databinding.ActivityAddServiceBinding;
import com.freelance.anantaadmin.network.RequestFormatter;
import com.freelance.anantaadmin.notification.NotificationViewModel;
import com.freelance.anantaadmin.utils.GlideHelper;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

public class AddServiceActivity extends AppCompatActivity {

    ActivityAddServiceBinding binding;
    String serviceName, serviceImg, price, discountedPrice, id, categoryId, info;
    ServicesViewModel servicesViewModel;
    final int SELECT_IMAGE = 101;
    File file;
    ProgressDialog progressDialog;
    int newService = 0;
    String desc;
    NotificationViewModel notificationViewModel;
    String topic = "service";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_service);
        servicesViewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating service....");
        progressDialog.setCanceledOnTouchOutside(false);
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
                binding.count.setText("" + s.length());
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
                try {
                    Toast.makeText(AddServiceActivity.this, "" + updateServiceResponse.getData().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddServiceActivity.this, AllServicesActivity.class));
                    finish();
                } catch (Exception e) {
                    Toast.makeText(AddServiceActivity.this, "Please try later", Toast.LENGTH_SHORT).show();
                }
                binding.update.setEnabled(true);
                progressDialog.dismiss();

            }
        });

        servicesViewModel.createServiceLiveData().observe(this, new Observer<UpdateServiceResponse>() {
            @Override
            public void onChanged(UpdateServiceResponse updateServiceResponse) {
                try {
                    Toast.makeText(AddServiceActivity.this, ""+updateServiceResponse.getData().getMessage(), Toast.LENGTH_SHORT).show();
                    if (categoryId.equals("116")){
                        notificationViewModel.sendNotification(RequestFormatter.sendNotification("/topics/" + topic, "Today's Special", ""+binding.serviceName.getText().toString(), R.drawable.main_logo));
                    }
                    startActivity(new Intent(AddServiceActivity.this, AllServicesActivity.class));
                    finish();
                }
                catch (Exception e){
                    Toast.makeText(AddServiceActivity.this, "Technical error", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                binding.update.setEnabled(true);
            }
        });
    }

    private void clickView() {
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newService == 1) {
                    if (file != null && categoryId != null && !binding.price.getText().toString().isEmpty() &&
                            !binding.discountPrice.getText().toString().isEmpty() &&    !binding.serviceName.getText().toString().isEmpty()) {
                        servicesViewModel.createService(categoryId, binding.price.getText().toString(), binding.discountPrice.getText().toString(), binding.serviceName.getText().toString(), binding.description.getText().toString(), file);
                        binding.update.setEnabled(false);
                        progressDialog.show();
                    }
                    else {
                        Toast.makeText(AddServiceActivity.this, "Field empty", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if (file == null) {
                        servicesViewModel.updateServiceWithoutImg(id, categoryId, binding.price.getText().toString(), binding.discountPrice.getText().toString(), binding.serviceName.getText().toString(), binding.description.getText().toString().replaceAll("\\\\n", "\n"));
                        binding.update.setEnabled(false);
                        progressDialog.show();
                    }
                    else {
                        servicesViewModel.updateService(id, categoryId, binding.price.getText().toString(), binding.discountPrice.getText().toString(), binding.serviceName.getText().toString(), binding.description.getText().toString().replaceAll("\\\\n", "\n"), file);
                        binding.update.setEnabled(false);
                        progressDialog.show();
                    }
                }


            }
        });

        binding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });
    }

    private void getIntentData() {
        try {
            newService = getIntent().getIntExtra("addNewService", 0);
        } catch (Exception e) {
        }
        try {
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
            binding.description.setText(info.replaceAll("\\\\n", "\n"));
            GlideHelper.setImageView(this, binding.img, serviceImg, R.drawable.ic_image_placeholder);
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
            if (categoryId.equals("113")) {
                binding.category.setText("Dandruff");
            }
            if (categoryId.equals("114")) {
                binding.category.setText("Spa");
            }
            if (categoryId.equals("115")) {
                binding.category.setText("Colour");
            }
            if (categoryId.equals("116")) {
                binding.category.setText("Other");
            }
        } catch (Exception e) {
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
                if (position == 0) {
                    categoryId = "100";
                }
                if (position == 1) {
                    categoryId = "101";
                }
                if (position == 2) {
                    categoryId = "102";
                }
                if (position == 3) {
                    categoryId = "103";
                }
                if (position == 4) {
                    categoryId = "104";
                }
                if (position == 5) {
                    categoryId = "105";
                }
                if (position == 6) {
                    categoryId = "106";
                }
                if (position == 7) {
                    categoryId = "107";
                }
                if (position == 8) {
                    categoryId = "108";
                }
                if (position == 9) {
                    categoryId = "109";
                }
                if (position == 10) {
                    categoryId = "110";
                }
                if (position == 11) {
                    categoryId = "111";
                }
                if (position == 12) {
                    categoryId = "112";
                }
                if (position == 13) {
                    categoryId = "113";
                }
                if (position == 14) {
                    categoryId = "114";
                }
                if (position == 15) {
                    categoryId = "115";
                }
                if (position == 16) {
                    categoryId = "116";
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                CropImage.activity(selectedImage).start(AddServiceActivity.this);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                GlideHelper.setImageViewWithURI(getApplicationContext(), binding.img, resultUri);
                file = new File(resultUri.getPath());
                Log.i("file", "" + file);

            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddServiceActivity.this, AllServicesActivity.class));
        finish();
    }
}