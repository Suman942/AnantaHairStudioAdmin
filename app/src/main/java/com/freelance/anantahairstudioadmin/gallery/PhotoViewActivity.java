package com.freelance.anantahairstudioadmin.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.databinding.ActivityPhotoViewBinding;
import com.freelance.anantahairstudioadmin.utils.PrefManager;

import java.util.ArrayList;

public class PhotoViewActivity extends AppCompatActivity {
    ActivityPhotoViewBinding binding;
    ArrayList<FetchGalleryResponse.Data.Image> imageList = new ArrayList<>();
    GalleryViewModel galleryViewModel;
    String url = null;
    GalleryViewAdapter adapter;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_photo_view);
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        getIntentData();
        observer();

    }

    private void getIntentData() {
         position = getIntent().getIntExtra("position",0);
        binding.viewPager.setCurrentItem(position);
    }

    private void observer() {
        galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
        galleryViewModel.fetchGalleryImgLiveData().observe(this, new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                imageList.clear();
                imageList.addAll(fetchGalleryResponse.getData().getImages());
                url = fetchGalleryResponse.getData().getBaseUrl();
                Log.i("file","2: "+url);
                adapter = new GalleryViewAdapter(getApplicationContext(),imageList,url);
                binding.viewPager.setAdapter(adapter);
                binding.viewPager.setCurrentItem(position);
                adapter.notifyDataSetChanged();

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}