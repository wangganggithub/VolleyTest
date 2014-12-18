package com.example.wg.volleytest.marketurl;

import android.net.Uri;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class AppChina extends Market {

    public final static String SCHEME = "http";
    public final static String AUTHORITY = "www.appchina.com";
    public final static String[] PATHS = new String[] {"app"};

    //  http://www.appchina.com/app/com.qihoo360.mobilesafe

    public final static String SELECTOR = "span.app-statistic"; // <span class="app-statistic">3475万下载 / 1052人喜欢 / 8483人评论 <br />大小：14.95M 更新：2014-12-05</span>


    public AppChina() {
        super(SCHEME, AUTHORITY, PATHS, "", SELECTOR);
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
