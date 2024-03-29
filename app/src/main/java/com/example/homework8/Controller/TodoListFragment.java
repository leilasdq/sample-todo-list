package com.example.homework8.Controller;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework8.Model.TodolistModel;
import com.example.homework8.R;
import com.example.homework8.Repository.TodoListRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment {
    private static final String TAG = "list";
    public static final int TO_ADD_PAGE_REQUEST_CODE = 1;
    public static final String LENGTH_OF_LIST = "length of list";
    private RecyclerView.Adapter mAdapter;
    private String name;
    public static int listCount;
    private CardView mCardView;
    private List<TodolistModel> todolistModels;
    private List<TodolistModel> newModels = new ArrayList<>();


    public TodoListFragment() {
        // Required empty public constructor
    }

    public static TodoListFragment newInstance() {

        Bundle args = new Bundle();

        TodoListFragment fragment = new TodoListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            listCount = savedInstanceState.getInt(LENGTH_OF_LIST);
        } else {
            listCount = getArguments().getInt(TodoListActivity.COUNT_ARGS);
        }
        name = getArguments().getString(TodoListActivity.NAME_ARGS);
        Log.e(TAG, "onCreate: name " + name);
        Log.e(TAG, "onCreate: count" + listCount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recycle);

        if (TodoListActivity.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else if (TodoListActivity.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        }

        todolistModels = TodoListRepository.getInstance(name, listCount).getTodoListRepositories();

        mAdapter = new TodoAdapter(todolistModels);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: called");
        TodoListRepository.addTodoItem(TodoListActivity.todolistModel);
        newModels.add(TodoListActivity.todolistModel);
        mAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater = Objects.requireNonNull(getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_btn, menu);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder implements Serializable {

        private TextView mTextViewName;
        private TextView mTextViewStatus;
        private ImageView mSatusImageView;

        private TodolistModel mTodolistModel;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewName = itemView.findViewById(R.id.name_text);
            mTextViewStatus = itemView.findViewById(R.id.status_text);
            mSatusImageView = itemView.findViewById(R.id.status_img);
            mCardView = itemView.findViewById(R.id.card_view);
        }

        public void bind(TodolistModel todolistModel) {
            mTodolistModel = todolistModel;

            mTextViewName.setText(mTodolistModel.getName());
            mTextViewStatus.setText(mTodolistModel.getStatus().toString());
            mSatusImageView.setImageDrawable(getResources().getDrawable(mTodolistModel.getImage()));
        }
    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

        List<TodolistModel> mTodoListModelList;

        public TodoAdapter(List<TodolistModel> todolistModelList) {
            mTodoListModelList = todolistModelList;
        }

        @NonNull
        @Override
        public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_view, parent, false);
            return new TodoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
            holder.bind(mTodoListModelList.get(position));
            int num = position % 2;
            switch (num) {
                case 0:
                    mCardView.setCardBackgroundColor(getResources().getColor(R.color.backGround1));
                    break;
                case 1:
                    mCardView.setCardBackgroundColor(getResources().getColor(R.color.backGround2));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return listCount;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LENGTH_OF_LIST, listCount);
    }
}
