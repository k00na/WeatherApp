package com.example.k00na_.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.k00na_.weatherapp.Fragments.AlertDialogFragment;
import com.example.k00na_.weatherapp.Fragments.AlertDialogNoConectivity;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity.class.yo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "d34476c3792c97247a27bc13224ba5e0";
        double latitude = 37.8267;
        double longitude = -122.423;
        String ourUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + "," + longitude;

        // 
        if(isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(ourUrl)
                    .build();


            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {

                    try {

                        if (response.isSuccessful())
                            Log.v(TAG, "The call was successful! " + response.body().string());
                        else
                            alertUserAboutError();

                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: " + e);
                    }

                }
            });
        }
        else
            alertUserAboutNoConnectivity();


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if(networkInfo!=null && networkInfo.isConnected())
            isAvailable = true;



        return isAvailable;

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


    private void alertUserAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getSupportFragmentManager(), "error_dialog");
    }

    private void alertUserAboutNoConnectivity(){
        AlertDialogNoConectivity dialog = new AlertDialogNoConectivity();
        dialog.show(getSupportFragmentManager(), "add_stuff_to_fm");
    }
}
