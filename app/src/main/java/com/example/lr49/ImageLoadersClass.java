package com.example.lr49;

import android.content.Context;
import android.widget.ImageView;

public abstract class ImageLoadersClass {
    protected Context context;

    public ImageLoadersClass(Context context) {
        this.context = context;
    }

    public abstract void loadImage(String url, ImageView imageView);
    public abstract void loadImage(String url, ImageView imageView, int placeholderResId);
    public abstract void loadImage(String url, ImageView imageView, int placeholderResId, int errorResId);
    public abstract void loadImage(String url, ImageView imageView, ImageLoadCallback callback);

    public abstract void resizeImageDefault(String url, ImageView imageView);
    public abstract void resizeImageCustom(String url, ImageView imageView, int resizeWidth, int resizeHeight);

    public abstract void centerCrop(String url, ImageView imageView);
    public abstract void centerInside(String url, ImageView imageView);
    public abstract void fit(String url, ImageView imageView);

    public abstract void rotateDefault(String url, ImageView imageView);
    public abstract void rotateCustom(String url, ImageView imageView, float rotate);

    public abstract void customTransform(String url, ImageView imageView);
}