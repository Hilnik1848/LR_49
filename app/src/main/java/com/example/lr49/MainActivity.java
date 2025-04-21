package com.example.lr49;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPicasso = findViewById(R.id.buttonPicasso);
        Button buttonGlide = findViewById(R.id.buttonGlide);
        Button buttonCoil = findViewById(R.id.buttonCoil);

        buttonPicasso.setOnClickListener(v -> launchWorkSpace(ImageLibrary.PICASSO));
        buttonGlide.setOnClickListener(v -> launchWorkSpace(ImageLibrary.GLIDE));
        buttonCoil.setOnClickListener(v -> launchWorkSpace(ImageLibrary.COIL));
    }

    private void launchWorkSpace(ImageLibrary library) {
        Intent intent = new Intent(this, WorkSpace.class);
        intent.putExtra("IMAGE_LIBRARY", library.name());
        startActivity(intent);
    }

}