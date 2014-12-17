package com.example.wg.volleytest.marketurl;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Meizu extends MarketUrl {

//    http://app.flyme.cn/apps/public/detail?package_name=com.sankuai.meituan

    public final static String SCHEME = "http";
    public final static String AUTHORITY = "app.flyme.cn";
    public final static String[] PATHS = new String[] {"apps", "public", "detail"};
    public final static String PACKAGE_QUERY_PARAMETER = "package_name";

    public Meizu() {
        super(SCHEME, AUTHORITY, PATHS, PACKAGE_QUERY_PARAMETER);
    }
}
