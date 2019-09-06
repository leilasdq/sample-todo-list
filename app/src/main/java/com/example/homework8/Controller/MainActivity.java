package com.example.homework8.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.homework8.R;

public class MainActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return new MainFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.mainFragment_container);
        if (fragment==null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.mainFragment_container, new MainFragment())
                    .commit();
        }

    }


    public static Intent newIntent(Context context){

        return new Intent(context, MainActivity.class);
    }
}
