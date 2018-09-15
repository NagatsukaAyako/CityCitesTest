package com.nagatsukaayako.stakhovtestcityguide;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class News {
    private String name;
    private String txt;
    private boolean topDay;
    private Long dateShowUNIX;
    private String newsIcon;
    private String NewsUrl;

    public News(JSONObject news){
        try{
            name = news.getString("name");
            txt = news.getString("txt");
            topDay = (news.getInt("top_day") == 1);
            dateShowUNIX = news.getLong("date_show_unix");
            newsIcon = news.getJSONObject("newsIcon").getString("imgfull");
            NewsUrl = news.getString("newsUrl");
        }
        catch (JSONException e){
            Log.d("City Cites", e.getMessage());
        }
    }

    String getName() { return name; }
    String getTxt() { return txt; }
    boolean getTopDay() { return topDay; }
    Long getDateShowUNIX() { return dateShowUNIX; }
    String getNewsIcons() { return newsIcon; }
    String getNewsUrl() { return NewsUrl; }
}
