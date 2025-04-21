package com.example.lr49;

import android.content.Context;

public class ImageLoaderFactory {
    public static ImageLoadersClass create(Context context, ImageLibrary loaderType){
        switch (loaderType) {
            case GLIDE:
                return new GlideImageLoader(context);
            case PICASSO:
                return new PicassoImageLoader(context);
            case COIL:
                return new CoilImageLoader(context);
            default:
                throw new IllegalArgumentException("Unknown loader type");
        }
    }
}
