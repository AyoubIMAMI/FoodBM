package etu.polytech.foodbm.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import etu.polytech.foodbm.MainActivity;
import etu.polytech.foodbm.R;

public class CurrencyHelper extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak") private final Activity activity;

    private final String TAG = "CurrencyHelper";
    private final String REQUESTED_URL =
            "https://openexchangerates.org/api/latest.json?app_id=8a25f1ef6e764f488fa9dfcfd2bb53e0";

    public CurrencyHelper(Context context){
        super();
        activity = (MainActivity) context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String apiResponse = "";
        String answer = "";

        try {
            URL url = new URL(REQUESTED_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            Log.d(TAG, "Response code: " + status);


            if (status == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                apiResponse = sb.toString();
            }
            conn.disconnect();

            Log.d(TAG, "Response body: " + apiResponse);
            JSONObject jsonObject = new JSONObject(apiResponse);
            JSONArray ratesNode = jsonObject.getJSONArray("rates");

            JSONObject jsonObject1 = new JSONObject(ratesNode.toString());
            JSONArray currency = jsonObject1.getJSONArray(strings[0]);
            answer = currency.toString();

            Log.d(TAG, answer);


        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e);
        }

        return answer;
    }

    @Override
    protected void onPostExecute(String response) {
        TextView currencyTextView = activity.findViewById(R.id.totalValue);
    }
}

