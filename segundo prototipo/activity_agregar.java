package efervescencia.es.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class activity_agregar extends AppCompatActivity {

    private static final String TAG = "";
    Calendar calendarNow = null;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        String fecha = getIntent().getExtras().getString("fecha");
        EditText editFecha = findViewById(R.id.editTextAgregarFecha);
        editFecha.setText(fecha);

        EditText editTextHora = (EditText) findViewById(R.id.editTextAgregarHora);
        calendarNow = Calendar.getInstance();
        Integer horas = calendarNow.get(Calendar.HOUR_OF_DAY);
        Integer minutos = calendarNow.get(Calendar.MINUTE);
        String hora = horas+":"+minutos;
        editTextHora.setText(hora);
    }

    public void hecho(View v){
        finish();
    }

    public void guardar(View v) {

        EditText editTextFecha = (EditText) findViewById(R.id.editTextAgregarFecha);
        String fecha = editTextFecha.getText().toString();

        EditText editTextHora = (EditText) findViewById(R.id.editTextAgregarHora);
        String hora = editTextHora.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.spinnerAgregar);
        String ingesta = spinner.getSelectedItem().toString();

        EditText editTextGlucosaPrevia = (EditText) findViewById(R.id.editTextAgregarGlucosaPrevia);
        String glucosaPrevia = editTextGlucosaPrevia.getText().toString();
        int glucosaPreviaNumber = 0;
        if(glucosaPrevia.length()>0) {
            glucosaPreviaNumber = Integer.parseInt(editTextGlucosaPrevia.getText().toString());
        }

        EditText editTextGlucosaPosterior = (EditText) findViewById(R.id.editTextAgregarGlucosaPosterior);
        String glucosaPosterior = editTextGlucosaPosterior.getText().toString();
        int glucosaPosteriorNumber = 0;
        if(glucosaPosterior.length()>0){
            glucosaPosteriorNumber = Integer.parseInt(glucosaPosterior);
        }

        EditText editTextHidratos = (EditText) findViewById(R.id.editTextAgregarHidratos);
        String hidratos = editTextHidratos.getText().toString();
        int hidratosNumber =0;
        if(hidratos.length()>0){
            hidratosNumber= Integer.parseInt(hidratos);
        }

        EditText editTextInsulina = (EditText) findViewById(R.id.editTextAgregarInsulina);
        String insulina = editTextInsulina.getText().toString();
        System.out.println(insulina.length());
        int insulinaNumber =0;
        if(insulina.length()>0){
            insulinaNumber = Integer.parseInt(insulina);
        }

        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );

        ContentValues values= new ContentValues();
        values.put("fecha", fecha);
        values.put("hora", hora);
        values.put("ingesta", ingesta);
        values.put("glucosa_previa", glucosaPreviaNumber);
        values.put("glucosa_posterior", glucosaPosteriorNumber);
        values.put("insulina", insulinaNumber);
        values.put("hidratos", hidratosNumber);
        db.insert("glucosas2", null, values);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public void showTimePickerDialog(View v) {
        EditText editTextHora = (EditText) findViewById(R.id.editTextAgregarHora);
        DialogFragment newFragment = new TimePickerFragment();
        ((TimePickerFragment) newFragment).setTextView(editTextHora);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        EditText editTextFecha = (EditText) findViewById(R.id.editTextAgregarFecha);
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment) newFragment).setTextView(editTextFecha);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



    public void sugerirDosis(View v){


        //avisamos de datos necesarios


        //Recopilamos datos

        String fecha;
        String ingesta;
        int glucosa_previa_ahora =0;
        double insulina_ahora=0;
        int hidratos_ahora=0;
        int glucosa_previa_ayer;
        int glucosa_posterior_ayer;
        double insulina_ayer;
        double total_insulina_ayer;
        int hidratos_ayer;


        EditText editTextFecha = (EditText) findViewById(R.id.editTextAgregarFecha);
        fecha = editTextFecha.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.spinnerAgregar);
        ingesta = spinner.getSelectedItem().toString();

        EditText editTextGlucosaPrevia = (EditText) findViewById(R.id.editTextAgregarGlucosaPrevia);
        String glucosaPrevia = editTextGlucosaPrevia.getText().toString();
        if(glucosaPrevia.length()>0) {
            glucosa_previa_ahora = Integer.parseInt(editTextGlucosaPrevia.getText().toString());
        }

        EditText editTextHidratos = (EditText) findViewById(R.id.editTextAgregarHidratos);
        String hidratos = editTextHidratos.getText().toString();
        if(hidratos.length()>0){
            hidratos_ahora= Integer.parseInt(hidratos);
        }

        EditText editTextInsulina = (EditText) findViewById(R.id.editTextAgregarInsulina);
        String insulina = editTextInsulina.getText().toString();
        System.out.println(insulina.length());
        if(insulina.length()>0){
            insulina_ahora = Integer.parseInt(insulina);
        }

                //Datos de ayer



        //Comprobamos que tenemos los datos necesarios


        //llamamos a calcular dosis

        Diabetes2 d = new Diabetes2();
        d.CalcularDosis(3,6,135,189,18,119,6);

    }

}
