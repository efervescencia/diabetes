package efervescencia.es.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LecturasAdapter {


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView listadoHora, listadoIngesta,listadoGlucosaPrevia,listadoGlucosaPosterior;


        public MyViewHolder(View itemView) {
            super(itemView);

            listadoHora = (TextView) itemView.findViewById(R.id.listadoHora);
            listadoIngesta = (TextView) itemView.findViewById(R.id.listadoIngesta);
            listadoGlucosaPrevia = (TextView) itemView.findViewById(R.id.listadoGlucosaPrevia);
            listadoGlucosaPosterior = (TextView) itemView.findViewById(R.id.listadoGlucosaPosterior);

        }
    }

}
