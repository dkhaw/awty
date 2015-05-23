package edu.washington.dkhaw.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    String message = MainActivity.message;
    String phoneNumber = MainActivity.phoneNumber;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(context, "SMS sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "SMS failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
