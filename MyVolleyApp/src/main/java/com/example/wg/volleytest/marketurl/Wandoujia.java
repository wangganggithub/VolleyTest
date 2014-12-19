package com.example.wg.volleytest.marketurl;

import android.net.Uri;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Wandoujia extends Market {

    public final static String SCHEME = "http";
    public final static String AUTHORITY = "www.wandoujia.com";
    public final static String[] PATHS = new String[] {"apps"};

    //  http://www.wandoujia.com/apps/com.netease.edu.study


    public Wandoujia() {
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
        Document doc = Jsoup.parse(response);

        Element span_install = doc.select("span.item").first();
        Element span_love = doc.select("span.item.love").first();
        Element a_comment = doc.select("a.item.last.comment-open").first();

        String install = (span_install==null) ? "" : span_install.text();
        String love = (span_love==null) ? "" : span_love.text();
        String comment = (a_comment==null) ? "" : a_comment.text();

        return formatResponse(String.format("%s, %s, %s", install, love, comment));
    }
}
