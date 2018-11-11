package efervescencia.es.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class nueva_lectura extends AppCompatActivity {

    int _id, glucosa_previa, glucosa_posterior, insulina, hidratos;
    String ingesta;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_lectura);
        _id= getIntent().getExtras().getInt("_id");
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
        TextView tv= (TextView) findViewById(R.id.textView3);
        tv.setText(ingesta);
        String datos= "ID: "+_id+" Gl previa: "+glucosa_previa;
        EditText casilla = findViewById(R.id.editText7);
        casilla.setText(datos);
    }



    public void hecho(View v){
        finish();
    }

    public void borrar(View v){
        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );
        db.delete("glucosas","_id="+_id,null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
