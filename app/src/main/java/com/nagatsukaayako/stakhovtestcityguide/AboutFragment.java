package com.nagatsukaayako.stakhovtestcityguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linear = new LinearLayout(getActivity());
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setPadding(20,20,20,20);
        TextView textView = new TextView(getActivity());
        textView.setText("Название: Stakhov Test City Guide");
        TextView textView1 = new TextView(getActivity());
        textView1.setText("Автор: Стахов Дмитрий Сергеевич");
        TextView textView2 = new TextView(getActivity());
        textView2.setText("Версия: 1.0");
        linear.addView(textView);
        linear.addView(textView1);
        linear.addView(textView2);
        return linear;
    }
}
