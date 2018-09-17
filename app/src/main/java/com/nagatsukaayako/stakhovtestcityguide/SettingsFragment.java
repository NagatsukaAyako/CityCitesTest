package com.nagatsukaayako.stakhovtestcityguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linear = new LinearLayout(getActivity());
        linear.setOrientation(LinearLayout.VERTICAL);
        CheckBox background = new CheckBox(getActivity());
        background.setText("Работа в фоновом режиме");
        background.setChecked(Settings.getInstance().isBackground);
        background.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.getInstance().isBackground = isChecked;
            }
        });
        linear.addView(background);
        CheckBox notificate = new CheckBox(getActivity());
        notificate.setText("Уведомлять о новых новостях");
        notificate.setChecked(Settings.getInstance().isNotificate);
        notificate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.getInstance().isNotificate = isChecked;
            }
        });
        final Spinner spinner = new Spinner(getActivity());
        spinner.setAdapter(new SpinnerAdapter());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Settings.getInstance().backgroundTime = (int) spinner.getAdapter().getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {  }
        });

        linear.addView(notificate);
        linear.addView(spinner);
        return linear;
    }

}
