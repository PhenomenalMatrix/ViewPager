package com.example.viewpager;

import android.app.Application;

import com.example.viewpager.data.network.mokerService.MokerApiClient;

public class App extends Application {
    public static MokerApiClient mockerService;

    @Override
    public void onCreate() {
        super.onCreate();
        mockerService = new MokerApiClient();
    }
}
