package efervescencia.es.myapplication;

        import android.annotation.TargetApi;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.Build;
        import android.support.annotation.RequiresApi;

        import java.sql.Date;
        import java.sql.Time;


public class BdAyuda extends SQLiteOpenHelper {

    Context ctx;
    String sqlInit = "CREATE TABLE glucosas (id integer PRIMARY KEY AUTOINCREMENT, ingesta integer, glucosa_previa float, glucosa_post float, insulina float, hidratos integer, fecha Date, hora Time)";
    String sqlDrop = "DROP TABLE glucosas";

    public BdAyuda(Context context) {
        super(context, "datos_glucosa", null, 1);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlInit);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDrop);
        onCreate(db);
    }

    //METODOS PARA MANEJAR LA BASE DE DATOS

    //VARIABLES GENERALES
    BdAyuda ayuda;
    SQLiteDatabase db;

    public void abrir(){

        ayuda = new BdAyuda(ctx);
        db = ayuda.getWritableDatabase();
    }

    public void cerrar(){
        db.close();
    }


    public long addData(int pId, int pIngesta, float pGlucosaPrevia, float pGlucosaPost,
                        float pInsulina, int pHidratos, String pFecha, String pHora ){

        ContentValues valores = new ContentValues();
        valores.put("ingesta", pIngesta);
        valores.put("glucosa_previa", pGlucosaPrevia);
        valores.put("glucosa_post", pGlucosaPost);
        valores.put("insulina", pInsulina);
        valores.put("hidratos", pHidratos);
        valores.put("fecha", pFecha);
        valores.put("hora", pHora);

        long result;

        if (pId == 0) {
            result= db.insert("glucosas", null, valores);
        }
        else {
            result= db.update("glucosas", valores, "id="+pId, null);
        }

        cerrar();
        return result;
    }





    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String consultar(String pFecha){
        String[] ingesta = {" ","Desayuno","Almuerzo","Comida","Merienda","Cena"};
        String datos ="";
        String [] columnas = new String[]{"id", "ingesta","glucosa_previa","glucosa_post","insulina","hidratos", "fecha", "hora"};

        try (Cursor c = db.query("glucosas", columnas, "fecha=" + pFecha, null, null, null, null)) {

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                datos += ingesta[Integer.parseInt(c.getString(c.getColumnIndex("ingesta")))]+"\n";
                datos += "Hora:" + c.getString(c.getColumnIndex("hora"));
                datos += " Glucosa: " + c.getString(c.getColumnIndex("glucosa_previa"))+"\n";
                datos += "Insulina: " + c.getString(c.getColumnIndex("insulina"))+"\n";
                datos += "Hidratos: " + c.getString(c.getColumnIndex("hidratos"))+"\n";
                datos += "\n\n";
            }
        }
        return datos;
    }

}