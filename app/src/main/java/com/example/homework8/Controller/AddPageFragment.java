package com.example.homework8.Controller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.homework8.R;
import com.example.homework8.Status;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPageFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static final String ADDED_NAME_TEXT = "Added_name_text";
    public static final String ADDED_STATUS = "Added status";
    String TAG = "add page";
    Spinner mSpinner;
    Button mAddToListButton;
    public EditText mAddNameToLstEditText;
    List<String> mStatusSpinnerItems;

    Status mStatus;
    String mName;


    public AddPageFragment() {
        // Required empty public constructor
    }

    public static AddPageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AddPageFragment fragment = new AddPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_page, container, false);

        mSpinner = view.findViewById(R.id.spinner);
        mAddToListButton = view.findViewById(R.id.addToListButton);
        mAddNameToLstEditText = view.findViewById(R.id.add_name_edit_text);
        mStatusSpinnerItems = new ArrayList<>();

        mStatusSpinnerItems.add(String.valueOf(Status.DONE));
        mStatusSpinnerItems.add(String.valueOf(Status.TODO));
        mStatusSpinnerItems.add(String.valueOf(Status.DOING));

        mSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, mStatusSpinnerItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);


        mAddToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mAddNameToLstEditText.getText().toString().equals("")) {
                    AddPageActivity addPageActivity = (AddPageActivity) getActivity();
                    addPageActivity.setItemResult(mAddNameToLstEditText.getText().toString(), mStatus);
                } else {
                    Snackbar.make(getView(), "Fill Parameters", Snackbar.LENGTH_LONG).show();
                }
//                    Intent intent = new Intent();
//                    intent.putExtra(ADDED_NAME_TEXT, mAddNameToLstEditText.getText().toString());
//                    intent.putExtra(ADDED_STATUS, mStatus);
//
//                    Log.e(TAG, "onClick: get name: " +  mAddNameToLstEditText.getText().toString());
//                    Log.e(TAG, "onClick: get status: " +  mStatus);
//
//                    getActivity().setResult(Activity.RESULT_OK, intent);
//                    getActivity().finish();
//                }
//                else {
//                    Snackbar.make(getView(), "Fill Parameters", Snackbar.LENGTH_LONG).show();
//                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();

        if (item.equals(Status.DONE.name())) {
            mStatus = Status.DONE;
        }
        else if (item.equals(Status.TODO.name())){
            mStatus = Status.TODO;
        }
        else if (item.equals(Status.DOING.name())){
            mStatus = Status.DOING;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mStatus = Status.DONE;
    }
}
