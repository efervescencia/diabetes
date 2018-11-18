package efervescencia.es.myapplication;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    TextView textView = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        String horas, minutos;

        if(hourOfDay<10){
            horas="0"+hourOfDay;
        }
            else{
            horas=""+hourOfDay;
        }
        if(minute<10){
            minutos="0"+minute;
        }
        else{
            minutos=""+minute;
        }

        textView.setText(horas+":"+minutos);
    }
    

    public void setTextView(EditText editTextHora) {
        textView = editTextHora;
    }
}
