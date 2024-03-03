package com.example.my_lesson_2;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.my_lesson_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final String message = "Привет Астро";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AppCompatButton sendText = findViewById(R.id.sendTextBtn);
        final AppCompatButton sendImageText = findViewById(R.id.sendImageTextBtn);
        sendImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImageFromGallery();

            }
        });
        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                startActivity(intent);
            }
        });

    }
   private void chooseImageFromGallery(){activityResultLauncher.launch("image/");}

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri o) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,message);
            intent.putExtra(Intent.EXTRA_STREAM,o);
            intent.setType("text/plain");
            intent.setType("image/jpng");
            intent.setPackage("com.whatsapp");
            startActivity(intent);
        }
    });
}