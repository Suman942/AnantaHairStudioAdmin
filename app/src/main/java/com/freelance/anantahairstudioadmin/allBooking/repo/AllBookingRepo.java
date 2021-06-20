package com.freelance.anantahairstudioadmin.allBooking.repo;

import androidx.lifecycle.MutableLiveData;

import com.freelance.anantahairstudioadmin.allBooking.response.AcceptBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.BookingDetailsResponse;
import com.freelance.anantahairstudioadmin.network.ApiClient;
import com.freelance.anantahairstudioadmin.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllBookingRepo {
    private static AllBookingRepo allBookingRepo;
    private ApiInterface apiInterface;

    public AllBookingRepo() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public static AllBookingRepo getInstance() {
        if (allBookingRepo == null) {
            allBookingRepo = new AllBookingRepo();
        }
        return allBookingRepo;
    }

    public void allBooking(String status, String fetch,String page ,MutableLiveData<AllBookingResponse>mutableLiveData){
        apiInterface.allBooking(status,fetch,page).enqueue(new Callback<AllBookingResponse>() {
            @Override
            public void onResponse(Call<AllBookingResponse> call, Response<AllBookingResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AllBookingResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

    public void bookingDetails(String fetchDetails, MutableLiveData<BookingDetailsResponse> mutableLiveData){
        apiInterface.allBookingDetails(fetchDetails).enqueue(new Callback<BookingDetailsResponse>() {
            @Override
            public void onResponse(Call<BookingDetailsResponse> call, Response<BookingDetailsResponse> response) {
                if (response.code() ==200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BookingDetailsResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

    public void acceptBooking(String bookingId, MutableLiveData<AcceptBookingResponse> mutableLiveData){
        apiInterface.acceptBooking(bookingId).enqueue(new Callback<AcceptBookingResponse>() {
            @Override
            public void onResponse(Call<AcceptBookingResponse> call, Response<AcceptBookingResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AcceptBookingResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

    public void rejectBooking(String bookingId, MutableLiveData<AcceptBookingResponse> mutableLiveData){
        apiInterface.rejectBooking(bookingId).enqueue(new Callback<AcceptBookingResponse>() {
            @Override
            public void onResponse(Call<AcceptBookingResponse> call, Response<AcceptBookingResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AcceptBookingResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

    public void closeBooking(String bookingId, MutableLiveData<AcceptBookingResponse> mutableLiveData){
        apiInterface.closeBooking(bookingId).enqueue(new Callback<AcceptBookingResponse>() {
            @Override
            public void onResponse(Call<AcceptBookingResponse> call, Response<AcceptBookingResponse> response) {
                if (response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AcceptBookingResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }

}
