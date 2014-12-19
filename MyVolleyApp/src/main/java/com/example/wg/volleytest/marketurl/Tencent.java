package com.example.wg.volleytest.marketurl;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Tencent extends Market {

    // Tencent
    // http://android.myapp.com/myapp/detail.htm?apkName=com.tencent.mm

    public final static String SCHEME = "http";
    public final static String AUTHORITY = "android.myapp.com";
    public final static String[] PATHS = new String[] {"myapp", "detail.htm"};
    public final static String PACKAGE_QUERY_PARAMETER = "apkName";

    public final static String SELECTOR = "div.com-blue-star-num"; // <div class="com-blue-star-num">3.1分</div>

    public Tencent() {
        super(SCHEME, AUTHORITY, PATHS, PACKAGE_QUERY_PARAMETER, SELECTOR);
    }
}
