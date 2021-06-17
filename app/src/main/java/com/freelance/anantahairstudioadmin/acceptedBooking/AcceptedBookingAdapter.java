package com.freelance.anantahairstudioadmin.acceptedBooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.BookingDetailsActivity;
import com.freelance.anantahairstudioadmin.allBooking.response.AllBookingResponse;
import com.freelance.anantahairstudioadmin.utils.LocalTime;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AcceptedBookingAdapter extends RecyclerView.Adapter<AcceptedBookingAdapter.AcceptedViewHolder> {

    Context context;
    ArrayList<AllBookingResponse.Data.Result> bookingList;

    public AcceptedBookingAdapter(Context context, ArrayList<AllBookingResponse.Data.Result> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @Override
    public AcceptedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_booking_layout, parent, false);
        return new AcceptedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcceptedViewHolder holder, int position) {

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AcceptedBookingDetailsActivity.class);
                intent.putExtra("bookingId", bookingList.get(position).getId());
                intent.putExtra("position", position);
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

    public class AcceptedViewHolder extends RecyclerView.ViewHolder {
        //        View statusBackground;
        TextView time, date, bookingId, pay;
        MaterialCardView layout;

        public AcceptedViewHolder(@NonNull View itemView) {
            super(itemView);
//            statusBackground = itemView.findViewById(R.id.statusBackground);
//            statusTxt = itemView.findViewById(R.id.adStatusTxt);
            time = itemView.findViewById(R.id.timeTxt);
            date = itemView.findViewById(R.id.dateTxt);
            bookingId = itemView.findViewById(R.id.serviceNameTxt);
            pay = itemView.findViewById(R.id.payTxt);
            layout = itemView.findViewById(R.id.layout);

        }
    }
}