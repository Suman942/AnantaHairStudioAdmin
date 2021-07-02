package com.freelance.anantahairstudioadmin.network;


import com.freelance.anantahairstudioadmin.addService.pojo.ServicesResponse;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.adminInfo.ContactUpdateResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.AcceptBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.BookingDetailsResponse;
import com.freelance.anantahairstudioadmin.gallery.FetchGalleryResponse;
import com.freelance.anantahairstudioadmin.notification.FcmResponse;
import com.freelance.anantahairstudioadmin.signup.Authentication;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("admin/booking?")
    Call<AllBookingResponse> allBooking(
            @Query("status") String status,
            @Query("fetch") String fetch,
            @Query("page") String page

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

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("services")
    Call<ServicesResponse> services(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("login")
    Call<Authentication> authentication(
            @Field("email") String email
    );



    @Headers({
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @Multipart
    @POST("admin/service")
    Call<UpdateServiceResponse> deleteService(
            @Part("delete") RequestBody service_id
            );


    @Headers({
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @Multipart
    @POST("admin/service")
    Call<UpdateServiceResponse> updateService(
            @Part("service_id") RequestBody service_id,
            @Part("category_id") RequestBody category_id,
            @Part("price") RequestBody price,
            @Part("discounted_price") RequestBody discounted_price,
            @Part("name") RequestBody name,
            @Part("info") RequestBody info,
            @Part MultipartBody.Part file

    );

    @Headers({
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @Multipart
    @POST("admin/service")
    Call<UpdateServiceResponse> updateService(
            @Part("service_id") RequestBody service_id,
            @Part("category_id") RequestBody category_id,
            @Part("price") RequestBody price,
            @Part("discounted_price") RequestBody discounted_price,
            @Part("name") RequestBody name,
            @Part("info") RequestBody info
//            @Part MultipartBody.Part file

    );

    @Headers({
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @Multipart
    @POST("admin/service")
    Call<UpdateServiceResponse> createService(
            @Part("category_id") RequestBody category_id,
            @Part("price") RequestBody price,
            @Part("discounted_price") RequestBody discounted_price,
            @Part("name") RequestBody name,
            @Part("info") RequestBody info,
            @Part MultipartBody.Part file
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("admin/update")
    Call<UpdateServiceResponse> updateAdminDetail(
            @Field("service_id") String service_id,
            @Field("category_id") String category_id,
            @Field("business_data") String business_data,
            @Field("phone") String phone,
            @Field("whatsapp") String whatsapp,
            @Field("email") String email,
            @Field("facebook") String facebook,
            @Field("youtube") String youtube,
            @Field("instagram") String instagram,
            @Field("website") String website
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("fetch?business_info=1")
    Call<ContactUpdateResponse> getAdminDetails(
            @Header("Authorization") String token
    );

    @Headers({
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @Multipart
    @POST("admin/gallery")
    Call<UpdateServiceResponse> uploadPic(
            @Part MultipartBody.Part file);


    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @GET("fetch?gallery")
    Call<FetchGalleryResponse> getGalleryPic(
            @Header("Authorization") String token
    );

    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "secret: SECn83ninsoPi40ZjfHjeQwUdfomns9d",
    })
    @FormUrlEncoded
    @POST("admin/gallery")
    Call<FetchGalleryResponse> deletePhoto(
            @Field("delete") String image
    );
}
