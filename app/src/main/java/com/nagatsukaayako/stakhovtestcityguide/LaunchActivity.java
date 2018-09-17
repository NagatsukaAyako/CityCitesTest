package com.nagatsukaayako.stakhovtestcityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class LaunchActivity extends AppCompatActivity {
    NewsFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Settings.getInstance().isBackground) {
            stopService(new Intent(this, MyService.class));
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final FrameLayout mainFrame = new FrameLayout(this);
        mainFrame.setId(View.generateViewId());
        mainFragment = new NewsFragment();
        getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), mainFragment).commit();
        BottomNavigationView navigation = new BottomNavigationView(this);
        Menu navMenu = navigation.getMenu();
        navMenu.add("Новости").setIcon(R.drawable.ic_news).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), mainFragment).commit();
                return false;
            }
        });
        navMenu.add("Настройки").setIcon(R.drawable.ic_settings).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), new SettingsFragment()).commit();
                return false;
            }
        });
        navMenu.add("О программе").setIcon(R.drawable.ic_info).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getSupportFragmentManager().beginTransaction().replace(mainFrame.getId(), new AboutFragment()).commit();
                return false;
            }
        });
        linearLayout.addView(mainFrame, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        linearLayout.addView(navigation, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setContentView(linearLayout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(Settings.getInstance().isBackground) {
            News[] array = mainFragment.adapter.mValues.toArray(new News[mainFragment.adapter.mValues.size()]);
            Intent intent = new Intent(this, MyService.class);
            intent.putExtra("newsArray", array);
            startService(intent);
        }
    }
}
