package com.example.robi.recycleviewscrollpagination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.robi.recycleviewscrollpagination.adapter.LaporanAdapter;
import com.example.robi.recycleviewscrollpagination.model.Laporan;
import com.example.robi.recycleviewscrollpagination.model.LaporanResponse;
import com.example.robi.recycleviewscrollpagination.network.ApiClient;
import com.example.robi.recycleviewscrollpagination.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LaporanAdapter adapter;
    private ApiInterface apiInterface;
    private LinearLayoutManager layoutManager;

    private int currentPage = 1;

    private boolean isLoading = true;
    private int pasVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        progressBar = findViewById(R.id.progressBar);

        getData();
    }

    public void getData(){
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<LaporanResponse> call = apiInterface.getAllLaporan(currentPage);

        call.enqueue(new Callback<LaporanResponse>() {
            @Override
            public void onResponse(Call<LaporanResponse> call, Response<LaporanResponse> response) {
                List<Laporan> laporans = response.body().getLaporans();
                adapter = new LaporanAdapter(laporans, MainActivity.this);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LaporanResponse> call, Throwable t) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                Log.d("visibleItemCount", String.valueOf(visibleItemCount));
                totalItemCount = layoutManager.getItemCount();
                Log.d("totalItemCount", String.valueOf(totalItemCount));
                pasVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.d("pasVisibleItems", String.valueOf(pasVisibleItems));
                Log.d("view_threshold", String.valueOf(view_threshold));

                if (dy>0){
                    if (isLoading){
                        if(totalItemCount>previous_total){
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    }

                    if (!isLoading&&(totalItemCount-visibleItemCount)<=(pasVisibleItems+view_threshold)){
                        currentPage++;
                        getPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void getPagination(){
        progressBar.setVisibility(View.VISIBLE);
        Call<LaporanResponse> call = apiInterface.getAllLaporan(currentPage);

        call.enqueue(new Callback<LaporanResponse>() {
            @Override
            public void onResponse(Call<LaporanResponse> call, Response<LaporanResponse> response) {
                if (response.body().getMessage().equals("Semua Data")){
                    List<Laporan> laporans = response.body().getLaporans();
                    adapter.addLaporan(laporans);
                    Toast.makeText(MainActivity.this, "Page " + currentPage +" loaded ", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Tidak Ada Data Lagi", Toast.LENGTH_LONG).show();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LaporanResponse> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
