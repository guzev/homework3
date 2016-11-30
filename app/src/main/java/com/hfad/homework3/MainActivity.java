package com.hfad.homework3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static String url = "http://www.bizkit.ru/wp-content/uploads/2011/03/google-apple.jpg";
    private static String TAG = "MainActivity";
    public static String BROADCAST_NAME  = "com.hfad.homework3";
    ImageView myImage;
    TextView myText;
    MyReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.text);
        myImage = (ImageView) findViewById(R.id.image);

        final String pathOfImage = getFilesDir().getPath() + "/myImage6.jpg";

        broadcastReceiver = new MyReceiver() {
            // действия при получении сообщений
            public void onReceive(Context context, Intent intent) {
                File file = new File(pathOfImage);
                myImage.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                myImage.setVisibility(View.VISIBLE);
                myText.setVisibility(View.GONE);
            }
        };


        File file = new File(pathOfImage);
        if (file.exists()) {
            myImage.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            myImage.setVisibility(View.VISIBLE);
            myText.setVisibility(View.GONE);
        }

        IntentFilter intFilt = new IntentFilter(BROADCAST_NAME);

        registerReceiver(broadcastReceiver, intFilt);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


}
