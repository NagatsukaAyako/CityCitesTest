package com.nagatsukaayako.stakhovtestcityguide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends IntentService {

    final String LOG_TAG = "myLogs";
    public MyService() {
        super("myname");
    }
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "onHandleIntent");
        List<News> list = Arrays.asList((News[])intent.getExtras().get("newsArray"));
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(getApplicationContext(), list), 10000,10000);
    }
}
class MyTimerTask extends TimerTask {

    private Context context;
    private List<News> list;
    MyTimerTask(Context context, List<News> list){
        this.context =context;
        this.list = list;
    }
    @Override
    public void run() {
        SendNotificationListener notificationListener = new SendNotificationListener() {
            @Override
            public void sendNotification(String title, String text) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "")
                        .setSmallIcon(R.drawable.ic_news)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                int notificationId = 1;
                notificationManager.notify(notificationId, mBuilder.build());
            }
        };
        new GetJSON(list, notificationListener).execute();
    }
}
interface SendNotificationListener{
    void sendNotification(String title, String text);
}