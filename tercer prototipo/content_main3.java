package efervescencia.es.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class content_main3 extends AppCompatActivity {

    RecyclerView recyclerLecturas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main3);

        recyclerLecturas = (RecyclerView) findViewById(R.id.recicler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerLecturas.setLayoutManager(linearLayoutManager);
    }
}
