package com.sample.labassignment1;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by Esha Mayuri on 11/14/2017.
 */

public class OkHttpUtils {
    public static void cancelCallWithTag(OkHttpClient client, String tag) {
        // A call may transition from queue -> running. Remove queued Calls first.
        for(Call call : client.dispatcher().queuedCalls()) {
            if(call.request().tag().equals(tag))
                call.cancel();
        }
        for(Call call : client.dispatcher().runningCalls()) {
            if(call.request().tag().equals(tag))
                call.cancel();
        }
    }
}
