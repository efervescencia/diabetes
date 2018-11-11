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

        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );
        db.execSQL("create table if not exists glucosas " +
            "(_id integer primary key, ingesta string, "+
                "glucosa_previa integer, glucosa_posterior integer, insulina integer, hidratos integer);");

        //llena la tabla de operas
        //llenaTabla();

        //-----Realiza una busqueda
        String[] columns={"_id","ingesta","glucosa_previa","glucosa_posterior", "insulina", "hidratos"};

        Cursor cursor=db.query("glucosas", columns,null,null,null,null,null);

        //---Adapta el cursor al listview
        ListView lv =(ListView) findViewById(R.id.listView);
        String[] from={"ingesta","glucosa_previa"};
        int[] to={R.id.textView, R.id.textView2};
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, R.layout.list, cursor, from,to);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        db.close();


        calendarNow = Calendar.getInstance();
        actualizarFecha();


    }

    void llenaTabla(){
        insertaFila("desayuno",133,156,3,3);
        insertaFila("comida",111,111,2,6);
        insertaFila("merienda",98,148,3,2);
        insertaFila("cena",127,221,3,5);

    }

    void insertaFila(String ingesta, int glucosa_previa, int glucosa_posterior, int insulina, int hidratos){

        ContentValues values= new ContentValues();
        values.put("ingesta", ingesta);
        values.put("glucosa_previa", glucosa_previa);
        values.put("glucosa_posterior", glucosa_posterior);
        values.put("insulina", insulina);
        values.put("hidratos", hidratos);
        db.insert("glucosas", null, values);
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
        String ingesta=cursor.getString(1);
        int glucosa_previa=cursor.getInt(2);
        int glucosa_posterior=cursor.getInt(3);
        int insulina=cursor.getInt(4);
        int hidratos=cursor.getInt(5);

        Context context = getApplicationContext();
        CharSequence text = "Hello toast! "+_id+ " "+ingesta+" "+glucosa_previa;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, nueva_lectura.class);
        intent.putExtra("_id", _id);
        intent.putExtra("ingesta", ingesta);
        intent.putExtra("glucosa_previa",glucosa_previa);
        intent.putExtra("glucosa_posterior", glucosa_posterior);
        intent.putExtra("insulina", insulina);
        intent.putExtra("hidratos",hidratos);
        startActivity(intent);

    }
}
