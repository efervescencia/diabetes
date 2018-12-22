package efervescencia.es.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;


public class activity_crear_alarma extends AppCompatActivity {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarma);
    }

    public void ponerAlarma(View v) {
        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(), MyAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 234324243, intent, 0);

        RadioButton r1 = (RadioButton) findViewById(R.id.radioButton_15_min);
        RadioButton r2 = (RadioButton) findViewById(R.id.radioButton_1_h);
        RadioButton r3 = (RadioButton) findViewById(R.id.radioButton_2_h);

        int minutos_alarma = 0;

        if (r1.isChecked()) {
            minutos_alarma = 15;
        } else if (r2.isChecked()) {
            minutos_alarma = 60;
        } else if (r3.isChecked()) {
            minutos_alarma = 120;
        }

        if (minutos_alarma > 0) {
            alarmMgr.set(AlarmManager.RTC_WAKEUP, (SystemClock.elapsedRealtime()+(100000*60)), pendingIntent);
            Toast toast = Toast.makeText(getBaseContext(), "Alarma fijada en "+minutos_alarma+" minutos.", Toast.LENGTH_LONG);
            toast.show();
            volverAPrincipal();
        }

    }

    public void cancelarAlarma(View v){
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
        volverAPrincipal();
    }


    public void volverAPrincipal(){
        startActivity(new Intent(this, MainActivity.class));
    }

}
