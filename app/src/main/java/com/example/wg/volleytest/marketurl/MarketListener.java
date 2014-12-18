package com.example.wg.volleytest.marketurl;

import android.widget.TextView;

import com.android.volley.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * Created by WG on 12/18/2014.
 */
public class MarketListener implements Response.Listener<String> {
    private Market market;
    private TextView output;

    public MarketListener(Market market, TextView output) {
        this.market = market;
        this.output = output;
    }

    @Override
    public void onResponse(String response) {
        this.output.append(market.parseResponse(response));
    }
}
