package com.example.lr49;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.security.MessageDigest;

public class GlideImageLoader extends ImageLoadersClass {

    public GlideImageLoader(Context context) {
        super(context);
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId) {
        Glide.with(context)
                .load(url)
                .placeholder(placeholderResId)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, int placeholderResId, int errorResId) {
        Glide.with(context)
                .load(url)
                .placeholder(placeholderResId)
                .error(errorResId)
                .into(imageView);
    }

    @Override
    public void loadImage(String url, ImageView imageView, ImageLoadCallback callback) {
        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        callback.onFailure(e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        callback.onSuccess();
                        return false;
                    }
                })
                .into(imageView);
    }

    @Override
    public void resizeImageDefault(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .override(100, 100)
                .into(imageView);
    }

    @Override
    public void resizeImageCustom(String url, ImageView imageView, int resizeWidth, int resizeHeight) {
        if (resizeWidth != 0 && resizeHeight != 0) {
            Glide.with(context)
                    .load(url)
                    .override(resizeWidth, resizeHeight)
                    .into(imageView);
        } else {
            resizeImageDefault(url, imageView);
        }
    }

    @Override
    public void centerCrop(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void centerInside(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .override(100, 100)
                .centerInside()
                .into(imageView);
    }

    @Override
    public void fit(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fitCenter()
                .into(imageView);
    }

    @Override
    public void rotateDefault(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new RotateTransformation(90f))
                .into(imageView);
    }

    @Override
    public void rotateCustom(String url, ImageView imageView, float rotateF) {
        if (rotateF != 0) {
            Glide.with(context)
                    .load(url)
                    .transform(new RotateTransformation(rotateF))
                    .into(imageView);
        } else {
            rotateDefault(url, imageView);
        }
    }

    @Override
    public void customTransform(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new CircleCrop())
                .into(imageView);
    }

    // Внутренний класс для поворота изображения
    private static class RotateTransformation extends BitmapTransformation {
        private final float rotateRotationAngle;

        public RotateTransformation(float rotateRotationAngle) {
            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotateRotationAngle);
            return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
            messageDigest.update(("rotate" + rotateRotationAngle).getBytes());
        }
    }
}