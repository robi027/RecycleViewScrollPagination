package com.example.robi.recycleviewscrollpagination.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robi on 1/14/2019.
 */

public class ApiClient {

    public static Retrofit retrofit = null;
    public static final String BASE_URL = "http://192.168.1.80";
    public static final String API_URL = "/sigap";
    public static final String API_ALLLAPORAN =
            API_URL + "/laporanpage.php";

    public static Retrofit getApiClient(){
        if (retrofit ==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
