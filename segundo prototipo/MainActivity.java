package efervescencia.es.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends Activity implements OnItemClickListener{

    Calendar calendarNow = null;
    String fecha = "";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );
        db.execSQL("create table if not exists glucosas2 " +
            "(_id integer primary key, fecha string, hora string, ingesta string, "+
                "glucosa_previa integer, glucosa_posterior integer, insulina integer, hidratos integer);");

        //llena la tabla de operas
        //llenaTabla();

        //-----Realiza una busqueda
        String[] columns={"_id","fecha", "hora", "ingesta","glucosa_previa","glucosa_posterior", "insulina", "hidratos"};

        Cursor cursor=db.query("glucosas2", columns,null,null,null,null,null);

        //---Adapta el cursor al listview
        ListView lv =(ListView) findViewById(R.id.listView);
        String[] from={"hora","ingesta","glucosa_previa","glucosa_posterior"};
        int[] to={R.id.listadoHora, R.id.listadoIngesta, R.id.listadoGlucosaPrevia, R.id.listadoGlucosaPosterior};
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, R.layout.list, cursor, from,to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        db.close();


        calendarNow = Calendar.getInstance();
        actualizarFecha();


    }

    void llenaTabla(){
        insertaFila("10-10-2018","10:10","Desayuno",120,178, 3, 3);
        insertaFila("10-10-2018","10:10","Almuerzo",120,178, 3, 3);
        insertaFila("10-10-2018","10:10","Comida",120,178, 3, 3);
        insertaFila("10-10-2018","10:10","Merienda",120,178, 3, 3);
        insertaFila("10-10-2018","10:10","Cena",120,178, 3, 3);


    }

    void insertaFila(String fecha, String hora, String ingesta, int glucosa_previa, int glucosa_posterior, int insulina, int hidratos){

        ContentValues values= new ContentValues();
        values.put("fecha", fecha);
        values.put("hora", hora);
        values.put("ingesta", ingesta);
        values.put("glucosa_previa", glucosa_previa);
        values.put("glucosa_posterior", glucosa_posterior);
        values.put("insulina", insulina);
        values.put("hidratos", hidratos);
        db.insert("glucosas2", null, values);
    }


    public void actualizarFecha(){
        TextView viewFecha = (TextView) findViewById(R.id.fecha);
        int day = calendarNow.get(Calendar.DAY_OF_MONTH);
        String dia = "";
        if(day<10){dia = "0"+day;}
            else {dia = ""+day;}
        int month = calendarNow.get(Calendar.MONTH);
        String mes = "";
        if(month<10){ mes = "0"+month;}
            else {mes = ""+month;}
        int year = calendarNow.get(Calendar.YEAR);
        fecha = dia+"-"+mes+"-"+year;
        viewFecha.setText(fecha);
    }

    public void aumentarDia(View v){
        calendarNow.add(Calendar.DAY_OF_YEAR, 1);
        actualizarFecha();
    }

    public void disminuirDia(View v){
        calendarNow.add(Calendar.DAY_OF_YEAR, -1);
        actualizarFecha();
    }


public void agregar(View v){
    Intent intent = new Intent(this, activity_agregar.class);
    intent.putExtra("fecha", fecha);
    startActivity(intent);
}


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        int _id=cursor.getInt(0);
        String fecha=cursor.getString(1);
        String hora=cursor.getString(2);
        String ingesta=cursor.getString(3);
        int glucosa_previa=cursor.getInt(4);
        int glucosa_posterior=cursor.getInt(5);
        int insulina=cursor.getInt(6);
        int hidratos=cursor.getInt(7);

        /*
        Context context = getApplicationContext();
        CharSequence text = "Hello toast! "+_id+ " "+ingesta+" "+glucosa_previa;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        */

        Intent intent = new Intent(this, nueva_lectura.class);
        intent.putExtra("_id", _id);
        intent.putExtra("fecha", fecha);
        intent.putExtra("hora", hora);
        intent.putExtra("ingesta", ingesta);
        intent.putExtra("glucosa_previa",glucosa_previa);
        intent.putExtra("glucosa_posterior", glucosa_posterior);
        intent.putExtra("insulina", insulina);
        intent.putExtra("hidratos",hidratos);
        startActivity(intent);

    }
}
