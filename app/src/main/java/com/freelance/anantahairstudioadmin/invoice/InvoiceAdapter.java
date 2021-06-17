package com.freelance.anantahairstudioadmin.invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.allBooking.response.BookingDetailsResponse;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {
    Context context;
    ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList;
    public InvoiceAdapter(Context context, ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList) {
        this.context = context;
        this.serviceArrayList = serviceArrayList;
    }    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_layout,parent,false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        holder.serviceName.setText(serviceArrayList.get(position).getName());

        holder.price.setText("\u20B9 "+Integer.parseInt(serviceArrayList.get(position).getIndividuals())*Integer.parseInt(serviceArrayList.get(position).getPrice()));
        holder.discount.setText("\u20B9 "+serviceArrayList.get(position).getDiscountedPrice()+" OFF");
        holder.noOfIndividuals.setText(serviceArrayList.get(position).getIndividuals());

    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    public class InvoiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName,discount, price, noOfIndividuals;
        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.service);
            discount = itemView.findViewById(R.id.discount);
            price = itemView.findViewById(R.id.price);
            noOfIndividuals = itemView.findViewById(R.id.quantity);

        }
    }
}