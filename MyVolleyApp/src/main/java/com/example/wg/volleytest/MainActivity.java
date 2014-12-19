package com.example.wg.volleytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.wg.volleytest.marketurl.AppChina;
import com.example.wg.volleytest.marketurl.Coolapk;
import com.example.wg.volleytest.marketurl.Google;
import com.example.wg.volleytest.marketurl.Market;
import com.example.wg.volleytest.marketurl.MarketFactory;
import com.example.wg.volleytest.marketurl.MarketListener;
import com.example.wg.volleytest.marketurl.MarketRequest;
import com.example.wg.volleytest.marketurl.Meizu;
import com.example.wg.volleytest.marketurl.Tencent;
import com.example.wg.volleytest.marketurl.Wandoujia;
import com.example.wg.volleytest.volley.VolleySingleton;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

    private Response.ErrorListener errorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resp = (TextView) findViewById(R.id.textView_output);
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                resp.setText(error.getClass().getCanonicalName());
            }
        };
    }

    public void onClickGo(View view) {
        TextView output = (TextView) findViewById(R.id.textView_output);
        TextView pkg = (TextView) findViewById(R.id.textView_input);
        String pkgName = pkg.getText().toString();

        HashMap<Integer, String> map = new HashMap<>();
        map.put(R.id.checkBox_google, Google.class.getName());
        map.put(R.id.checkBox_meizu, Meizu.class.getName());
        map.put(R.id.checkBox_tencent, Tencent.class.getName());
        map.put(R.id.checkBox_appchina, AppChina.class.getName());
        map.put(R.id.checkBox_wdj, Wandoujia.class.getName());
        map.put(R.id.checkBox_coolapk, Coolapk.class.getName());

        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            CheckBox checkBox = (CheckBox) findViewById((Integer) entry.getKey());
            if (checkBox.isChecked()) {
                Market market = MarketFactory.buildMarket((String) entry.getValue());
                Log.i("onClickGo", market.buildUrl(pkgName));
                MarketListener listener = new MarketListener(market, output);
                MarketRequest request = new MarketRequest(
                        Request.Method.GET,
                        market.buildUrl(pkgName),
                        listener,
                        errorListener);
                request.setTag(this);
                VolleySingleton.getInstance(this).addToRequestQueue(request);
            }
        }
    }

    public void onClickClear(View view) {
        TextView rsp = (TextView) findViewById(R.id.textView_output);
        rsp.setText("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        VolleySingleton.getInstance(this).getRequestQueue().cancelAll(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
