package efervescencia.es.myapplication;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;


public class MiAdaptador extends BaseAdapter {

    private final Activity actividad;
    private final Vector<Lectura> lecturas;

    public MiAdaptador(Activity pActividad, Vector<Lectura> pLecturas){
        super();
        actividad = pActividad;
        lecturas = pLecturas;
    }


    @Override
    public int getCount() {
        return lecturas.size();
    }

    @Override
    public Object getItem(int position) {
        return lecturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.list, null, true);
        TextView tvHora = (TextView) view.findViewById(R.id.listadoHora);
        tvHora.setText((CharSequence) lecturas.get(position).getHora());
        TextView tvIngesta = (TextView) view.findViewById(R.id.listadoIngesta);
        tvIngesta.setText((CharSequence) lecturas.get(position).getIngesta());
        TextView tvGlucosaPrevia = (TextView) view.findViewById(R.id.listadoGlucosaPrevia);

        //DETECTAMOS VALORES PELIGROSOS Y ACTIVAMOS AVISO
        int gp = 0;
        if(lecturas.get(position).getGlucosaPrevia().length()>0){
            gp = Integer.parseInt(lecturas.get(position).getGlucosaPrevia());
            if(gp>260 || gp<80){

                Drawable image = actividad.getResources().getDrawable(R.drawable.ic_warning_black_24dp );
                int h = image.getIntrinsicHeight();
                int w = image.getIntrinsicWidth();
                image.setBounds( 0, 0, w, h );
                tvGlucosaPrevia.setCompoundDrawables( image, null, null, null );

            }
        }

        tvGlucosaPrevia.setText((CharSequence) lecturas.get(position).getGlucosaPrevia());

        TextView tvGlucosaPosterior = (TextView) view.findViewById(R.id.listadoGlucosaPosterior);

        //DETECTAMOS VALORES PELIGROSOS Y ACTIVAMOS AVISO
        int gpost = 0;
        if(lecturas.get(position).getGlucosaPosterior().length()>0){
            gpost = Integer.parseInt(lecturas.get(position).getGlucosaPosterior());
            if(gpost>260 || gpost<80){

                Drawable image = actividad.getResources().getDrawable( R.drawable.ic_warning_black_24dp );
                int h = image.getIntrinsicHeight();
                int w = image.getIntrinsicWidth();
                image.setBounds( 0, 0, w, h );
                tvGlucosaPosterior.setCompoundDrawables( image, null, null, null );
            }
        }

        tvGlucosaPosterior.setText((CharSequence) lecturas.get(position).getGlucosaPosterior());
        return view;
    }
}
