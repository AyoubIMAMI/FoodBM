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
import java.util.Locale;

import etu.polytech.foodbm.MainActivity;
import etu.polytech.foodbm.R;

public class CurrencyHelper extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak") private final Activity activity;
    private static Locale locale = Locale.FRENCH;
    private final String TAG = "CurrencyHelper";
    private final String REQUESTED_URL =
            "https://openexchangerates.org/api/latest.json?app_id=8a25f1ef6e764f488fa9dfcfd2bb53e0";

    public CurrencyHelper(Context context){
        super();
        activity = (MainActivity) context;
        setLocale(Locale.FRENCH);
    }

    public static void setLocale(Locale locale) {
        CurrencyHelper.locale = locale;
        Locale.setDefault(locale);
    }

    /**
     * First string - The value to convert.
     * Second string - The current currency.
     * Third String - The currency to convert to.
     */
    @Override
    protected String doInBackground(String... strings) {
        String apiResponse = "";
        float answer = Float.parseFloat(strings[0]);

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

            // Get rates node
            JSONObject reader = new JSONObject(apiResponse);
            JSONObject  ratesNode = reader.getJSONObject("rates");

            // Get the first current currency
            String currency1 = ratesNode.getString(strings[1]);

            // Get the second current currency
            String currency2 = ratesNode.getString(strings[2]);

            //Processing the information to display
            answer = (Float.parseFloat(strings[0]) * Float.parseFloat(currency2))
                    / Float.parseFloat(currency1);

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e);
        }

        String value2 = String.format("%.2f", answer);
        return  value2+ " " + strings[2];
    }

    @Override
    protected void onPostExecute(String response) {
        TextView currencyTextView = activity.findViewById(R.id.totalValue);
        currencyTextView.setText(response);
    }
}

