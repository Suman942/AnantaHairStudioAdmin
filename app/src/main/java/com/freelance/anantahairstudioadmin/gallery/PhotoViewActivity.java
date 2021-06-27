package com.freelance.anantahairstudioadmin.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.databinding.ActivityPhotoViewBinding;
import com.freelance.anantahairstudioadmin.utils.PrefManager;

import java.util.ArrayList;

public class PhotoViewActivity extends AppCompatActivity implements GalleryViewAdapter.Callback{
    ActivityPhotoViewBinding binding;
    ArrayList<FetchGalleryResponse.Data.Image> imageList = new ArrayList<>();
    GalleryViewModel galleryViewModel;
    GalleryViewAdapter adapter;
    int current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_photo_view);
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        getIntentData();
        observer();

    }

    private void getIntentData() {
         current = getIntent().getIntExtra("position",0);
        binding.viewPager.setCurrentItem(current);
    }

    private void observer() {
        galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
        galleryViewModel.fetchGalleryImgLiveData().observe(this, new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                imageList.clear();
                imageList.addAll(fetchGalleryResponse.getData().getImages());
                adapter = new GalleryViewAdapter(getApplicationContext(),imageList,PhotoViewActivity.this::deletePhoto);
                binding.viewPager.setAdapter(adapter);
                binding.viewPager.setCurrentItem(current);
                adapter.notifyDataSetChanged();

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PhotoViewActivity.this,AllPicsActivity.class));
        finish();
    }

    @Override
    public void deletePhoto(String img , int position) {
        current = position;
        galleryViewModel.deleteImg(img);
        galleryViewModel.deleteImgLiveData().observe(this, new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                Toast.makeText(PhotoViewActivity.this, "Image deleted successfully", Toast.LENGTH_SHORT).show();
                galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
                current ++;
            }
        });
    }
}