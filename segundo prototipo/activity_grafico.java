package efervescencia.es.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Vector;


public class activity_grafico extends AppCompatActivity {

    private String fecha = "";
    private SQLiteDatabase db;
    LineChart graficoLinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        fecha = getIntent().getExtras().getString("fecha");

        graficoLinea = (LineChart) findViewById(R.id.grafico_linea);
        LineDataSet lineDataSet = new LineDataSet(cargarDatos(),"Lecturas de glucosa");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData  data = new LineData(dataSets);
        graficoLinea.setData(data);
        graficoLinea.setDescription(new Description());
        graficoLinea.invalidate();
    }


    public ArrayList<Entry> cargarDatos(){
        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );

        //-----Realiza una busqueda
        String[] columns={"_id","hora","glucosa_previa","glucosa_posterior"};
        String[] condicion={fecha};

        Cursor cursor=db.query("glucosas2", columns,"fecha=?", condicion, null ,null,"hora",null);

        ArrayList<Entry> dataVals = new ArrayList<Entry>();

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                float hora = Float.parseFloat(cursor.getString(1).substring(0,1));
                int glucosaPrevia = cursor.getInt(2);
                int glucosaPosterior = cursor.getInt(3);
                if(glucosaPrevia>0){
                    dataVals.add(new Entry(hora+0,glucosaPrevia+0));
                }
                if(glucosaPosterior>0){
                    dataVals.add(new Entry(hora+2,glucosaPrevia+0));
                }
            } while(cursor.moveToNext());
        }
        else{
            Toast toast = Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG);
            toast.show();
        }
        db.close();
        return dataVals;
    }





    public void volver(View v){
        finish();
    }






}


