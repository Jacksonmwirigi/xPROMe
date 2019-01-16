package com.jackoyee.xprome.network;

import com.jackoyee.xprome.apis.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static  final String Base_URL="http://172.29.200.28/dp/rest/";

    private static  RetrofitClient networkInstance;
    private Retrofit retrofit;

    private   RetrofitClient(){
            OkHttpClient client =new OkHttpClient.Builder().connectTimeout(300, TimeUnit.SECONDS).writeTimeout(300,TimeUnit.SECONDS)
                    .readTimeout(300,TimeUnit.SECONDS).build();
        retrofit=new Retrofit.Builder()
                .baseUrl(Base_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public  static  synchronized  RetrofitClient getNetworkInstance(){

        if (networkInstance==null){
            networkInstance=new RetrofitClient();
        }
        return  networkInstance;
    }
    public ApiService getApiService(){
        return  retrofit.create(ApiService.class);
    }


}
