package com.nagatsukaayako.stakhovtestcityguide;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class News implements Serializable{
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

    public static void sortNews(ArrayList<News> newsArrayList){
        int p = 0;

        for(int i = 0;i<newsArrayList.size();i++ )
            if(newsArrayList.get(i).getTopDay()){
                News temp = newsArrayList.get(p);
                newsArrayList.set(p, newsArrayList.get(i));
                newsArrayList.set(i, temp);
                p++;
            }
        quickSort(newsArrayList,0, p-1);
        quickSort(newsArrayList,p-1, newsArrayList.size()-1);
    }

    private static void quickSort(ArrayList<News> array, int low, int high) {
        if (array.size() == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        News opora = array.get(middle);
        int i = low, j = high;
        while (i <= j) {
            while (array.get(i).getDateShowUNIX().before(opora.getDateShowUNIX())) {
                i++;
            }
            while (array.get(j).getDateShowUNIX().after(opora.getDateShowUNIX())) {
                j--;
            }
            if (i <= j) {
                News temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
}
