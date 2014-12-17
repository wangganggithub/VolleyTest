package com.example.wg.volleytest;

import android.app.Activity;
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
import com.example.wg.volleytest.marketurl.Google;
import com.example.wg.volleytest.marketurl.MarketUrlFactory;
import com.example.wg.volleytest.marketurl.Meizu;
import com.example.wg.volleytest.marketurl.Tencent;
import com.example.wg.volleytest.marketurl.ThreeSixZero;
import com.example.wg.volleytest.volley.StringRequestPC;
import com.example.wg.volleytest.volley.VolleySingleton;


public class MainActivity extends Activity {

    private Response.Listener<String> listener;
    private Response.ErrorListener errorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resp = (TextView) findViewById(R.id.textView_output);
        listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("onResponse", response);
                resp.setText(response);
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                resp.setText(error.getClass().getCanonicalName());
            }
        };
    }

    public void onClickGo(View view) {
        TextView pkg = (TextView) findViewById(R.id.textView_input);
        String pkgName = pkg.getText().toString();
        String url = "";

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_meizu);
        if (checkBox.isChecked()) {
            url = MarketUrlFactory.buildUrl(Meizu.class, pkgName);
        }
        checkBox = (CheckBox) findViewById(R.id.checkBox_google);
        if (checkBox.isChecked()) {
            url = MarketUrlFactory.buildUrl(Google.class, pkgName);
        }
        checkBox = (CheckBox) findViewById(R.id.checkBox_tencent);
        if (checkBox.isChecked()) {
            url = MarketUrlFactory.buildUrl(Tencent.class, pkgName);
        }
        checkBox = (CheckBox) findViewById(R.id.checkBox_360);
        if (checkBox.isChecked()) {
            url = MarketUrlFactory.buildUrl(ThreeSixZero.class, pkgName);
        }

        Log.i("onClickGo", url);
        VolleySingleton.getInstance(this).addToRequestQueue(new StringRequestPC(Request.Method.GET, url, listener, errorListener).setTag(this));
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
