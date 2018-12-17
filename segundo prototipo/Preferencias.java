package efervescencia.es.myapplication;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

public class Preferencias extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Object listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniciar();
    }

    private void iniciar(){
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }



    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {


        String cadena = sharedPreferences.getString(s,"");
        int number=0;
        Toast toast = Toast.makeText(getBaseContext(), "Valor no permitido", Toast.LENGTH_LONG);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if(cadena.length()>0){
            number = Integer.parseInt(cadena);
        }
        switch(s){
            case "hipo": if(number<60 || number > 90) {
                editor.putString(s, "80");
                editor.commit();
                toast.show();
                iniciar();
            }
                break;
            case "hipoSevera": if(number<40 || number > 60) {
                editor.putString(s, "70");
                editor.commit();
                toast.show();
                iniciar();
            }
                break;

            case "hiper": if(number<160 || number > 220) {
                editor.putString(s, "180");
                editor.commit();
                toast.show();
                iniciar();
            }
                break;

            case "hiperSevera": if( number < 220) {
                editor.putString(s, "250");
                editor.commit();
                toast.show();
                iniciar();
            }
                break;
        }

    }








    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }



}

