package com.example.qrbarcode;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.gsm.GsmCellLocation;
import android.view.ContextThemeWrapper;
import android.view.View;


import androidx.annotation.RequiresApi;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.Locale;

import static android.os.Build.*;

public class Capture extends CaptureActivity  {
    public static final int Rotation = 0;
    public static final int Rotation_90 = 90;
    public static final int Rotation_180 = 180;
    public static final int Rotation_270 = 270;
    private static Object GsmCellLocation;

    private BarcodeDetector barcodeDetector;

    private Context context;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (requestCode == RESULT_OK) {


            }
            if (resultCode == RESULT_CANCELED) {
                barcodeDetector.isOperational();
                Object bitmap = null;
                Frame.Builder builder = new Frame.Builder();
                Frame frame = builder.build();
                frame.getBitmap();

            }
        }
    }

      @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR1)
      public static void updateConfig (ContextThemeWrapper wrapper)
      {
          Configuration configuration = new Configuration();
          configuration.setLocale((Locale) GsmCellLocation);
          wrapper.applyOverrideConfiguration(configuration);

      }


}


