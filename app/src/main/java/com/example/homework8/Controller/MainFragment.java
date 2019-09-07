package com.example.homework8.Controller;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.homework8.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public static final String NAME_EDIT_TEXT = "com.example.homework8.nameEditText";
    public static final String NUMBER_OF_LIST_ITEMS = "com.example.homework8.numberOfListItems";
    private EditText name;
    private EditText count;
    private Button build;

    private String getNameString;
    private int getCountInt;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        build.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("") && !count.getText().toString().equals("")){
                    getNameString = name.getText().toString();
                    getCountInt = Integer.parseInt(count.getText().toString());

                    Intent intent = new Intent(getActivity(), TodoListActivity.class);
                    intent.putExtra(NAME_EDIT_TEXT, getNameString);
                    intent.putExtra(NUMBER_OF_LIST_ITEMS, getCountInt);
                    startActivity(intent);
                } else {
                    Snackbar.make(Objects.requireNonNull(getView()), getString(R.string.snackbar_fill_everything_error_text), Snackbar.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void initViews(View view) {
        name = view.findViewById(R.id.name_edit_text);
        count = view.findViewById(R.id.number_of_tasks);
        build = view.findViewById(R.id.build);
    }

    public String getName (){
        return getNameString;
    }

    public int getGetCountInt() {
        return getCountInt;
    }

}
