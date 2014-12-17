package com.example.wg.volleytest.marketurl;

import android.net.Uri;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class MarketUrl {

    public String scheme;
    public String authority;
    public String[] paths;
    public String packageQueryParameter;

    public MarketUrl(String scheme, String authority, String[] paths, String packageQueryParameter) {
        this.scheme = scheme;
        this.authority = authority;
        this.paths = paths;
        this.packageQueryParameter = packageQueryParameter;
    }

    public String buildUrl(String packageName) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(scheme).authority(authority);
        for (String path : paths) {
            builder.appendPath(path);
        }
        builder.appendQueryParameter(packageQueryParameter, packageName);
        return builder.build().toString();
    }

}
