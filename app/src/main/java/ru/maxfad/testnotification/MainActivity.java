package ru.maxfad.testnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setAutoCancel(true)
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(pendingIntent)
                                .setContentTitle("Заголовок")
                                .setContentText("Какой то текс")
                                .setOngoing(true)
//                                .setTimeoutAfter(6000)
//                                .setProgress(100,0, true)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText("fnjfffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                                        "аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                                        "аааааааааааааааааааааааааааааааааааааааааааааааааа"))
                                .setPriority(PRIORITY_HIGH);
                createChannelIfNeeded(notificationManager);
                NOTIFY_ID+=1;
                notificationManager.notify(NOTIFY_ID, notificationBuilder.build());


    }

    public static void  createChannelIfNeeded(NotificationManager manager){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }


}
