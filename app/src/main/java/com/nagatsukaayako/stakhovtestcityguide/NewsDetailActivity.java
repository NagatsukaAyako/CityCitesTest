package com.nagatsukaayako.stakhovtestcityguide;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NewsDetailActivity extends AppCompatActivity {
    News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        news = (News) getIntent().getSerializableExtra("news");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        scrollView.addView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final ImageView imageView = new ImageView(this);
        new DownloadImageTask(new NewsAdapter.onGetImageListener(){
            @Override
            public void onGetImage(Bitmap image) {
                imageView.setImageBitmap(image);
            }
        }).execute(news.getNewsIcons());
        linearLayout.addView(imageView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        TextView Name = new TextView(this);
        Name.setText(news.getName());
        Name.setTypeface(Typeface.DEFAULT_BOLD);
        Name.setTextSize(16f);
        Name.setPadding(20,0,20,0);
        linearLayout.addView(Name);
        TextView date = new TextView(this);
        date.setText(new SimpleDateFormat("d.MM.yyyy HH:mm", new Locale("ua-UK")).format(news.getDateShowUNIX()));
        date.setTextSize(12f);
        date.setPadding(20,0,20,0);
        linearLayout.addView(date);
        TextView text = new TextView(this);
        text.setText(news.getTxt());
        text.setPadding(20,0,20,0);
        linearLayout.addView(text);
        setContentView(scrollView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }
}
