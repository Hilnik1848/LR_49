package com.example.lr49;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WorkSpace extends AppCompatActivity {

    private ImageLoadersClass imageLoader;
    private ImageLibrary selectedLibrary;
    private final String IMAGE_URL =
            "https://t4.ftcdn.net/jpg/02/66/72/41/360_F_266724172_Iy8gdKgMa7XmrhYYxLCxyhx6J7070Pr8.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_space);

        String libraryName = getIntent().getStringExtra("IMAGE_LIBRARY");
        selectedLibrary = ImageLibrary.valueOf(libraryName);
        imageLoader = ImageLoaderFactory.create(this, selectedLibrary);

        ImageView imageView = findViewById(R.id.imageView);
        Button buttonDefault = findViewById(R.id.buttonDefault);
        Button buttonResize = findViewById(R.id.buttonResize);
        EditText etWidth = findViewById(R.id.ETW);
        EditText etHeight = findViewById(R.id.ETH);
        Button buttonCrop = findViewById(R.id.buttonCrop);
        Button buttonInside = findViewById(R.id.buttonInside);
        Button buttonFit = findViewById(R.id.buttonFit);
        Button buttonPlaceholder = findViewById(R.id.buttonPlaceholder);
        Button buttonCallback = findViewById(R.id.buttonCallback);
        Button buttonError = findViewById(R.id.buttonError);
        Button buttonRotate = findViewById(R.id.buttonRotate);
        Button buttonCircle = findViewById(R.id.buttonCircle);
        Button buttonGif = findViewById(R.id.buttonGif);

        buttonDefault.setOnClickListener(v -> imageLoader.loadImage(IMAGE_URL, imageView));

        buttonResize.setOnClickListener(v -> {
            try {
                int width = Integer.parseInt(etWidth.getText().toString());
                int height = Integer.parseInt(etHeight.getText().toString());
                imageLoader.resizeImageCustom(IMAGE_URL, imageView, width, height);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid width and height",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonCrop.setOnClickListener(v -> imageLoader.centerCrop(IMAGE_URL, imageView));
        buttonInside.setOnClickListener(v -> imageLoader.centerInside(IMAGE_URL, imageView));
        buttonFit.setOnClickListener(v -> imageLoader.fit(IMAGE_URL, imageView));

        buttonPlaceholder.setOnClickListener(v ->
                imageLoader.loadImage(IMAGE_URL, imageView, R.drawable.baseline_healing_24));

        buttonError.setOnClickListener(v ->
                imageLoader.loadImage("invalid_url", imageView, R.drawable.baseline_healing_24,
                        R.drawable.baseline_error_24));

        buttonCallback.setOnClickListener(v ->
                imageLoader.loadImage(IMAGE_URL, imageView, new ImageLoadCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(WorkSpace.this, "Image loaded successfully",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(WorkSpace.this, "Failed to load image",
                                Toast.LENGTH_SHORT).show();
                    }
                }));

        buttonRotate.setOnClickListener(v -> imageLoader.rotateDefault(IMAGE_URL, imageView));
        buttonCircle.setOnClickListener(v -> imageLoader.customTransform(IMAGE_URL, imageView));

        buttonGif.setOnClickListener(v -> {
            String gifUrl = "https://media.tenor.com/f_saBHiUTz0AAAAM/cute-cat.gif";
            imageLoader.loadImage(gifUrl, imageView);
        });

        imageLoader.loadImage(IMAGE_URL, imageView);
    }
}