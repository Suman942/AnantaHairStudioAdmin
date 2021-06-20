package com.freelance.anantahairstudioadmin.gallery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.utils.GlideHelper;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;


public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.ProfessionalGalleryViewHolder>{

    Context context;
    ArrayList<FetchGalleryResponse.Data.Image> photoList;
    String url;
    public GalleryViewAdapter(Context context, ArrayList<FetchGalleryResponse.Data.Image> photoList,String url) {
        this.context = context;
        this.photoList = photoList;
        this.url = url;
    }

    @NonNull
    @Override
    public ProfessionalGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_image_view, parent, false);
        return new ProfessionalGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessionalGalleryViewHolder holder, int position) {
        GlideHelper.setImageView(context,holder.images,""+url+photoList.get(position).getImage(),R.drawable.ic_image_placeholder);
        Log.i("file",""+url+photoList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ProfessionalGalleryViewHolder extends RecyclerView.ViewHolder {
        PhotoView images;
        public ProfessionalGalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.images);
        }
    }
}
