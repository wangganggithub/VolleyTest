package com.example.wg.volleytest.marketurl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class Meizu extends Market {

//    http://app.flyme.cn/apps/public/detail?package_name=com.sankuai.meituan

    public final static String SCHEME = "http";
    public final static String AUTHORITY = "app.flyme.cn";
    public final static String[] PATHS = new String[] {"apps", "public", "detail"};
    public final static String PACKAGE_QUERY_PARAMETER = "package_name";

    public final static String SELECTOR = "div.star_bg"; // <div class="star_bg" data-num="26"></div>
    public final static String ATTR = "data-num";

    public Meizu() {
        super(SCHEME, AUTHORITY, PATHS, PACKAGE_QUERY_PARAMETER, SELECTOR);
    }

    @Override
    public String parseResponse(String response) {
        // <div class="star_bg" data-num="26"></div>
        Element element = Jsoup.parse(response).select(selector).first();
        if (element != null) {
            String data_num = element.attr(ATTR);
            return formatResponse(String.valueOf((Integer.valueOf(data_num) / 10.0)));
        } else {
            return formatResponse("");
        }
    }

}
