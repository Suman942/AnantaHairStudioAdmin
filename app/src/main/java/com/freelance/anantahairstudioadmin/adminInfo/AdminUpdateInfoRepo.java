package com.freelance.anantahairstudioadmin.adminInfo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.addService.repo.ServicesRepo;
import com.freelance.anantahairstudioadmin.network.ApiClient;
import com.freelance.anantahairstudioadmin.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminUpdateInfoRepo {
    private static AdminUpdateInfoRepo adminUpdateInfoRepo;
    private ApiInterface apiInterface;



    public AdminUpdateInfoRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static AdminUpdateInfoRepo getInstance() {
        if (adminUpdateInfoRepo == null) {
            adminUpdateInfoRepo = new AdminUpdateInfoRepo();
        }
        return adminUpdateInfoRepo;
    }

    public void updateAdminInfo(String serviceId, String categoryId, String businessData, String phone, String watzapp, String email, MutableLiveData<UpdateServiceResponse> mutableLiveData){
        apiInterface.updateAdminDetail(serviceId,categoryId,businessData,phone,watzapp,email).enqueue(new Callback<UpdateServiceResponse>() {
            @Override
            public void onResponse(Call<UpdateServiceResponse> call, Response<UpdateServiceResponse> response) {
                if (response.code() == 200){
                    Log.i("info","updated");
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UpdateServiceResponse> call, Throwable t) {
                Log.i("info",""+t.getMessage());
                mutableLiveData.setValue(null);
            }
        });
    }

    public void adminInfo(String token,MutableLiveData<ContactUpdateResponse> mutableLiveData){
        apiInterface.getAdminDetails(token).enqueue(new Callback<ContactUpdateResponse>() {
            @Override
            public void onResponse(Call<ContactUpdateResponse> call, Response<ContactUpdateResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactUpdateResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }
}
