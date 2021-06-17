package com.freelance.anantahairstudioadmin.allBooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.response.BookingDetailsResponse;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class BookingDetailsAdapter extends RecyclerView.Adapter<BookingDetailsAdapter.BookingDetailViewHolder> {

    Context context;
    ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList;
    public BookingDetailsAdapter(Context context, ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList) {
        this.context = context;
        this.serviceArrayList = serviceArrayList;
    }

    @NonNull
    @Override
    public BookingDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_list,parent,false);
        return new BookingDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingDetailViewHolder holder, int position) {
        holder.serviceName.setText(serviceArrayList.get(position).getName());
        holder.price.setText("\u20B9 "+serviceArrayList.get(position).getPrice());
        holder.discount.setText("\u20B9 "+serviceArrayList.get(position).getDiscountedPrice()+" OFF");
        holder.noOfIndividuals.setText("Individuals -  "+serviceArrayList.get(position).getIndividuals());

    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    public class BookingDetailViewHolder extends RecyclerView.ViewHolder {
            TextView serviceName,discount, price, noOfIndividuals;
        public BookingDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName =  itemView.findViewById(R.id.serviceNameTxt);
            discount =  itemView.findViewById(R.id.discountAmount);
            price =  itemView.findViewById(R.id.amountTxt);
            noOfIndividuals =  itemView.findViewById(R.id.noOfIndividualText);

        }
    }
}
