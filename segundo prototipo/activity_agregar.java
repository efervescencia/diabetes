package efervescencia.es.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class activity_agregar extends AppCompatActivity {

    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        fecha = getIntent().getExtras().getString("fecha");
        EditText editFecha = findViewById(R.id.editTextFecha);
        editFecha.setText(fecha);

    }
}
