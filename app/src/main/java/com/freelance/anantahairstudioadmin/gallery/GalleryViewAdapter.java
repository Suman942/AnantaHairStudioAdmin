package com.freelance.anantahairstudioadmin.gallery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.utils.GlideHelper;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.ProfessionalGalleryViewHolder>{

    Context context;
    ArrayList<FetchGalleryResponse.Data.Image> photoList;
    public  interface Callback{
        void deletePhoto(String img , int position);
    }
    Callback callback;
    public GalleryViewAdapter(Context context, ArrayList<FetchGalleryResponse.Data.Image> photoList,Callback callback) {
        this.context = context;
        this.photoList = photoList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ProfessionalGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_image_view, parent, false);
        return new ProfessionalGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessionalGalleryViewHolder holder, int position) {

        GlideHelper.setImageView(context,holder.images,""+photoList.get(position).getImage(),R.drawable.ic_image_placeholder);
        Log.i("file",""+photoList.get(position).getImage());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.deletePhoto(photoList.get(position).getImage(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ProfessionalGalleryViewHolder extends RecyclerView.ViewHolder {
        PhotoView images;
        CardView delete;
        public ProfessionalGalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.images);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
