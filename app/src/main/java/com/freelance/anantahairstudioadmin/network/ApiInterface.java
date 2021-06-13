package com.freelance.anantahairstudioadmin.network;



import com.freelance.anantahairstudioadmin.allBooking.response.AcceptBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.BookingDetailsResponse;
import com.freelance.anantahairstudioadmin.notification.FcmResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("admin/booking?")
    Call<AllBookingResponse> allBooking(
            @Query("status") String status,
            @Query("fetch") String fetch
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("admin/booking?")
    Call<BookingDetailsResponse> allBookingDetails(
            @Query("fetch_details") String fetch
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("admin/booking")
    Call<AcceptBookingResponse> acceptBooking(
            @Field("accept") String bookingId
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("admin/booking")
    Call<AcceptBookingResponse> rejectBooking(
            @Field("reject") String bookingId
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("admin/booking")
    Call<AcceptBookingResponse> closeBooking(
            @Field("close") String bookingId
    );

    @Headers({
            "Content-Type: application/json",
            "Authorization:key=AAAAlZCQ3F8:APA91bEKy8G_coXnOmT7-kkvnDX4YtKqkVetcF7iawq8ICHKnX5YOoAsXzd3sTkaOMEt-eaDo69GqOCOyvxCSdyBD8N0VCKfUcRyAdkqSSUufUyz8tA0n-nqBYwZ96e9PJyZgid_NfJb"
    })
    @POST("https://fcm.googleapis.com/fcm/send")
    Call<FcmResponse> sendNotifications(
            @Body JsonObject jsonObject
    );
}
