package com.freelance.anantahairstudioadmin.allBooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.adapter.BookingDetailsAdapter;
import com.freelance.anantahairstudioadmin.allBooking.response.AcceptBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.response.BookingDetailsResponse;
import com.freelance.anantahairstudioadmin.allBooking.viewModel.AllBookingViewModel;
import com.freelance.anantahairstudioadmin.databinding.ActivityBookingDetailsBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;
import com.freelance.anantahairstudioadmin.network.RequestFormatter;
import com.freelance.anantahairstudioadmin.notification.NotificationViewModel;
import com.freelance.anantahairstudioadmin.utils.LocalTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookingDetailsActivity extends AppCompatActivity {

    ActivityBookingDetailsBinding binding;
    BookingDetailsAdapter adapter;
    AllBookingViewModel allBookingViewModel;
    String bookingId;
    ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList = new ArrayList<>();
    NotificationViewModel notificationViewModel;
    String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_details);
        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        getIntentData();
        initialise();
        observer();
        clickViews();
    }

    private void clickViews() {
        binding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBookingViewModel.acceptBooking(bookingId);
            }
        });

        binding.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBookingViewModel.rejectBooking(bookingId);
            }
        });
    }

    private void getIntentData() {
        bookingId = getIntent().getStringExtra("bookingId");
    }

    private void observer() {
        allBookingViewModel.bookingDetails(bookingId);
        allBookingViewModel.bookingDetailsLiveData().observe(this, new Observer<BookingDetailsResponse>() {
            @Override
            public void onChanged(BookingDetailsResponse bookingDetailsResponse) {
                if (bookingDetailsResponse != null) {
                    try {
                        if (bookingDetailsResponse.getData().getName() != null) {
                            binding.name.setText("Name: " + bookingDetailsResponse.getData().getName());
                        } else {
                            binding.name.setText("Name: N/A");
                        }
                        if (bookingDetailsResponse.getData().getPhone() != null) {
                            binding.phone.setText("Contact: " + bookingDetailsResponse.getData().getPhone());
                        } else {
                            binding.phone.setText("Contact: N/A");
                        }
                    } catch (Exception e) {
                    }
                    binding.bookingId.setText("BookingId: #" + bookingDetailsResponse.getData().getId());
                    long slot = Long.parseLong(bookingDetailsResponse.getData().getSlot());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = formatter.format(new Date(slot));
                    binding.dateTxt.setText("Date: " + dateString);
                    binding.timeTxt.setText("Time: " + LocalTime.getLocalTime(slot));
                    topic = bookingDetailsResponse.getData().getEmail().substring(0, bookingDetailsResponse.getData().getEmail().indexOf("@")).trim();
                    serviceArrayList.addAll(bookingDetailsResponse.getData().getServices());

                    binding.loader.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            }
        });


        allBookingViewModel.acceptBookingLiveData().observe(this, new Observer<AcceptBookingResponse>() {
            @Override
            public void onChanged(AcceptBookingResponse acceptBookingResponse) {
                if (acceptBookingResponse.getData() != null) {
                    Toast.makeText(BookingDetailsActivity.this, "Booking accepted successfully", Toast.LENGTH_SHORT).show();
                    notificationViewModel.sendNotification(RequestFormatter.sendNotification("/topics/" + topic, "Booking Accepted", "BookingId: " + bookingId, R.drawable.main_logo));
                    startActivity(new Intent(BookingDetailsActivity.this, AllBookingsActivity.class));
                    finish();
                }
            }
        });

        allBookingViewModel.rejectBookingLiveData().observe(this, new Observer<AcceptBookingResponse>() {
            @Override
            public void onChanged(AcceptBookingResponse acceptBookingResponse) {
                if (acceptBookingResponse.getData() != null) {
                    Toast.makeText(BookingDetailsActivity.this, "Booking rejected successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookingDetailsActivity.this, AllBookingsActivity.class));
                    finish();
                }
            }
        });

    }

    private void initialise() {
        allBookingViewModel = new ViewModelProvider(this).get(AllBookingViewModel.class);
        adapter = new BookingDetailsAdapter(this, serviceArrayList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(BookingDetailsActivity.this, AllBookingsActivity.class));
        finish();
    }
}