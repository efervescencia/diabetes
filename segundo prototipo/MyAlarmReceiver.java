package efervescencia.es.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();
        context.startActivity(new Intent(context, activity_alarma.class));
    }
}