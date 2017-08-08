package com.github.rcd27.dribbbleapp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.view.fragments.ShotsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ShotsFragment fragment = new ShotsFragment();
            transaction.replace(R.id.shots_fragment_container, fragment);
            transaction.commit();
        }
    }
}
