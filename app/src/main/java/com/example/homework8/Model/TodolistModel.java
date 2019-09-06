package com.example.homework8.Model;

import android.os.Build;

import com.example.homework8.Status;

import java.util.Objects;
import java.util.UUID;

import androidx.annotation.RequiresApi;

public class TodolistModel {
    private UUID mId;
    private String mName;
    private Status mStatus;
    private int mImage;

    public TodolistModel(String name, Status status, int image) {
        mName = name;
        mStatus = status;
        mImage = image;
    }

    public TodolistModel() {
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodolistModel that = (TodolistModel) o;
        return Objects.equals(mId, that.mId);
    }
}
