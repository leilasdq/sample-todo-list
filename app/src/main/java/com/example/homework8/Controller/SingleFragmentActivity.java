package com.example.homework8.Controller;

import android.os.Bundle;

import com.example.homework8.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.mainFragment_container);
        if (fragment == null)
            fragmentManager
                    .beginTransaction()
                    .add(R.id.mainFragment_container, createFragment())
                    .commit();

    }
}
