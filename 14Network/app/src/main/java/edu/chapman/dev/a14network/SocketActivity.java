package edu.chapman.dev.a14network;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SocketActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //new Thread(new Server()).start();
        //new Thread(new Client()).start();

        new Thread(new WebPageGrabber()).start();

    }

    private class WebPageGrabber implements Runnable {

        private void getWebsite(String addr) {
            String link = "http://www.google.com";
            //String link = "https://api.iextrading.com/1.0/stock/aapl/price";
            HttpURLConnection conn = null;
            try {
                URL url = new URL(link);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String webPage = "", data = "";

                while ((data = reader.readLine()) != null) {
                    webPage += data + "\n";
                }

                Log.i("SocketActivity", webPage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    conn.disconnect();
            }

        }

        public void run() {
            getWebsite("addr");
        }

    }
}