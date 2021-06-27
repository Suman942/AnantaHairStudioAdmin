package com.freelance.anantahairstudioadmin.gallery;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;

import java.io.File;

public class GalleryViewModel extends ViewModel {
    MutableLiveData<UpdateServiceResponse> galleryMutableLiveData = new MutableLiveData<>();
    MutableLiveData<FetchGalleryResponse> fetchGalleryResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<FetchGalleryResponse> deleteImgResponseMutableLiveData = new MutableLiveData<>();

    public void galleryPic(File file){
        GalleryRepo.getInstance().galleryPicUpload(file,galleryMutableLiveData);

    }
    public MutableLiveData<UpdateServiceResponse> galleryPicLiveData(){
        return galleryMutableLiveData;
    }

    public void fetchGalleryImg(String token){
        GalleryRepo.getInstance().fetchGalleryImg(token,fetchGalleryResponseMutableLiveData);
    }
    public MutableLiveData<FetchGalleryResponse> fetchGalleryImgLiveData(){
        return fetchGalleryResponseMutableLiveData;
    }


    public void deleteImg(String image){
        GalleryRepo.getInstance().deleteImg(image,deleteImgResponseMutableLiveData);
    }

    public MutableLiveData<FetchGalleryResponse> deleteImgLiveData(){
        return deleteImgResponseMutableLiveData;
    }
}
