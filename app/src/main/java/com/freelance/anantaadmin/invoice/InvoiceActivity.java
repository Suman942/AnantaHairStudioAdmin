package com.freelance.anantaadmin.invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantaadmin.R;
import com.freelance.anantaadmin.allBooking.response.BookingDetailsResponse;
import com.freelance.anantaadmin.allBooking.viewModel.AllBookingViewModel;
import com.freelance.anantaadmin.databinding.ActivityInvoiceBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity implements InvoiceAdapter.Callback{
    ActivityInvoiceBinding binding;
    AllBookingViewModel allBookingViewModel;
    ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList = new ArrayList<>();
    InvoiceAdapter adapter;
    String bookingId;
  ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_invoice);
        allBookingViewModel = new ViewModelProvider(this).get(AllBookingViewModel.class);
        bookingId = getIntent().getStringExtra("bookingId");
        initialise();
        observer();
        clickViews();
    }

    private void clickViews() {
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareInvoice();
            }
        });


    }

    private void shareInvoice() {
        Bitmap bitmap = getBitmapFromView(binding.invooice);

        try {
            File file = new File(getExternalCacheDir(), "Invoice.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            Uri imageUri = FileProvider.getUriForFile(
                    InvoiceActivity.this,
                    "com.freelance.anantaadmin.provider", //(use your app signature + ".provider" )
                    file);
            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            intent.setType("image/*");
            startActivity(Intent.createChooser(intent, "Share ia"));
        } catch (Exception e) {
            Log.e("shareError", " " + e.getMessage());
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    private void initialise() {
        adapter = new InvoiceAdapter(this, serviceArrayList,this::totalPrice);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Invoice Generating");
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
    }

    private void observer() {
        allBookingViewModel.bookingDetails(bookingId);
        allBookingViewModel.bookingDetailsLiveData().observe(this, new Observer<BookingDetailsResponse>() {
            @Override
            public void onChanged(BookingDetailsResponse bookingDetailsResponse) {
                if (bookingDetailsResponse != null) {
                    serviceArrayList.addAll(bookingDetailsResponse.getData().getServices());
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }
        });



    }

    @Override
    public void totalPrice(int totalPrice, int totalDiscount, int subtotal) {
        binding.price.setText("\u20B9 " + String.valueOf(totalPrice));
        binding.discount.setText("\u20B9 " + String.valueOf(totalDiscount));
        binding.amount.setText("\u20B9 " + String.valueOf(subtotal));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(InvoiceActivity.this, AcceptedBookingDetailsActivity.class));
        finish();
    }
}