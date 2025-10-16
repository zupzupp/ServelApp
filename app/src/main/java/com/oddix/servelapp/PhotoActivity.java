package com.oddix.servelapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    private ImageView ivPreview;
    private ActivityResultLauncher<Intent> takePictureLauncher;
    private ActivityResultLauncher<String> pickImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ivPreview = findViewById(R.id.ivPreview);
        Button btnTake = findViewById(R.id.btnTake);
        Button btnPick = findViewById(R.id.btnPick);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        TextView btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(v -> finish());

        btnEnviar.setOnClickListener(v -> {
            ivPreview.setImageDrawable(null);
            Toast.makeText(this, "Imagen enviada", Toast.LENGTH_SHORT).show();
        });

        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        if (extras != null) {
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            ivPreview.setImageBitmap(imageBitmap);
                        }
                    }
                });

        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) ivPreview.setImageURI(uri);
                });

        btnTake.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureLauncher.launch(intent);
        });

        btnPick.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
    }
}
