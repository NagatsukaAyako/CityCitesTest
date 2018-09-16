package com.nagatsukaayako.stakhovtestcityguide;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class News {
    private String name;
    private String txt;
    private boolean topDay;
    private Date dateShowUNIX;
    private String newsIcon;
    private String NewsUrl;
    private Bitmap Image;

    public News(JSONObject news){
        try{
            name = news.getString("name");
            txt = news.getString("txt");
            topDay = (news.getInt("top_day") == 1);
            dateShowUNIX = new Date(news.getLong("date_show_unix"));
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
    Date getDateShowUNIX() { return dateShowUNIX; }
    String getNewsIcons() { return newsIcon; }
    String getNewsUrl() { return NewsUrl; }
    Bitmap getImage() { return Image; }
    void setImage(Bitmap image) { Image = image; }
}
