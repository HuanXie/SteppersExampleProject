package com.example.huaxie.steppersexampleproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by huaxie on 10/23/2017.
 */

abstract class TabPageAbstractAdapter extends FragmentStatePagerAdapter {
    private Context context;

    TabPageAbstractAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm);
        this.context= context;
    }

    View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_view, null);
        TextView counter = v.findViewById(R.id.tab_counter);
        counter.setText(String.valueOf(position + 1));
        return v;
    }
}
