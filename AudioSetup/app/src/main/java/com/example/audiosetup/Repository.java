package com.example.audiosetup;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public static final String TAG="Repository";
    private static final String SERVICE_BASE_URL = "http://192.168.1.164/";
    private static Repository me;

    private Api apiService;

    private static String curUrl;

    public static String getBaseUrl(){
        return (curUrl == null ? SERVICE_BASE_URL : curUrl);
    }
    public static void setIP(String ip){

        curUrl="http://"+ip+"/";
        Log.d(TAG,"new URL is:"+curUrl);
    }
    public Repository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);

        me = this;
    }

    /*

        public static String getAbsUrl(String relUrl){

            return  SERVICE_BASE_URL+relUrl;
        }

    */
    public void setSpeed(int speed) {

        Log.d(TAG,"setting speed as "+speed);
        apiService.setSpeed(speed)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d(TAG,"___________________________________");
                        if (response.body() != null) {
                            Log.d(TAG,"apiService.setSpeed succes body:"+response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e(TAG,"apiService.setSpeed fail",t);
                    }
                });
    }

    public static synchronized Repository get(){

        if (me != null) return me;

        me   = new Repository();
        return  me;
    }

}