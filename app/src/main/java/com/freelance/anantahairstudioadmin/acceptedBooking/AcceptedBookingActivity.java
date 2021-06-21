package com.freelance.anantahairstudioadmin.acceptedBooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.adapter.AllBookingAdapter;
import com.freelance.anantahairstudioadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantahairstudioadmin.allBooking.viewModel.AllBookingViewModel;
import com.freelance.anantahairstudioadmin.databinding.ActivityAcceptedBookingBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;

import java.util.ArrayList;

public class AcceptedBookingActivity extends AppCompatActivity {

    ActivityAcceptedBookingBinding binding;
    AcceptedBookingAdapter bookingAdapter;
    AllBookingViewModel allBookingViewModel;
    ArrayList<AllBookingResponse.Data.Result> bookingList = new ArrayList<>();
    int totalItems, scrollOutItems, currentVisibleItems;
    LinearLayoutManager mLayoutManager;
    boolean isLoading = false;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accepted_booking);
        binding.bookingRecyclerView.setEmptyView(binding.emptyText);
        initialise();
        mLayoutManager = new LinearLayoutManager(this);
        binding.bookingRecyclerView.setLayoutManager(mLayoutManager);
        observer();


        binding.bookingRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading ) {
                    currentVisibleItems = mLayoutManager.getChildCount();
                    totalItems = mLayoutManager.getItemCount();
                    scrollOutItems = mLayoutManager.findFirstVisibleItemPosition();
                    if ((currentVisibleItems + scrollOutItems == totalItems)) {

                        isLoading = true;
                        page++;
                        Log.i("page", "pageNo: " + page);
                        Log.i("data", "response1: ");

                        allBookingViewModel.allAcceptedBookings("accepted","1",String.valueOf(page));
                        binding.paginationLoader.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
    private void observer() {
        allBookingViewModel.allAcceptedBookings("accepted","1",String.valueOf(page));

        allBookingViewModel.allAcceptedBookingsLiveData().observe(this, new Observer<AllBookingResponse>() {
            @Override
            public void onChanged(AllBookingResponse allBookingResponse) {
                if (allBookingResponse.getData().getResults() != null) {
                    bookingList.addAll(allBookingResponse.getData().getResults());
                    bookingAdapter.notifyDataSetChanged();
                    binding.loader.setVisibility(View.GONE);
                    binding.paginationLoader.setVisibility(View.GONE);
                }
                else if (allBookingResponse.getData().getResults().size() == 0){
                    binding.loader.setVisibility(View.GONE);
                    binding.paginationLoader.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initialise() {
        allBookingViewModel = new ViewModelProvider(this).get(AllBookingViewModel.class);
        bookingAdapter = new AcceptedBookingAdapter(this,bookingList);
        binding.bookingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.bookingRecyclerView.setAdapter(bookingAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(AcceptedBookingActivity.this, HomeActivity.class));
        finish();
    }
}