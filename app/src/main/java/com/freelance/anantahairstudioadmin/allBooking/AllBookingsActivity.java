package com.freelance.anantahairstudioadmin.allBooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.adapter.AllBookingAdapter;
import com.freelance.anantahairstudioadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.viewModel.AllBookingViewModel;
import com.freelance.anantahairstudioadmin.databinding.ActivityAllBookingsBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;

import java.util.ArrayList;

public class AllBookingsActivity extends AppCompatActivity {

    ActivityAllBookingsBinding binding;
    AllBookingAdapter bookingAdapter;
    AllBookingViewModel allBookingViewModel;
    ArrayList<AllBookingResponse.Data.Result> bookingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_all_bookings);

      binding.bookingRecyclerView.setEmptyView(binding.emptyText);
      initialise();
      allBookingViewModel.allBookings("new","1");
      binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              bookingList.clear();
              allBookingViewModel.allBookings("new","1");
          }
      });
      observer();
    }

    private void observer() {
        allBookingViewModel.allBookingsLiveData().observe(this, new Observer<AllBookingResponse>() {
            @Override
            public void onChanged(AllBookingResponse allBookingResponse) {
              if (allBookingResponse.getData().getResults() != null) {
                  bookingList.addAll(allBookingResponse.getData().getResults());
                  bookingAdapter.notifyDataSetChanged();
                  binding.loader.setVisibility(View.GONE);
                  binding.swipe.setRefreshing(false);
              }
              else if (allBookingResponse.getData().getResults().size() == 0){
                  binding.loader.setVisibility(View.GONE);
                  binding.swipe.setRefreshing(false);
              }
            }
        });
    }

    private void initialise() {
        allBookingViewModel = new ViewModelProvider(this).get(AllBookingViewModel.class);
        bookingAdapter = new AllBookingAdapter(this,bookingList);
        binding.bookingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.bookingRecyclerView.setAdapter(bookingAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AllBookingsActivity.this, HomeActivity.class));
        finish();
    }
}