package com.example.qrbarcode;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.integration.android.IntentIntegrator;

import java.io.IOException;


public class Barcode extends AppCompatActivity  implements View.OnClickListener{

    private BarcodeDetector barcodeDetector;
    private Button Scan;
    private ImageView imageView;
    private CameraSource cameraSource;
    private SurfaceView surfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        imageView = findViewById(R.id.imagescan);
        Scan = findViewById(R.id.scanitems);

        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    Intent intent = new Intent("com.google.zxing.client.android.Scan");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    startActivityForResult(intent , 0);

                }
                catch (Exception e)
                {
                  Uri markerUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                  Intent marketIntent = new Intent(Intent.ACTION_VIEW, markerUri);
                  startActivity(marketIntent);
                }
            }
        });


        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.MODE_WORLD_READABLE).build();
        cameraSource = new  CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true).build();
        surfaceView.getBackground();
        surfaceView.getImportantForAccessibility();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            private static final int REQUEST_CAMERA_PERMISSION = 0;

            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder)
            {
               try
               {
                   if (ActivityCompat.checkSelfPermission(Barcode.this
                           , Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                   {
                       cameraSource.start(surfaceView.getHolder());
                   }
                   else
                   {
                      ActivityCompat.requestPermissions(Barcode.this, new String[]
                              {
                                      Manifest.permission.CAMERA
                              },REQUEST_CAMERA_PERMISSION);
                   }
               }
               catch (IOException e)
               {
                   e.printStackTrace();
               }

            }
            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v)
    {
        try {
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setCaptureActivity(Capture.class);
            intentIntegrator.initiateScan();
            intentIntegrator.setOrientationLocked(getActionBar().isHideOnContentScrollEnabled());
            intentIntegrator.setBarcodeImageEnabled(true);
            intentIntegrator.createScanIntent();
            intentIntegrator.setCameraId(0);
            intentIntegrator.setPrompt("Scan done Sucessfully ");
            intentIntegrator.setBeepEnabled(false);
        }
        catch (ArithmeticException exception)
        {
            exception.printStackTrace();
        }




    }
}