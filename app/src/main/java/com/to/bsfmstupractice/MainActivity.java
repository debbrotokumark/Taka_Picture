package com.to.bsfmstupractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private  String currentimagepath;
    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String filename="photo";
              File storageDictory= getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                try {
                    File imagefile=File.createTempFile(filename,".jpg",storageDictory);
                    currentimagepath=imagefile.getAbsolutePath();

                 Uri imageuri= FileProvider.getUriForFile(MainActivity.this,"com.to.bsfmstupractice.fileprovider",imagefile);

                    Intent  intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
                    startActivityForResult(intent,1);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK)
        {
            Bitmap bitmap= BitmapFactory.decodeFile(currentimagepath);
            imageView.setImageBitmap(bitmap);
        }

    }
}