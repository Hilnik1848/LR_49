package com.example.lr49;

public interface ImageLoadCallback {
    void onSuccess();
    void onFailure(Exception e);
}