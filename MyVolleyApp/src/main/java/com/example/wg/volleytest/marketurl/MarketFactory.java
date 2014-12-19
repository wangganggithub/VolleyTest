package com.example.wg.volleytest.marketurl;

/**
 *
 * Created by WG on 12/17/2014.
 */
public class MarketFactory {

    public static Market buildMarket(String clazz) {
        Market market = null;
        try {
            market = (Market) Class.forName(clazz).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return market;
    }
}

