package edu.washington.dkhaw.arewethereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    private PendingIntent pendingIntent;
    public static String message;
    public static String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        final Button btnButton = (Button) findViewById(R.id.btnButton);
        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnText = btnButton.getText().toString();
                if (btnText.equals("Start")) {
                    if (checkValues()) {
                        btnButton.setText("Stop");
                        start();
                    }
                } else if (btnText.equals("Stop")) {
                    btnButton.setText("Start");
                    stop();
                }
            }
        });
    }

    private void start() {
        EditText etMinutes = (EditText) findViewById(R.id.etMinutes);
        int minutes = Integer.parseInt(etMinutes.getText().toString());
        // 60000 milliseconds = 1 minute
        int interval = minutes * 60000;

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    private boolean checkValues() {
        EditText etMessage = (EditText) findViewById(R.id.etMessage);
        message = etMessage.getText().toString();
        EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        phoneNumber = etPhoneNumber.getText().toString();
        EditText etMinutes = (EditText) findViewById(R.id.etMinutes);
        int minutes = Integer.parseInt(etMinutes.getText().toString());
        if (!message.isEmpty() && phoneNumber.matches("\\d{10}") && minutes > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void stop() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
