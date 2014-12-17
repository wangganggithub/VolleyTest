package com.example.wg.volleytest.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class StringRequestPC extends StringRequest{

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0";

    public StringRequestPC(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = new HashMap<>();
        map.put("User-Agent", USER_AGENT);
        return map;
    }
}
