package com.freelance.anantahairstudioadmin.adminInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;

public class AdminInfoViewModel extends ViewModel {
    MutableLiveData<UpdateServiceResponse> updateAdminInfo = new MutableLiveData<>();

    public void updateAdminInfo(String serviceId,String categoryId,String businessData,String phone,String watzapp,String email){
        AdminUpdateInfoRepo.getInstance().updateAdminInfo(serviceId,categoryId,businessData,phone,watzapp,email,updateAdminInfo);
    }

    public MutableLiveData<UpdateServiceResponse> updateAdminInfoLiveData(){
        return updateAdminInfo;
    }
}
