package edu.washington.dkhaw.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    String message = MainActivity.message;
    String phoneNumber = MainActivity.phoneNumber;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, phoneNumber + ": " + message, Toast.LENGTH_SHORT).show();
    }
}
