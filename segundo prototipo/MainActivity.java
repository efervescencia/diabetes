package efervescencia.es.myapplication;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Vector;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnItemClickListener{

    private Calendar calendarNow = null;
    private String fecha = "";
    private SQLiteDatabase db;
    private int hipo, hiper, hipoSevera, hiperSevera;
    private Toast toast;
    private boolean DEBUG = false;
    private Vector<Lectura> lecturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        calendarNow = Calendar.getInstance();
        actualizarFecha();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        hipo = Integer.parseInt(pref.getString("hipo","80").replaceAll("\\D+",""));
        hiper = Integer.parseInt(pref.getString("hiper","180").replaceAll("\\D+",""));
        hipoSevera = Integer.parseInt(pref.getString("hipoSevera","70").replaceAll("\\D+",""));
        hiperSevera = Integer.parseInt(pref.getString("hiperSevera","250").replaceAll("\\D+",""));

        if(DEBUG) {
            toast = Toast.makeText(this, "HELLO: " + hipo + " " + hiper + " " + hipoSevera + " " + hiperSevera, Toast.LENGTH_LONG);
            toast.show();
        }

        llenarListView();
        ListView lv = findViewById(R.id.listView);

                lv.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                disminuirDia(null);
            }
            public void onSwipeLeft() {
                aumentarDia(null);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Preferencias.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_alarm) {
            startActivity(new Intent(this, activity_crear_alarma.class));
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, activity_grafico.class);
            intent.putExtra("fecha", fecha);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {
            finishAffinity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void llenarListView(){
        db=openOrCreateDatabase("glucosa.db", MODE_PRIVATE, null );
        db.execSQL("create table if not exists glucosas2 " +
                "(_id integer primary key, fecha string, hora string, ingesta string, "+
                "glucosa_previa integer, glucosa_posterior integer, insulina string, hidratos integer);");


        //-----Realiza una busqueda
        String[] columns={"_id","fecha", "hora", "ingesta","glucosa_previa","glucosa_posterior", "insulina", "hidratos"};
        String[] condicion={fecha};

        Cursor cursor=db.query("glucosas2", columns,"fecha=?", condicion, null ,null,"hora",null);

        /*---Adapta el cursor al listview
        ListView lv =(ListView) findViewById(R.id.listView);
        String[] from={"hora","ingesta","glucosa_previa","glucosa_posterior"};
        int[] to={R.id.listadoHora, R.id.listadoIngesta, R.id.listadoGlucosaPrevia, R.id.listadoGlucosaPosterior};
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, R.layout.list, cursor, from,to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        db.close();
        */

        ListView lv=(ListView) findViewById(R.id.listView);
        lecturas = new Vector<Lectura>();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Lectura l = new Lectura (cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5));
                l.setId(cursor.getInt(0));
                l.setFecha(cursor.getString(1));
                l.setInsulina(cursor.getString(6));
                l.setHidratos(cursor.getInt(7));
                lecturas.add(l);
            } while(cursor.moveToNext());
        }
        //leemos los valores de prefs

        //creamos el adapter del listview
        MiAdaptador miAdaptador = new MiAdaptador(this,lecturas, hipo, hiperSevera);
        lv.setAdapter(miAdaptador);
        lv.setOnItemClickListener(this);
        db.close();
    }


    void llenaTabla(){
        insertaFila("10-10-2018","10:10","Desayuno",120,178, "3", 3);
        insertaFila("10-10-2018","10:10","Almuerzo",120,178, "3", 3);
        insertaFila("10-10-2018","10:10","Comida",120,178, "3", 3);
        insertaFila("10-10-2018","10:10","Merienda",120,178, "3", 3);
        insertaFila("10-10-2018","10:10","Cena",120,178, "3.5", 3);


    }

    void insertaFila(String fecha, String hora, String ingesta, int glucosa_previa, int glucosa_posterior, String insulina, int hidratos){

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
        int month = calendarNow.get(Calendar.MONTH)+1;
        String mes = "";
        if(month<10){ mes = "0"+month;}
        else {mes = ""+month;}
        int year = calendarNow.get(Calendar.YEAR);
        fecha = dia+"-"+mes+"-"+year;
        String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        viewFecha.setText(fecha+"\n"+dias[calendarNow.get(Calendar.DAY_OF_WEEK)-1]);
    }

    public void aumentarDia(View v){
        calendarNow.add(Calendar.DAY_OF_YEAR, 1);
        actualizarFecha();
        animar(true);
        llenarListView();
    }

    public void disminuirDia(View v){
        calendarNow.add(Calendar.DAY_OF_YEAR, -1);
        actualizarFecha();
        animar(true);
        llenarListView();
    }

    private void animar(boolean mostrar)
    {
        View layout = findViewById(R.id.content2layout);
        ObjectAnimator animatorOut = ObjectAnimator.ofFloat(layout, View.ALPHA,1.0f, 0.6f);
        ObjectAnimator animatorIn = ObjectAnimator.ofFloat(layout, View.ALPHA,0.6f, 1.0f);
        animatorOut.setDuration(100);
        animatorIn.setDuration(100);
        animatorOut.start();
        animatorIn.start();

    }


    public void agregar(View v){
        Intent intent = new Intent(this, activity_agregar.class);
        intent.putExtra("fecha", fecha);
        intent.putExtra("hipo", hipo);
        intent.putExtra("hiper", hiper);
        intent.putExtra("hipoSevera", hipoSevera);
        intent.putExtra("hiperSevera", hiperSevera);

        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Lectura l = (Lectura) parent.getAdapter().getItem(position);

        String  _id= ""+l.getId();
        String fecha= l.getFecha();
        String hora= l.getHora();
        String ingesta= l.getIngesta();
        String glucosa_previa= l.getGlucosaPrevia();
        String glucosa_posterior= l.getGlucosaPosterior();
        String insulina= l.getInsulina();
        String hidratos= ""+l.getHidratos();

/*
        Context context = getApplicationContext();
        CharSequence text = "Hello toast! "+_id+ " "+ingesta+" "+glucosa_previa;
        int duration = Toast.LENGTH_SHORT;

        if(DEBUG){
        toast = Toast.makeText(context, text, duration);
        toast.show();
        }
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
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

}
