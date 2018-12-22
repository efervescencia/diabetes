package efervescencia.es.myapplication;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_alarma extends AppCompatActivity {

    Ringtone ringtoneSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);

        if (ringtoneSound != null) {
            ringtoneSound.play();
        }

        RotateAnimation anim = new RotateAnimation(0f, 5f, 0f, 0f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);

        final ImageView splash = (ImageView) findViewById(R.id.reloj_sonando_alarma);
        splash.startAnimation(anim);
        
    }

    public void pararAlarma(View v){
        if (ringtoneSound != null) {
            ringtoneSound.stop();
        }
        finish();
    }
}
