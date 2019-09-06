package com.example.homework8.Repository;

import android.util.Log;

import com.example.homework8.Controller.MainFragment;
import com.example.homework8.Model.TodolistModel;
import com.example.homework8.R;
import com.example.homework8.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TodoListRepository {
    private static TodoListRepository ourInstance;
    private static final String TAG = "repo";
    static List<TodolistModel> mTodoListRepositories = new ArrayList<>();
    private int random;

    public static TodoListRepository getInstance(String name, int count) {
        if (ourInstance == null)
            ourInstance = new TodoListRepository(name, count);
        return ourInstance;
    }

    public static TodoListRepository getInstance(){
        return ourInstance;
    }

    private TodoListRepository(String name, int count) {
//        mMainFragment = new MainFragment();
//        count = mMainFragment.getGetCountInt();
//        name = mMainFragment.getName();

        for (int i = 0; i < count; i++) {
            random = new Random().nextInt(45 - 28) + 28;
            int num = random % 3;
            TodolistModel todolistModel = new TodolistModel();
            todolistModel.setName(name + " #" + (i + 1));

            Log.e(TAG, "TodoListRepository: random " + random);
            Log.e(TAG, "TodoListRepository: num " + num);
            switch (num) {
                case 0:
                    todolistModel.setStatus(Status.DOING);
                    todolistModel.setImage(R.drawable.ic_doing_icon);
                    break;
                case 1:
                    todolistModel.setStatus(Status.TODO);
                    todolistModel.setImage(R.drawable.ic_todo_icon);
                    break;
                case 2:
                    todolistModel.setStatus(Status.DONE);
                    todolistModel.setImage(R.drawable.ic_done_icon);
                    break;
            }
//            Log.e(TAG, "TodoListRepository: name: " + todolistModel.getName());
//            Log.e(TAG, "TodoListRepository: status: " + todolistModel.getName());
            mTodoListRepositories.add(todolistModel);
        }
    }

    public List<TodolistModel> getTodoListRepositories() {
        return mTodoListRepositories;
    }

    public static void addTodoItem (TodolistModel todolistModel) {
        mTodoListRepositories.add(todolistModel);
    }
}
