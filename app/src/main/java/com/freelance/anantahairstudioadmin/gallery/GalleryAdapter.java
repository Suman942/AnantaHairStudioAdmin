package com.freelance.anantahairstudioadmin.gallery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.utils.GlideHelper;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    Context context;
    ArrayList<FetchGalleryResponse.Data.Image> imageList ;
    String url ;

    public interface Callback{
        void setImageToImageView(String imageUrl);
    }
    Callback callback;

    public GalleryAdapter(Context context, ArrayList<FetchGalleryResponse.Data.Image> imageList,String  url) {
        this.context = context;
        this.imageList = imageList;
        this.url = url;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_view_layout,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
         url = "https://xbytelab.com/projects/ananta-salon/image/gallery/";

        GlideHelper.setImageView(context,holder.galleryImg,url+imageList.get(position).getImage(),R.drawable.ic_image_placeholder);
        holder.galleryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotoViewActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position",position);
                intent.putExtra("image",url+imageList.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryImg;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryImg = itemView.findViewById(R.id.galleryImg);
        }
    }
}
