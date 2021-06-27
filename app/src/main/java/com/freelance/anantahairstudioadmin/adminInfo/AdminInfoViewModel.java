package com.freelance.anantahairstudioadmin.adminInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;

public class AdminInfoViewModel extends ViewModel {
    MutableLiveData<UpdateServiceResponse> updateAdminInfo = new MutableLiveData<>();

    MutableLiveData<ContactUpdateResponse> contactUpdateResponseMutableLiveData = new MutableLiveData<>();

    public void updateAdminInfo(String serviceId,String categoryId,String businessData,String phone,String watzapp,String email,
                                String fb,String instagram,String youtube,String website){
        AdminUpdateInfoRepo.getInstance().updateAdminInfo(serviceId,categoryId,businessData,phone,watzapp,email,fb,instagram,youtube,
                website,updateAdminInfo);
    }

    public MutableLiveData<UpdateServiceResponse> updateAdminInfoLiveData(){
        return updateAdminInfo;
    }

    public void getAdminDetails(String token){
        AdminUpdateInfoRepo.getInstance().adminInfo(token,contactUpdateResponseMutableLiveData);

    }
    public MutableLiveData<ContactUpdateResponse> getAdminDetailsLiveData(){
        return contactUpdateResponseMutableLiveData;
    }
}
