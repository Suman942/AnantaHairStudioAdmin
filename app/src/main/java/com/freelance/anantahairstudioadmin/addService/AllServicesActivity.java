package com.freelance.anantahairstudioadmin.addService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.addService.pojo.ServicesResponse;
import com.freelance.anantahairstudioadmin.addService.pojo.UpdateServiceResponse;
import com.freelance.anantahairstudioadmin.addService.viewModel.ServicesViewModel;
import com.freelance.anantahairstudioadmin.adminInfo.AdminInfoActivity;
import com.freelance.anantahairstudioadmin.databinding.ActivityAllServicesBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;
import com.freelance.anantahairstudioadmin.utils.PrefManager;

import java.util.ArrayList;

public class AllServicesActivity extends AppCompatActivity implements ServiceAdapter.Callback {

    ActivityAllServicesBinding binding;
    ServiceAdapter serviceAdapter;
    ArrayList<ServicesResponse.Data.Service> serviceList = new ArrayList<>();
    ServicesViewModel servicesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_services);
        servicesViewModel  = new ViewModelProvider(this).get(ServicesViewModel.class);
        PrefManager.getInstance(this,true);
        clickView();
        initialise();
        observer();

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serviceList.clear();
                servicesViewModel.getAllServices(PrefManager.getInstance().getString(R.string.authToken));

            }
        });
    }

    private void observer() {
        servicesViewModel.getAllServices(PrefManager.getInstance().getString(R.string.authToken));
        servicesViewModel.getAllServicesLiveData().observe(this, new Observer<ServicesResponse>() {
            @Override
            public void onChanged(ServicesResponse servicesResponse) {
                if (servicesResponse != null){
                    serviceList.addAll(servicesResponse.getData().getServices());
                    serviceAdapter.notifyDataSetChanged();
                    binding.loader.setVisibility(View.GONE);
                    binding.swipe.setRefreshing(false);
                }
                if (servicesResponse.getData().getServices().size() == 0){
                    binding.loader.setVisibility(View.GONE);
                    binding.swipe.setRefreshing(false);
                }
            }
        });

        servicesViewModel.deleteServiceLiveData().observe(this, new Observer<UpdateServiceResponse>() {
            @Override
            public void onChanged(UpdateServiceResponse updateServiceResponse) {

                    Toast.makeText(AllServicesActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                    servicesViewModel.getAllServices(PrefManager.getInstance().getString(R.string.authToken));

            }
        });
    }

    private void initialise() {
        serviceAdapter = new ServiceAdapter(this, serviceList,this::delete);
        binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.serviceRecyclerView.setAdapter(serviceAdapter);
    }

    private void clickView() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllServicesActivity.this,AddServiceActivity.class);
                intent.putExtra("addNewService",1);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(AllServicesActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void delete(String id) {
        servicesViewModel.deleteService(id);
    }
}