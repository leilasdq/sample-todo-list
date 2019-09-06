package com.example.homework8.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.OrientationHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.homework8.Model.TodolistModel;
import com.example.homework8.R;
import com.example.homework8.Repository.TodoListRepository;
import com.example.homework8.Status;

public class TodoListActivity extends SingleFragmentActivity {

    public static final String END_TEXT = "EndText";
    public static TodolistModel todolistModel = new TodolistModel();
    private String TAG = "List Activity";
    static int orientation;

    @Override
    public Fragment createFragment() {
        return new TodoListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE=0
        //ActivityInfo.SCREEN_ORIENTATION_PORTRAIT=1

        orientation = getResources().getConfiguration().orientation;

    }

    public static Intent newIntent (Context context){
        return new Intent(context, TodoListActivity.class);
    }

    @Override
    public void onBackPressed() {
        System.exit(1);
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setCancelable(true);
//        alertDialogBuilder.setTitle("EXIT");
//        alertDialogBuilder.setMessage("Are you sure you want to exit app?");
//        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
////                Intent intent = new Intent();
////                intent.putExtra(END_TEXT, true);
////                MainActivity.newIntent(TodoListActivity.this, true);
////                finish();
//                System.exit(1);
//            }
//        });
//        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //alertDialogBuilder.dismiss;
//            }
//        });
//        alertDialogBuilder.create();
//        alertDialogBuilder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_btn, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_btn) {
            Intent intent = new Intent(this, AddPageActivity.class);
            startActivityForResult(intent, TodoListFragment.TO_ADD_PAGE_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED || data==null) {
            return;
        }
        else if (requestCode == TodoListFragment.TO_ADD_PAGE_REQUEST_CODE){

            String addedName = data.getStringExtra(AddPageFragment.ADDED_NAME_TEXT);
            Status addedStatus = (Status) data.getSerializableExtra(AddPageFragment.ADDED_STATUS);
            int addedImageRss;
            if (addedStatus == Status.DONE){
                addedImageRss = R.drawable.ic_done_icon;
            }
            else if (addedStatus == Status.TODO){
                addedImageRss = R.drawable.ic_todo_icon;
            }
            else {
                addedImageRss = R.drawable.ic_doing_icon;
            }

            Log.e(TAG, "onActivityResult: added name: " + addedName);
            Log.e(TAG, "onActivityResult: added status: " + addedStatus.toString());

            todolistModel.setName(addedName);
            todolistModel.setStatus(addedStatus);
            todolistModel.setImage(addedImageRss);

            TodoListFragment.listCount++;

            todolistModel = new TodolistModel();

//            TodoListFragment fragment = TodoListFragment.newInstance();
//            TodoListRepository.addTodoItem(todolistModel);
//            fragment.listCount++;
//            fragment.mAdapter.notifyDataSetChanged();
        }
    }
}
