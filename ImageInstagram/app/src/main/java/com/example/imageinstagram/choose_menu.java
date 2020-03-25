package com.example.imageinstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imageinstagram.Utils.BitmapUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import com.example.imageinstagram.MainActivity;

import java.util.List;

import ja.burhanrashid52.photoeditor.OnSaveBitmap;

import static com.example.imageinstagram.MainActivity.PERMISSION_PICK_IMAGE;


public class choose_menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_menu);
        SharedPreferences preferences = getSharedPreferences("open_image",MODE_PRIVATE);
        String check_status = preferences.getString("remember","");

        SharedPreferences preferences1 = getSharedPreferences("take_image",MODE_PRIVATE);
        String check_status1 = preferences1.getString("remember1","");

        ImageView im1 = findViewById(R.id.btn_choose1);
        ImageView im2 = findViewById(R.id.btn_choose2);


        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("open_image ", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.commit();

                SharedPreferences preferences1 = getSharedPreferences("take_image", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = preferences1.edit();
                editor1.putString("remember1", "true");
                editor1.commit();
                //Toast.makeText(getApplicationContext(), "openimage"+editor.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences1 = getSharedPreferences("take_image ", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = preferences1.edit();
                editor1.putString("remember1", "false");
                editor1.commit();
                //Toast.makeText(getApplicationContext(), "Choose photo from album", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getSharedPreferences("open_image", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "true");
                editor.commit();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                //openImageFromGallery();

            }
        });


    }

    private void openImage(String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path), "image/*");
        startActivity(intent);

    }

    private void openImageFromGallery() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");

                            startActivityForResult(intent, PERMISSION_PICK_IMAGE);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }

}