package com.example.wg.volleytest.marketurl;

import android.net.Uri;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class ThreeSixZero extends MarketUrl {

    public final static String SCHEME = "http";
    public final static String AUTHORITY = "www.appchina.com";
    public final static String[] PATHS = new String[] {"app"};

    //  http://www.appchina.com/app/com.qihoo360.mobilesafe

    public ThreeSixZero() {
        super(SCHEME, AUTHORITY, PATHS, "");
    }

    @Override
    public String buildUrl(String packageName) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(scheme).authority(authority);
        for (String path : paths) {
            builder.appendPath(path);
        }
        builder.appendPath(packageName);
        return builder.build().toString();
    }
}
