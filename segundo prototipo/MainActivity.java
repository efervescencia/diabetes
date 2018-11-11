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
    TextView tv1, tv2;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView) findViewById(R.id.textView);
        tv2=(TextView) findViewById(R.id.textView2);

        db=openOrCreateDatabase("musica.db", MODE_PRIVATE, null );
        db.execSQL("create table if not exists operas1 " +
            "(_id integer primary key, titulo text, "+
                "compositor text, year integer);");

        //llena la tabla de operas
        //llenaTabla();

        //-----Realiza una busqueda
        String[] columns={"_id","titulo","compositor","year"};

        Cursor cursor=db.query("operas1", columns,null,null,null,null,null);

        //---Adapta el cursor al listview
        ListView lv =(ListView) findViewById(R.id.listView);
        String[] from={"titulo","compositor"};
        int[] to={R.id.textView, R.id.textView2};
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, R.layout.list, cursor, from,to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        db.close();


        calendarNow = Calendar.getInstance();
        actualizarFecha();
    }

    void llenaTabla(){
        insertaFila("Don Giovanni","W.A.Mozart", 1787);
        insertaFila("Giulio Cesare", "G.F. Haendel", 1724);
        insertaFila("Orlando Furioso","A. Vivaldi", 1727);
    }

    void insertaFila(String titulo, String compositor, int year){

        ContentValues values= new ContentValues();
        values.put("titulo", titulo);
        values.put("compositor", compositor);
        values.put("year", year);
        db.insert("operas1", null, values);
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


public void nuevaLectura(View v){
    setContentView(R.layout.activity_nueva_lectura);

}


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        int _id=cursor.getInt(0);
        String titulo=cursor.getString(1);
        String compositor=cursor.getString(2);
        int year=cursor.getInt(3);

        Context context = getApplicationContext();
        CharSequence text = "Hello toast! "+_id+ " "+compositor+" "+year;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, nueva_lectura.class);
        intent.putExtra(EXTRA_MESSAGE, text);
        startActivity(intent);

    }
}
