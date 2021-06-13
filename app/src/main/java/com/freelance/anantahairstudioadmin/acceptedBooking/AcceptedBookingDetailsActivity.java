package com.freelance.anantahairstudioadmin.acceptedBooking;

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
import com.freelance.anantahairstudioadmin.databinding.ActivityAcceptedBookingBinding;
import com.freelance.anantahairstudioadmin.databinding.ActivityAcceptedBookingDetailsBinding;
import com.freelance.anantahairstudioadmin.invoice.InvoiceActivity;
import com.freelance.anantahairstudioadmin.utils.LocalTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AcceptedBookingDetailsActivity extends AppCompatActivity {

    ActivityAcceptedBookingDetailsBinding binding;
    BookingDetailsAdapter adapter ;
    ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList = new ArrayList<>();
    String bookingId;
    AllBookingViewModel allBookingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_accepted_booking_details);
        allBookingViewModel = new ViewModelProvider(this).get(AllBookingViewModel.class);

        initilaise();
        getIntentData();
        observer();
        clickViews();
    }

    private void clickViews() {
        binding.markDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBookingViewModel.closeBooking(bookingId);
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
                if (bookingDetailsResponse != null){
                    binding.bookingId.setText("BookingId: #"+bookingDetailsResponse.getData().getId());
                    long slot = Long.parseLong(bookingDetailsResponse.getData().getSlot());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = formatter.format(new Date(slot));
                    binding.dateTxt.setText("Date: "+dateString);
                    binding.timeTxt.setText("Time: "+ LocalTime.getLocalTime(slot));

                    serviceArrayList.addAll(bookingDetailsResponse.getData().getServices());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        allBookingViewModel.closeBookingLiveData().observe(this, new Observer<AcceptBookingResponse>() {
            @Override
            public void onChanged(AcceptBookingResponse acceptBookingResponse) {
                if (acceptBookingResponse != null){
                    Toast.makeText(AcceptedBookingDetailsActivity.this, "Booking closed successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AcceptedBookingDetailsActivity.this,AcceptedBookingActivity.class));
                    finish();
                }
            }
        });
    }

    private void initilaise() {
        adapter = new BookingDetailsAdapter(this,serviceArrayList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        binding.billGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceptedBookingDetailsActivity.this, InvoiceActivity.class);
                intent.putExtra("bookingId","bookingId");
                startActivity(intent);
            }
        });
    }
}