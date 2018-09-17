package com.nagatsukaayako.stakhovtestcityguide;


import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class GetJSON extends AsyncTask<String,String,String>{
    private NewsAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<News> list;
    private SendNotificationListener listener;
    GetJSON(NewsAdapter adapter, SwipeRefreshLayout swipeRefreshLayout){
        mAdapter = adapter;
        this.swipeRefreshLayout = swipeRefreshLayout;

    }
    GetJSON(List<News> list, SendNotificationListener listener){
        this.list = list;
        this.listener =listener;
    }
    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("https://www.go102.ru/api2/news?type=json ");
            urlConnection = ( HttpURLConnection)url.openConnection();
            InputStream stream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
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
        Log.d("City Cites", String.valueOf(swipeRefreshLayout==null));
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s != null)
        {
            JSONObject json = null;
            try {
                json = new JSONObject(s);
            }
            catch (JSONException e){ Log.d("City Cites", e.getMessage()); }
            if(json!= null){
                try{
                    JSONArray news = json.getJSONObject("response").getJSONArray("news");
                    ArrayList<News> newsArrayList = new ArrayList<>();
                    if(news.length()!=0) {
                        for (int i = 0; i < news.length(); i++) {
                            newsArrayList.add(new News((JSONObject) news.get(i)));
                        }
                        News.sortNews(newsArrayList);
                        if(mAdapter != null)
                            mAdapter.setData(newsArrayList);
                        if(list!=null){
                            for (News n: newsArrayList ) {
                                boolean isConsist = false;
                                for (News news1: list) {
                                    if(n.getNewsUrl().equals(news1.getNewsUrl()))
                                    {
                                        isConsist = true;
                                        break;
                                    }
                                }
                                if(!isConsist && Settings.getInstance().isNotificate)
                                    listener.sendNotification(n.getName(), n.getTxt());
                            }
                        }
                    }
                }
                catch (JSONException e){
                    Log.d("City Cites", e.getMessage());
                }
            }
        }

        if(swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}