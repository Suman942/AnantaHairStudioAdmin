package com.freelance.anantahairstudioadmin.gallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.databinding.ActivityAllPicsBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;
import com.freelance.anantahairstudioadmin.utils.PrefManager;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;

public class AllPicsActivity extends AppCompatActivity {
    ActivityAllPicsBinding binding;
    final int SELECT_IMAGE = 101;
    GalleryViewModel galleryViewModel;
    GalleryAdapter adapter;
    ArrayList<FetchGalleryResponse.Data.Image> imageList = new ArrayList<>();
     String url = "https://xbytelab.com/projects/ananta-salon/image/gallery/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_pics);
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        observer();
        clickView();
        binding.recyclerView.setEmptyView(binding.empty);

    }


    private void clickView() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });
    }

    private void observer() {
        galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
        galleryViewModel.fetchGalleryImgLiveData().observe(this, new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                imageList.clear();
                imageList.addAll(fetchGalleryResponse.getData().getImages());
                Log.i("file","2: "+url);
                adapter = new GalleryAdapter(getApplicationContext(),imageList,url);
                binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                binding.recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                binding.loader.setVisibility(View.GONE);
            }
        });
        galleryViewModel.galleryPicLiveData().observe(this, new Observer<UpdateServiceResponse>() {
            @Override
            public void onChanged(UpdateServiceResponse updateServiceResponse) {
               try {
                   Toast.makeText(AllPicsActivity.this, ""+updateServiceResponse.getData().getMessage(), Toast.LENGTH_SHORT).show();
                   galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
               }
               catch (Exception e){
                   Toast.makeText(AllPicsActivity.this, "Please try later", Toast.LENGTH_SHORT).show();
                   binding.loader.setVisibility(View.GONE);
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
                CropImage.activity(selectedImage).start(AllPicsActivity.this);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());
                Log.i("file",""+file);
                galleryViewModel.galleryPic(file);
                binding.loader.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AllPicsActivity.this, HomeActivity.class));
    }
}