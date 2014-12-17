package com.example.wg.volleytest.marketurl;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class MarketUrlFactory {

    public static String buildUrl(Class clazz, String packageName) {
        MarketUrl marketUrl = null;
        try {
            marketUrl = (MarketUrl) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (marketUrl!=null) ? marketUrl.buildUrl(packageName) : null;
    }
}

