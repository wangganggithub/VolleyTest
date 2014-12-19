package com.example.wg.volleytest.marketurl;

import android.net.Uri;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Coolapk extends Market {

    // http://www.coolapk.com/apk/com.google.android.apps.maps
    public final static String SCHEME = "http";
    public final static String AUTHORITY = "www.coolapk.com";
    public final static String[] PATHS = new String[] {"apk"};

    public final static String SELECTOR = "span.pull-left.hidden-sm.hidden-xs";
    // <span class="pull-left hidden-sm hidden-xs">1312人关注，319万+次下载，3601个评论，23个权限，简体中文，</span>

    public final static String SELECTOR1 = "span.ex-apk-rank-score";
    // <span class="ex-apk-rank-score">4.1</span>

    public Coolapk() {
        super(SCHEME, AUTHORITY, PATHS, "", "");
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

    @Override
    public String parseResponse(String response) {
        Element element = Jsoup.parse(response).select(SELECTOR).first();
        Element element1 = Jsoup.parse(response).select(SELECTOR1).first();
        String star = (element1==null) ? "" : element1.text();
        String stat = (element==null) ? "" : element.text();
        return formatResponse(String.format("%s, %s", star, stat));
    }
}
