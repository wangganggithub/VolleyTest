package com.example.wg.volleytest.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class VolleySingleton {
    private RequestQueue requestQueue;

    private static VolleySingleton instance;
    private static Context context;

    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 20000;

    private VolleySingleton(Context context) {
        VolleySingleton.context = context;
    }

    public static synchronized VolleySingleton getInstance(Context appContext) {
        if (VolleySingleton.instance == null) {
            VolleySingleton.instance = new VolleySingleton(appContext);
        }
        return VolleySingleton.instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext(), new TimeoutHurlStack());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    private class TimeoutHurlStack extends HurlStack {
        @Override
        protected HttpURLConnection createConnection(URL url) throws IOException {
            HttpURLConnection connection = super.createConnection(url);
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            return connection;
        }
    }

}
