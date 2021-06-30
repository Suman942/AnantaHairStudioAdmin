package com.freelance.anantahairstudioadmin.addService.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.freelance.anantahairstudioadmin.addService.pojo.ServicesResponse;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.addService.repo.ServicesRepo;

import java.io.File;

public class ServicesViewModel extends ViewModel {

    MutableLiveData<ServicesResponse> servicesResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<UpdateServiceResponse> updateServiceResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<UpdateServiceResponse> deleteServiceResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<UpdateServiceResponse> createServiceResponseMutableLiveData = new MutableLiveData<>();

    public void getAllServices(String token){
        ServicesRepo.getInstance().allServices(token,servicesResponseMutableLiveData);
    }
    public MutableLiveData<ServicesResponse> getAllServicesLiveData(){
        return  servicesResponseMutableLiveData;
    }

    public void updateService(String serviceId, String categoryId, String price, String discountPrice, String name, String description, File file){
        ServicesRepo.getInstance().updateService(serviceId,categoryId,price,discountPrice,name,description,file,updateServiceResponseMutableLiveData);
    }

    public MutableLiveData<UpdateServiceResponse> updateServiceLiveData(){
        return  updateServiceResponseMutableLiveData;
    }

    public void deleteService(String serviceId){
        ServicesRepo.getInstance().deleteService(serviceId,deleteServiceResponseMutableLiveData);
    }

    public MutableLiveData<UpdateServiceResponse> deleteServiceLiveData(){
        return deleteServiceResponseMutableLiveData;
    }
    public void createService( String categoryId, String price, String discountPrice, String name, String description, File file){
        ServicesRepo.getInstance().createService(categoryId,price,discountPrice,name,description,file,createServiceResponseMutableLiveData);
    }

    public MutableLiveData<UpdateServiceResponse> createServiceLiveData(){
        return  createServiceResponseMutableLiveData;
    }
}

