package com.hotweather.app.util;

/**
 * Created by taojunyang on 25/5/2016.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
