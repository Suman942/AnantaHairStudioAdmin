package com.freelance.anantahairstudioadmin.allBooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudioadmin.R;

public class AllBookingAdapter extends RecyclerView.Adapter<AllBookingAdapter.AllBookingViewHolder> {

    Context context;

    public AllBookingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public AllBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_booking_layout,parent,false);
        return new AllBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBookingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class AllBookingViewHolder extends RecyclerView.ViewHolder {
        View statusBackground;
        TextView statusTxt,time,date,bookingId,pay;
        public AllBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            statusBackground = itemView.findViewById(R.id.statusBackground);
            statusTxt = itemView.findViewById(R.id.adStatusTxt);
            time = itemView.findViewById(R.id.timeTxt);
            date = itemView.findViewById(R.id.dateTxt);
            bookingId = itemView.findViewById(R.id.serviceNameTxt);
            pay = itemView.findViewById(R.id.payTxt);
        }
    }
}
