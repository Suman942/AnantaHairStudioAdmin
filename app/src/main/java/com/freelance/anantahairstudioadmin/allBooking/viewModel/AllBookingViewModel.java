package com.freelance.anantahairstudioadmin.allBooking.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.freelance.anantahairstudioadmin.allBooking.repo.AllBookingRepo;
import com.freelance.anantahairstudioadmin.allBooking.response.AcceptBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.BookingDetailsResponse;

public class AllBookingViewModel extends ViewModel {
    MutableLiveData<AllBookingResponse> allBookingResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AllBookingResponse> acceptedBookingLiveData = new MutableLiveData<>();
    MutableLiveData<BookingDetailsResponse> bookingDetailsResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AcceptBookingResponse> acceptBookingResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AcceptBookingResponse> rejectBookingResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AcceptBookingResponse> closeBookingResponseMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AllBookingResponse> bookingHistoryMutableLiveData = new MutableLiveData<>();




    public void bookingHistory(String status,String fetch,String page){
        AllBookingRepo.getInstance().allBooking(status,fetch,page,bookingHistoryMutableLiveData);
    }

    public MutableLiveData<AllBookingResponse> bookingHistoryLiveData(){
        return bookingHistoryMutableLiveData;
    }


    public void allBookings(String status,String fetch,String page){
        AllBookingRepo.getInstance().allBooking(status,fetch,page,allBookingResponseMutableLiveData);
    }

    public MutableLiveData<AllBookingResponse> allBookingsLiveData(){
        return allBookingResponseMutableLiveData;
    }

    public void allAcceptedBookings(String status,String fetch,String page){
        AllBookingRepo.getInstance().allBooking(status,fetch,page,acceptedBookingLiveData);
    }

    public MutableLiveData<AllBookingResponse> allAcceptedBookingsLiveData(){
        return acceptedBookingLiveData;
    }

    public void bookingDetails(String fetchDetails){
        AllBookingRepo.getInstance().bookingDetails(fetchDetails,bookingDetailsResponseMutableLiveData);
    }
    public MutableLiveData<BookingDetailsResponse> bookingDetailsLiveData(){
        return bookingDetailsResponseMutableLiveData;
    }

    public void acceptBooking(String bookingId){
        AllBookingRepo.getInstance().acceptBooking(bookingId,acceptBookingResponseMutableLiveData);
    }
    public MutableLiveData<AcceptBookingResponse> acceptBookingLiveData(){
        return acceptBookingResponseMutableLiveData;
    }

    public void rejectBooking(String bookingId){
        AllBookingRepo.getInstance().rejectBooking(bookingId,rejectBookingResponseMutableLiveData);
    }
    public MutableLiveData<AcceptBookingResponse> rejectBookingLiveData(){
        return rejectBookingResponseMutableLiveData;
    }

    public void closeBooking(String bookingId){
        AllBookingRepo.getInstance().closeBooking(bookingId,closeBookingResponseMutableLiveData);
    }

    public MutableLiveData<AcceptBookingResponse> closeBookingLiveData(){
        return closeBookingResponseMutableLiveData;
    }
}
