package com.example.wg.volleytest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends Activity {

    private RequestQueue queue;

//    private final static String GOOGLE_PLAY = "http://play.google.com/store/apps/details?id=%s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
    }

    public void onClickGo(View view) {
        TextView pkg = (TextView) findViewById(R.id.textView_input);
        final TextView resp = (TextView) findViewById(R.id.textView_output);

        String url = makeUrl(pkg.getText().toString());
        Log.i("onClickGo", url);

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("onResponse", response);
                        resp.setText(response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("onErrorResponse", error.getLocalizedMessage());
                resp.setText(error.getLocalizedMessage());
            }
        });

        queue.add(request);
    }

    public void onClickClear(View view) {
        TextView pkg = (TextView) findViewById(R.id.textView_input);
        TextView resp = (TextView) findViewById(R.id.textView_output);
        pkg.setText("");
        resp.setText("");
    }

    private static String makeUrl(String packageName) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("play.google.com")
                .appendPath("store")
                .appendPath("apps")
                .appendPath("details")
                .appendQueryParameter("id", packageName);

        return builder.build().toString();
    }

    @Override
    protected void onStop() {
        super.onStop();
        queue.stop();
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
