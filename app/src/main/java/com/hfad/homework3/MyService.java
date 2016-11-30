package com.hfad.homework3;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ivan on 30.11.16.
 */

public class MyService extends IntentService {

    public MyService(){
        super("MyService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        try {
            URL url = new URL(intent.getStringExtra("url"));
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            FileOutputStream fileOutputStream = openFileOutput("myImage6.jpg", Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
