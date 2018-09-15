package com.nagatsukaayako.stakhovtestcityguide;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class GetJSON extends AsyncTask<String,String,String>{
//    private onGetJSONSuccess mOnSuccess;
//    public void setOnSucessListener(onGetJSONSuccess onSuccessListener){
//        mOnSuccess = onSuccessListener;
//    }
    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        Log.d("City Cites","doInBackground");
        try {
            URL url = new URL("https://www.go102.ru/api2/news?type=json ");
            urlConnection = ( HttpURLConnection)url.openConnection();
            InputStream stream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("City Cites: ", "> " + line);
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s != null)
            Log.d("City Cites", s);
    }
}