package com.example.CurrencyCalculator;

import ch.qos.logback.core.util.SystemInfo;
import org.springframework.boot.system.SystemProperties;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private final String API_KEY = "6d99276da2577d4c4316";
    private final String USER_AGENT_ID = "Java/" + System.getProperty("java.version");

    public double rate(String from, String to) throws IOException {
        String queryPath = "https://free.currconv.com/api/v7/convert?q=" +
                from + "_" + to + "&compact=ultra&apiKey=" + API_KEY;

        URL queryUrl = new URL(queryPath);
        HttpURLConnection connection = (HttpURLConnection) queryUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT_ID);
        int responscode = connection.getResponseCode();
        if (responscode == 200) {
            InputStream stream = (InputStream) connection.getContent();
            Scanner scanner = new Scanner(stream);
            String quote = scanner.nextLine();
            String number = quote.substring(quote.indexOf(":") + 1, quote.indexOf("}"));
            return Double.parseDouble(number);
        } else {
            throw new RuntimeException();
        }
    }

    public double rateUSDtoSEK() throws IOException {
        return rate("USD", "SEK");
    }
    public double rateUSDtoNOK() throws IOException {
        return rate("USD", "NOK");
    }
    public double rateUSDtoEUR() throws IOException {
        return rate("USD", "EUR");
    }
    public double rateSEKtoNOK() throws IOException {
        return rate("SEK", "NOK");
    }
}
