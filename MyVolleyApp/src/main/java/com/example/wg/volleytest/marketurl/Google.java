package com.example.wg.volleytest.marketurl;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Google extends Market {

    // GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=com.google.android.apps.maps";
    public final static String SCHEME = "https";
    public final static String AUTHORITY = "play.google.com";
    public final static String[] PATHS = new String[] {"store", "apps", "details"};
    public final static String PACKAGE_QUERY_PARAMETER = "id";

    public final static String SELECTOR = "div.score"; // <div class="score">4.2</div>

    public Google() {
        super(SCHEME, AUTHORITY, PATHS, PACKAGE_QUERY_PARAMETER, SELECTOR);
    }

}
