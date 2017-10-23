package com.example.huaxie.steppersexampleproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by huaxie on 10/23/2017.
 */

public class SimpleFragment extends Fragment{

    private static final String ARGS_NUMBER = "number";
    private static final String ARGS_SIZE = "list_size";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        final SwipeDisabledViewPager viewPager = getActivity().findViewById(R.id.viewpager);
        TextView textView = view.findViewById(R.id.number);
        int postion = getArguments().getInt(ARGS_NUMBER);
        textView.setText(getString(R.string.page, String.valueOf(postion + 1)));
        if (postion == getArguments().getInt(ARGS_SIZE) - 1) { //last page
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "This is the last page! Press back to close app!", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(getArguments().getInt(ARGS_NUMBER) + 1);
                }
            });
        }
        return view;
    }

    public static SimpleFragment newInstance(int s, int size) {
        Bundle args = new Bundle();
        SimpleFragment fragment = new SimpleFragment();
        args.putInt(ARGS_NUMBER, s);
        args.putInt(ARGS_SIZE, size);
        fragment.setArguments(args);
        return fragment;
    }
}

