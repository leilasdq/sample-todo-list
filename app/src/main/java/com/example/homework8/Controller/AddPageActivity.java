package com.example.homework8.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.homework8.R;
import com.example.homework8.Status;
import com.google.android.material.snackbar.Snackbar;

public class AddPageActivity extends SingleFragmentActivity {
    FrameLayout root;

    @Override
    public Fragment createFragment() {
        return AddPageFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);
    }

    public void setItemResult(String name, Status status) {
            Intent intent = new Intent();
            intent.putExtra(AddPageFragment.ADDED_NAME_TEXT, name);
            intent.putExtra(AddPageFragment.ADDED_STATUS, status);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
}
