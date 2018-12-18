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

public class activity_crear_alarma extends AppCompatActivity {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarma);

    }

    public void ponerAlarma(View v) {
        Context ctx = getApplicationContext();
        alarmMgr = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ctx, activity_alarma.class);
        alarmIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);

        int minutos = 0;

        if (findViewById(R.id.radioButton_15_min).isSelected()) {
            minutos = 15;
        } else if (findViewById(R.id.radioButton_1_h).isSelected()) {
            minutos = 60;
        } else if (findViewById(R.id.radioButton_2_h).isSelected()) {
            minutos = 120;
        }

        if (minutos > 0) {
            alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() +
                            minutos * 60 * 1000, alarmIntent);

        }

        volverAPrincipal();
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
