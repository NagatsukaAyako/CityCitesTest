package com.nagatsukaayako.stakhovtestcityguide;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    public List<Integer> list = Arrays.asList(1000, 10000, 60000, 600000);

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv =new TextView(parent.getContext());
        String s;
        switch (position){
            case 0: s = "1 секунда"; break;
            case 1: s = "10 секунд"; break;
            case 2: s = "1 минута"; break;
            case 3: s = "10 минут"; break;
            default: s = ""; break;
        }
        tv.setText(s);
        return tv;
    }
}
