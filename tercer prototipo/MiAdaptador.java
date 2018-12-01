package efervescencia.es.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        tvGlucosaPrevia.setText((CharSequence) lecturas.get(position).getGlucosaPrevia());
        TextView tvGlucosaPosterior = (TextView) view.findViewById(R.id.listadoGlucosaPosterior);
        tvGlucosaPosterior.setText((CharSequence) lecturas.get(position).getGlucosaPosterior());
        return view;
    }
}
