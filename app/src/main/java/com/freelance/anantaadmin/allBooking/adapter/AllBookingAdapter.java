package com.freelance.anantaadmin.allBooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantaadmin.R;
import com.freelance.anantaadmin.allBooking.BookingDetailsActivity;
import com.freelance.anantaadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantaadmin.utils.LocalTime;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllBookingAdapter extends RecyclerView.Adapter<AllBookingAdapter.AllBookingViewHolder> {

    Context context;
    ArrayList<AllBookingResponse.Data.Result> bookingList;

    public AllBookingAdapter(Context context, ArrayList<AllBookingResponse.Data.Result> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @Override
    public AllBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_booking_layout,parent,false);
        return new AllBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBookingViewHolder holder, int position) {

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookingDetailsActivity.class);
                intent.putExtra("bookingId",bookingList.get(position).getId());
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

        holder.bookingId.setText("BookingId: #"+bookingList.get(position).getId());
        long slot = Long.parseLong(bookingList.get(position).getSlot());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(slot));
        holder.date.setText("Date: "+dateString);

        holder.time.setText("Time: "+ LocalTime.getLocalTime(slot));

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class AllBookingViewHolder extends RecyclerView.ViewHolder {
//        View statusBackground;
        TextView time,date,bookingId;
        MaterialCardView layout;
        public AllBookingViewHolder(@NonNull View itemView) {
            super(itemView);
//            statusBackground = itemView.findViewById(R.id.statusBackground);
//            statusTxt = itemView.findViewById(R.id.adStatusTxt);
            time = itemView.findViewById(R.id.timeTxt);
            date = itemView.findViewById(R.id.dateTxt);
            bookingId = itemView.findViewById(R.id.serviceNameTxt);
            layout = itemView.findViewById(R.id.layout);

        }
    }
}
