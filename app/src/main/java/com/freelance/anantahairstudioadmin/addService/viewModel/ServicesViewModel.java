package com.freelance.anantahairstudioadmin.addService.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.freelance.anantahairstudioadmin.addService.pojo.ServicesResponse;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.addService.repo.ServicesRepo;

public class ServicesViewModel extends ViewModel {

    MutableLiveData<ServicesResponse> servicesResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<UpdateServiceResponse> updateServiceResponseMutableLiveData = new MutableLiveData<>();

    public void getAllServices(String token){
        ServicesRepo.getInstance().allServices(token,servicesResponseMutableLiveData);
    }
    public MutableLiveData<ServicesResponse> getAllServicesLiveData(){
        return  servicesResponseMutableLiveData;
    }

    public void updateService(String serviceId,String categoryId,String price, String discountPrice,String name,String description){
        ServicesRepo.getInstance().updateService(serviceId,categoryId,price,discountPrice,name,description,updateServiceResponseMutableLiveData);
    }

    public MutableLiveData<UpdateServiceResponse> updateServiceLiveData(){
        return  updateServiceResponseMutableLiveData;
    }
}

