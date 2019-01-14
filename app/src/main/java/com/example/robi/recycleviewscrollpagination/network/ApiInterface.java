package com.example.robi.recycleviewscrollpagination.network;

import com.example.robi.recycleviewscrollpagination.model.LaporanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by robi on 1/14/2019.
 */

public interface ApiInterface {

    @GET(ApiClient.API_ALLLAPORAN)
    Call<LaporanResponse> getAllLaporan(@Query("currentPage") int currentPage);
}
