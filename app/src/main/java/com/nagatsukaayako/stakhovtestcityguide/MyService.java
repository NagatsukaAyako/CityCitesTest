package com.nagatsukaayako.stakhovtestcityguide;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
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
        timer.schedule(new MyTimerTask(getApplicationContext(), list), Settings.getInstance().backgroundTime,Settings.getInstance().backgroundTime);
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