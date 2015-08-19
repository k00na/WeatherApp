package com.example.k00na_.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.k00na_.weatherapp.Fragments.AlertDialogFragment;
import com.example.k00na_.weatherapp.Fragments.AlertDialogNoConectivity;
import com.example.k00na_.weatherapp.Model.CurrentWeather;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity.class.yo";
    private CurrentWeather mCurrentWeather;

    @Bind(R.id.locationLabel)TextView locLabel;
    @Bind(R.id.temperature_label)TextView textView;
    @Bind(R.id.timeLabel)TextView timeLabel;
    @Bind(R.id.humidityValue)TextView humidityLabel;
    @Bind(R.id.rainValue)TextView rainValue;
    @Bind(R.id.shortDescriptionText)TextView shortDescribValue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "d34476c3792c97247a27bc13224ba5e0";
        double latitude = 37.8267;
        double longitude = -122.423;
        String ourUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + "," + longitude;

        ButterKnife.bind(this);




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
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            Log.v(TAG, "The call was successful! " + jsonData);
                            mCurrentWeather = getCurrentDetails(jsonData);
                            updateDisplay();

                        } else
                            alertUserAboutError();

                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: " + e);
                    }
                    catch (JSONException e){
                        Log.e(TAG, "JSON error" + e);
                    }

                }
            });
        }
        else
            alertUserAboutNoConnectivity();





    }

    private void updateDisplay() {

        locLabel.setText();
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {

        // making the main JSON object
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.d(TAG, "From JSON: " + timezone);

        // creating a new JSON object from the main object

        JSONObject currently = forecast.getJSONObject("currently");
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setmHumidity(currently.getDouble("humidity"));
        currentWeather.setmTime(currently.getLong("time"));
        currentWeather.setmIcon(currently.getString("icon"));
        currentWeather.setmPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setmSummary(currently.getString("summary"));
        currentWeather.setmTemperature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG, "" + currentWeather.getFromatedData());




        return currentWeather;

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if(networkInfo!=null && networkInfo.isConnected())
            isAvailable = true;

        return isAvailable;

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
