package com.freelance.anantaadmin.gallery;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantaadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantaadmin.network.ApiClient;
import com.freelance.anantaadmin.network.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryRepo {
    private static GalleryRepo galleryRepo;
    private ApiInterface apiInterface;

    public GalleryRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static GalleryRepo getInstance() {
        if (galleryRepo == null) {
            galleryRepo = new GalleryRepo();
        }
        return galleryRepo;
    }

    public void galleryPicUpload(File file, MutableLiveData<UpdateServiceResponse> mutableLiveData){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file );
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        apiInterface.uploadPic(body).enqueue(new Callback<UpdateServiceResponse>() {
            @Override
            public void onResponse(Call<UpdateServiceResponse> call, Response<UpdateServiceResponse> response) {
                if (response.code() == 200) {
                    Log.i("Gallery", "success: ");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UpdateServiceResponse> call, Throwable t) {
                Log.i("Gallery", " "+t.getMessage());
                mutableLiveData.setValue(null);

            }
        });
    }

    public void fetchGalleryImg(String token,MutableLiveData<FetchGalleryResponse> mutableLiveData){
        apiInterface.getGalleryPic(token).enqueue(new Callback<FetchGalleryResponse>() {
            @Override
            public void onResponse(Call<FetchGalleryResponse> call, Response<FetchGalleryResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FetchGalleryResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

    public void deleteImg(String img,MutableLiveData<FetchGalleryResponse> mutableLiveData){
        apiInterface.deletePhoto(img).enqueue(new Callback<FetchGalleryResponse>() {
            @Override
            public void onResponse(Call<FetchGalleryResponse> call, Response<FetchGalleryResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FetchGalleryResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }
}
