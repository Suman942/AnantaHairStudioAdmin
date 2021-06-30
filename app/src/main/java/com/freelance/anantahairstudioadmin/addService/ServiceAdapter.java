package com.freelance.anantahairstudioadmin.addService;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.ServicesResponse;
import com.freelance.anantahairstudioadmin.utils.GlideHelper;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    Context context;
    ArrayList<ServicesResponse.Data.Service> serviceList;
    public interface Callback{
        void delete(String id);
    }
    Callback callback;
    public ServiceAdapter(Context context,ArrayList<ServicesResponse.Data.Service> serviceList,Callback callback) {
        this.context = context;
        this.serviceList = serviceList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.servicelist_layout,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {

        GlideHelper.setImageView(context,holder.serviceImg,serviceList.get(position).getImg(),R.drawable.ic_image_placeholder);
        if (serviceList.get(position).getCategoryId().equals("100")) {
            holder.categoryTxt.setText("Hair cut");
        }
        else if (serviceList.get(position).getCategoryId().equals("101")) {
            holder.categoryTxt.setText("Shaving");
        }
        else if (serviceList.get(position).getCategoryId().equals("102")) {
            holder.categoryTxt.setText("D-tan");
        }
        else if (serviceList.get(position).getCategoryId().equals("103")) {
            holder.categoryTxt.setText("Facial");
        }

        else if (serviceList.get(position).getCategoryId().equals("104")) {
            holder.categoryTxt.setText("Straightening");
        }
        else if (serviceList.get(position).getCategoryId().equals("105")) {
            holder.categoryTxt.setText("Pedicure");
        }
        else if (serviceList.get(position).getCategoryId().equals("106")) {
            holder.categoryTxt.setText("Bride/Groom");
        }
        else if (serviceList.get(position).getCategoryId().equals("107")) {
            holder.categoryTxt.setText("Manicure");
        }
        else if (serviceList.get(position).getCategoryId().equals("108")) {
            holder.categoryTxt.setText("Massage");
        }
        else if (serviceList.get(position).getCategoryId().equals("109")) {
            holder.categoryTxt.setText("Waxing");
        }
        else if (serviceList.get(position).getCategoryId().equals("110")) {
            holder.categoryTxt.setText("Hair");
        }
        else if (serviceList.get(position).getCategoryId().equals("111")) {
            holder.categoryTxt.setText("Child mundan");
        }
        else if (serviceList.get(position).getCategoryId().equals("112")) {
            holder.categoryTxt.setText("Eye brow");
        }
        else if (serviceList.get(position).getCategoryId().equals("113")) {
            holder.categoryTxt.setText("Dandruff");
        }
        else if (serviceList.get(position).getCategoryId().equals("114")) {
            holder.categoryTxt.setText("Spa");
        }
        else if (serviceList.get(position).getCategoryId().equals("115")) {
            holder.categoryTxt.setText("Colour");
        }
        else if (serviceList.get(position).getCategoryId().equals("116")) {
            holder.categoryTxt.setText("Other");
        }
        holder.amount.setText("\u20B9 "+serviceList.get(position).getPrice());
        holder.discountAmount.setText("\u20B9 "+serviceList.get(position).getDiscountedPrice() + " OFF");
        holder.serviceName.setText(serviceList.get(position).getName());

        holder.serviceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callback.serviceDetails("1");
                Intent intent = new Intent(context,AddServiceActivity.class);
                intent.putExtra("serviceName",serviceList.get(position).getName());
                intent.putExtra("serviceImg",serviceList.get(position).getImg());
                intent.putExtra("id",serviceList.get(position).getId());
                intent.putExtra("price",serviceList.get(position).getPrice());
                intent.putExtra("discountedPrice",serviceList.get(position).getDiscountedPrice());
                intent.putExtra("categoryId",serviceList.get(position).getCategoryId());
                intent.putExtra("info",serviceList.get(position).getInfo());

                context.startActivity(intent);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.delete(serviceList.get(position).getId());
            }
        });
        Log.i("img",""+serviceList.get(0).getImg());

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImg,delete;
        TextView serviceName, categoryTxt,amount,originalAmount, discountAmount;
        MaterialCardView serviceLayout;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImg = itemView.findViewById(R.id.serviceImg);
            serviceName = itemView.findViewById(R.id.serviceNameTxt);
            categoryTxt = itemView.findViewById(R.id.categoryText);
            serviceLayout = itemView.findViewById(R.id.serviceLayout);
            amount = itemView.findViewById(R.id.amountTxt);
            discountAmount = itemView.findViewById(R.id.discountAmount);
            delete = itemView.findViewById(R.id.delete);

        }
    }

}
