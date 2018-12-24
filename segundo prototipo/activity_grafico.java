package efervescencia.es.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


public class activity_grafico extends AppCompatActivity {

    LineChart graficoLinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        graficoLinea = (LineChart) findViewById(R.id.grafico_linea);
        LineDataSet lineDataSet = new LineDataSet(dataValues(),"Lecturas de glucosa");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData  data = new LineData(dataSets);
        graficoLinea.setData(data);
        graficoLinea.invalidate();
    }

    private ArrayList<Entry> dataValues() {

        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(8, 94));
        dataVals.add(new Entry(10, 165));
        dataVals.add(new Entry(13, 212));
        dataVals.add(new Entry(15, 123));
        dataVals.add(new Entry(17, 134));
        return dataVals;
    }


    public void volver(View v){
        finish();
    }


}


