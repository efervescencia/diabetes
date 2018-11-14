package efervescencia.es.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class nueva_lectura extends AppCompatActivity {

    int _id, glucosa_previa, glucosa_posterior, insulina, hidratos;
    String ingesta, fecha, hora;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_lectura);
        _id= getIntent().getExtras().getInt("_id");
        fecha = getIntent().getExtras().getString("fecha");
        hora = getIntent().getExtras().getString("hora");
        ingesta =getIntent().getExtras().getString("ingesta");
        glucosa_previa=getIntent().getExtras().getInt("glucosa_previa");
        glucosa_posterior=getIntent().getExtras().getInt("glucosa_posterior");
        insulina=getIntent().getExtras().getInt("insulina");
        hidratos=getIntent().getExtras().getInt("hidratos");
        /*
        Spinner sp = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipos_comida, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter1);
        sp.setSelection(ingesta, true);
        */
        EditText cuadroFecha = findViewById(R.id.editTextActualizarFecha);
        cuadroFecha.setText(fecha);
        EditText cuadroHora = findViewById(R.id.editTextActualizarHora);
        cuadroHora.setText(hora);
        TextView tv= (TextView) findViewById(R.id.textViewIngesta);
        tv.setText(ingesta);
        EditText cuadroGlucosaPrevia = findViewById(R.id.editTextActualizarGlucosaPrevia);
        cuadroGlucosaPrevia.setText(""+glucosa_previa);
        EditText cuadroGlucosaPosterior = (EditText) findViewById(R.id.editTextActualizarGlucosaPosterior);
        cuadroGlucosaPosterior.setText(""+glucosa_posterior);
        EditText cuadroHidratos = findViewById(R.id.editTextActualizarHidratos);
        cuadroHidratos.setText(""+hidratos);
        EditText cuadroInsulina = findViewById(R.id.editTextActualizarInsulina);
        cuadroInsulina.setText(""+insulina);
    }



    public void hecho(View v){
        finish();
    }

    public void borrar(View v){
        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );
        db.delete("glucosas2","_id="+_id,null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void actualizar(View v){

        EditText editTextFecha = (EditText) findViewById(R.id.editTextActualizarFecha);
        String fecha = editTextFecha.getText().toString();
        EditText editTextHora = (EditText) findViewById(R.id.editTextActualizarHora);
        String hora = editTextHora.getText().toString();

        EditText editTextGlucosaPrevia = (EditText) findViewById(R.id.editTextActualizarGlucosaPrevia);
        String glucosaPrevia = editTextGlucosaPrevia.getText().toString();
        int glucosaPreviaNumber = 0;
        if(glucosaPrevia.length()>0) {
            glucosaPreviaNumber = Integer.parseInt(editTextGlucosaPrevia.getText().toString());
        }

        EditText editTextGlucosaPosterior = (EditText) findViewById(R.id.editTextActualizarGlucosaPosterior);
        String glucosaPosterior = editTextGlucosaPosterior.getText().toString();
        int glucosaPosteriorNumber = 0;
        if(glucosaPosterior.length()>0){
            glucosaPosteriorNumber = Integer.parseInt(glucosaPosterior);
        }

        EditText editTextHidratos = (EditText) findViewById(R.id.editTextActualizarHidratos);
        String hidratos = editTextHidratos.getText().toString();
        int hidratosNumber =0;
        if(hidratos.length()>0){
            hidratosNumber= Integer.parseInt(hidratos);
        }

        EditText editTextInsulina = (EditText) findViewById(R.id.editTextActualizarInsulina);
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
        db.update("glucosas2", values,"_id="+_id,null);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }




}
