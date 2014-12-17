package com.example.wg.volleytest.marketurl;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Tencent extends MarketUrl {

//    http://android.myapp.com/myapp/detail.htm?apkName=com.tencent.mm

    public final static String SCHEME = "http";
    public final static String AUTHORITY = "android.myapp.com";
    public final static String[] PATHS = new String[] {"myapp", "detail.htm"};
    public final static String PACKAGE_QUERY_PARAMETER = "apkName";

    public Tencent() {
        super(SCHEME, AUTHORITY, PATHS, PACKAGE_QUERY_PARAMETER);
    }
}
