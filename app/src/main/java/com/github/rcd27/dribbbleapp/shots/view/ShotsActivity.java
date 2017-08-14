package com.github.rcd27.dribbbleapp.shots.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.rcd27.dribbbleapp.R;

public class ShotsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ShotsFragment shotsFragment = new ShotsFragment();
            transaction.replace(R.id.shots_fragment_container, shotsFragment);
            transaction.commit();
        }
    }
}
