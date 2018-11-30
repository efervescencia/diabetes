package efervescencia.es.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        String cadenaHoras;
        String cadenaMinutos;
        if(horas<10){
            cadenaHoras="0"+horas;
        }
        else{
            cadenaHoras=""+horas;
        }
        if(minutos<10){
            cadenaMinutos="0"+minutos;
        }
        else{
            cadenaMinutos=""+minutos;
        }
        editTextHora.setText(cadenaHoras+":"+cadenaMinutos);
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
        double insulinaNumber =0;
        if(insulina.length()>0){
            insulinaNumber = Double.parseDouble(insulina);
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

        String fechaAyer;
        String ingesta;
        int glucosa_previa_ahora =0;
        double insulina_ahora=0;
        int hidratos_ahora=0;
        int glucosa_previa_ayer =0;
        int glucosa_posterior_ayer=0;
        double insulina_ayer=0;
        double total_insulina_ayer =0;
        int hidratos_ayer=0;

        //obtenemos la fecha de ayer
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.add(Calendar.DAY_OF_YEAR, -1);
        int day = calendarNow.get(Calendar.DAY_OF_MONTH);
        String dia = "";
        if(day<10){dia = "0"+day;}
        else {dia = ""+day;}
        int month = calendarNow.get(Calendar.MONTH)+1;
        String mes = "";
        if(month<10){ mes = "0"+month;}
        else {mes = ""+month;}
        int year = calendarNow.get(Calendar.YEAR);
        fechaAyer = dia+"-"+mes+"-"+year;

        Context context = getApplicationContext();
        CharSequence text = "Fecha de ayer: "+fechaAyer;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        //toast.show();

        //obtenemos datos necesarios de la lectura actual
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
            insulina_ahora = Double.parseDouble(insulina);
        }

                //Datos de ayer

        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );

        //-----Realiza una busqueda
        String[] columns={"_id","fecha", "hora", "ingesta","glucosa_previa","glucosa_posterior", "insulina", "hidratos"};
        String[] condicion={fechaAyer};

        Cursor cursor=db.query("glucosas2", columns,"fecha=?",condicion,null,null,null);

        //-----Recorremos todas las lecturas del dia con el cursor,
        // para sacar el total de insulina y encontrar la de la ingesta igual a la actual

            String result="entramos a cursor... ";

        while(cursor.moveToNext()){

            result+=cursor.getInt(0)+" ";

            total_insulina_ayer+= Double.parseDouble(cursor.getString(6));



            if(ingesta.equals(cursor.getString(3))){

                glucosa_previa_ayer = cursor.getInt(4);
                glucosa_posterior_ayer = cursor.getInt(5);
                insulina_ayer = Double.parseDouble(cursor.getString(6));
                hidratos_ayer = cursor.getInt(7);
            }
        }

        context = getApplicationContext();
        text = "Total de insulina: "+total_insulina_ayer+" "+result;
        duration = Toast.LENGTH_LONG;

        toast = Toast.makeText(context, text, duration);
        //toast.show();


        db.close();



        //Comprobamos que tenemos los datos necesarios


        //llamamos a calcular dosis
        double dosis_sugerida=0;
        Diabetes2 d = new Diabetes2();
        dosis_sugerida = d.CalcularDosis(insulina_ayer,hidratos_ayer,glucosa_previa_ayer,glucosa_posterior_ayer,total_insulina_ayer,glucosa_previa_ahora,hidratos_ahora);

        text = "InsulinaAyer: "+insulina_ayer+" HidratosAyer: "+hidratos_ayer+" GlucoPrevAyer: "+glucosa_previa_ayer+" GlucoPostAyer: "+glucosa_posterior_ayer+" TotalInsuAyer: "
                +total_insulina_ayer+" GlucoPrevAhora: "+glucosa_previa_ahora+" HidratosAhora: "+hidratos_ahora;
        toast = Toast.makeText(context, text, duration);
        toast.show();

        text = "Dosis Sugerida: "+dosis_sugerida;
        toast = Toast.makeText(context, text, duration);
        toast.show();


    }

}
