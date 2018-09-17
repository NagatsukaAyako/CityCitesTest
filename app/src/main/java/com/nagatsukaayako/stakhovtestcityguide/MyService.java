package com.nagatsukaayako.stakhovtestcityguide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
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
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Properties;
import java.util.Timer;
public class MyService extends Service {

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        someTask();
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

    void someTask() {
    }
}

//public class MyService extends Service {
//    final String LOG_TAG = "servMy";
//
//    public void onCreate() {
//        super.onCreate();
//        Log.d(LOG_TAG, "onCreate");
//    }
//
//    private static Timer timer = new Timer();
//
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(LOG_TAG, "onStartCommand");
//        new Thread(new Runnable() {
//            @SuppressLint({"HandlerLeak", "CommitPrefEdits"})
//            public void run() {
//                //itemListTarget = new ArrayList<>();
//                //itemListTargetString = new ArrayList<>();
//                sPref = PreferenceManager.getDefaultSharedPreferences(getApplication());
//                ed = sPref.edit();
//                serviceStart = Boolean.parseBoolean(sPref.getString("serviceStart: ", ""));
//                Log.d(LOG_TAG, "serviceStart: " + serviceStart);
//                if (serviceStart) {
//                    countdownTimerStart(intervalInMinute);
//                    //timer = new Timer();
//                    //timer.scheduleAtFixedRate(new mainTask(), 0, intervalInMinute*1000*20);
////                  someTask();
//                }
//                //else
//                //  loadText();
//
//                //loadText();
//
////                mHandler = new Handler() {
////                    public void handleMessage(Message status) {
////                        switch (status.what) {
////                            case 1:
////                                Log.d(LOG_TAG, "handleMessage");
////                                //saveText();
////                                break;
////                        }
////                    }
////                };
//                //stopSelf();
//            }
//        }).start();
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    public void onDestroy() {
//        super.onDestroy();
//        //serviceStart = false;
//        countdownTimerStop();
/////////        timer.cancel();
//        //saveText();
//        //ed.commit();
//        Log.d(LOG_TAG, "onDestroy");
//        //stopSelf();
//    }
//
//    public IBinder onBind(Intent intent) {
//        Log.d(LOG_TAG, "onBind");
//        return null;
//    }
//
//    public void sendNotification() {
//
//        //countFailed = itemListTarget.size() - countSuccess;
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication());
//        Intent notificationIntent = new Intent(getApplication(), MyService.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(getApplication(),
//                0, notificationIntent,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//
//        builder.setContentIntent(contentIntent)
//                // обязательные настройки
//                .setDefaults(Notification.DEFAULT_SOUND |
//                        Notification.DEFAULT_VIBRATE)
//                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
//                .setContentTitle("WebMon Report")
//                //.setContentText(res.getString(R.string.notifytext))
//                .setContentText(countFailed + " failures, " + countSuccess + " successful.") // Текст уведомления
//                // необязательные настройки
//                //.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.hungrycat)) // большая
//                // картинка
//                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
//                .setTicker("WebMon Report")
//                .setWhen(System.currentTimeMillis())
//                .setAutoCancel(true); // автоматически закрыть уведомление после нажатия
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
//        notificationManager.notify(101, builder.build());
//    }
//
//    public CountDownTimer cdt;
//
//    public void countdownTimerStart(long value) {
//        ////////////////////////////////////
//        Log.d(LOG_TAG, "1");
//
//        final long[] timeInMilis = {value * 1000 * 10};
//
//
//
//        Log.d(LOG_TAG, "2");
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                cdt = new CountDownTimer(timeInMilis[0], 1000) {
//                    @Override
//                    public void onTick(long l) {
//                        timeInMilis[0] = l;
//                        int minutes = (int) (timeInMilis[0] / 1000) / 60;
//                        int seconds = (int) (timeInMilis[0] / 1000) % 60;
//                        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//                        Log.d(LOG_TAG, timeFormatted);
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        itemListTarget = new ArrayList<>();
//                        itemListTargetString = new ArrayList<>();
////                        loadText();
//                        if (serviceStart) {
//
////                            Check();
//
//                        }
//                    }
//                }.start();
//                //timerRunning = true;
//            }
//        });
//    }
//
//    public void countdownTimerStop() {
//
//        serviceStart = false;
//        saveText();
//
//        cdt.cancel();
//        //timerRunning = false;
//    }
//
//    public static ArrayList<Target> itemListTarget;
//    public static ArrayList<String> itemListTargetString;
//    public static String latestRunValue = "None";
//    public static int intervalInMinute = 1;
//
//    public static boolean isNotificationAndroid = false;
//    public static boolean isNotificationEmail = false;
//    public static String emailTo = "";
//    public static String emailFrom = "";
//    public static String emailFromPassword = "";
//
//    public static class Target {
//        private String title = "";
//        private String URL = "";
//        private String FindText = "";
//        private String previousHtml = "";
//        private String html = "";
//        private String status = "";
//        private int countChange = 0;
//        private boolean isFind = false;
//        private boolean found = false;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getURL() {
//            return URL;
//        }
//
//        public String getFindText() {
//            return FindText;
//        }
//
//        public boolean getIsFind() {
//            return isFind;
//        }
//
//        public String getPreviousHtml() {
//            return previousHtml;
//        }
//
//        public String getHtml() {
//            return html;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public int getCountChange() {
//            return countChange;
//        }
//
//        public boolean getFound() {
//            return found;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public void setURL(String URL) {
//            this.URL = URL;
//        }
//
//        public void setFindText(String findText) {
//            FindText = findText;
//        }
//
//        public void setIsFind(boolean find) {
//            isFind = find;
//        }
//
//        public void setPreviousHtml(String previousHtml) {
//            this.previousHtml = previousHtml;
//        }
//
//        public void setHtml(String html) {
//            this.html = html;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public void setCountChange(int countChange) {
//            this.countChange = countChange;
//        }
//
//        public void setFound(boolean found) {
//            this.found = found;
//        }
//
//    }
//
//    SharedPreferences sPref;
//    SharedPreferences.Editor ed;
//
//    public static boolean serviceStart = false;
//
//    public static int countSuccess = 0;
//    public static int countFailed = 0;
//
//    public void saveText() {
//        Log.d(LOG_TAG, sPref.getString("size: ", ""));
//        ed.putString("size: ", Integer.toString(itemListTarget.size()));
//        for (int i = 0; i < itemListTarget.size(); i++) {
//            ed.putString(i + ") title: ", itemListTarget.get(i).getTitle());
//            ed.putString(i + ") URL: ", itemListTarget.get(i).getURL());
//            ed.putString(i + ") FindText: ", itemListTarget.get(i).getFindText());
//            ed.putString(i + ") IsFind: ", Boolean.toString(itemListTarget.get(i).getIsFind()));
//            ed.putString(i + ") PreviousHtml: ", itemListTarget.get(i).getPreviousHtml());
//            ed.putString(i + ") Html: ", itemListTarget.get(i).getHtml());
//            ed.putString(i + ") Status: ", itemListTarget.get(i).getStatus());
//            ed.putString(i + ") CountChange: ", Integer.toString(itemListTarget.get(i).getCountChange()));
//            ed.putString(i + ") Found: ", Boolean.toString(itemListTarget.get(i).getFound()));
//        }
//        ed.putString("intervalInMinute: ", Integer.toString(intervalInMinute));
//        ed.putString("latestRunValue: ", latestRunValue);
//
//        ed.putString("isNotificationAndroid: ", Boolean.toString(isNotificationAndroid));
//        ed.putString("isNotificationEmail: ", Boolean.toString(isNotificationEmail));
//
//        ed.putString("emailTo: ", emailTo);
//        ed.putString("emailFrom: ", emailFrom);
//        ed.putString("emailFromPassword: ", emailFromPassword);
//
//        ed.putString("serviceStart: ", Boolean.toString(MyService.serviceStart));
//        ed.putString("countFailed: ", Integer.toString(MyService.countFailed));
//
//        ed.commit();
//        //Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
//    }
//
//
//
//
//}