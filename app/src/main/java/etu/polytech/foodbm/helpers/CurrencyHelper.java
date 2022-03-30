package etu.polytech.foodbm.helpers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyHelper {
    private final String requestedURL = "https://openexchangerates.org/api/latest.json?app_id=8a25f1ef6e764f488fa9dfcfd2bb53e0";

    public CurrencyHelper() throws IOException {
        URL url = new URL(requestedURL);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
}
