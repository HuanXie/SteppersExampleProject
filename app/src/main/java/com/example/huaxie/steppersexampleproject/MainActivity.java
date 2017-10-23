package com.example.huaxie.steppersexampleproject;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private boolean isLastStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        loadFragment(new BaseFragment());
    }

    public void setLastStep(boolean lastStep) {
        isLastStep = lastStep;
    }

    @SuppressLint("CommitTransaction")
    public void loadFragment(BaseFragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragments_container, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (isLastStep) {
            finish();
        } else {
            onBackPressed();
        }
    }
}
