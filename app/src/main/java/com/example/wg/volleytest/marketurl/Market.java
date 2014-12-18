package com.example.wg.volleytest.marketurl;

import android.net.Uri;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Market {

    public String scheme;
    public String authority;
    public String[] paths;
    public String packageQueryParameter;
    public String selector;

    public Market(String scheme, String authority, String[] paths, String packageQueryParameter, String selector) {
        this.scheme = scheme;
        this.authority = authority;
        this.paths = paths;
        this.packageQueryParameter = packageQueryParameter;
        this.selector = selector;
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

    public String formatResponse(String response) {
        return String.format("%s: %s\n", authority, response);
    }

    public String parseResponse(String response) {
        Element element = Jsoup.parse(response).select(selector).first();
        return (element!=null) ? formatResponse(element.text()) : formatResponse("");
    }
}
