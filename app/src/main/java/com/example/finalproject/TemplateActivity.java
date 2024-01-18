package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TemplateActivity extends AppCompatActivity {
    public static int stops = 0;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel("stop_channel", "stopCount", "Notification Channel to show stop counts");
    }

    @Override
    protected void onStop(){
        super.onStop();
        showNotification("stop_channel", "Stops", "Total count so far: " + ++stops);
    }

    protected void createNotificationChannel(String channelID, String channelName, String channelDescription){
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(channelDescription);
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    protected void showNotification(String channelID, String title, String text){
        Notification.Builder builder = new Notification.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text);

        notificationManager.notify(0, builder.build());
    }

    protected void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}