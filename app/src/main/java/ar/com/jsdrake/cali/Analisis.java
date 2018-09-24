package ar.com.jsdrake.cali;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Analisis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);
    }
    public void Regresar(View view) {
        Intent Regresar = new Intent(this, Materias.class);
        startActivity(Regresar);
    }
    public void Informacion(View view){
        Intent Informacion = new Intent(this, Informacion.class);
        startActivity(Informacion);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //@Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu) {
        int id = opcion_menu.getItemId();

        if (id == R.id.info) {
            Informacion(null);
            return true;
        }
        return super.onOptionsItemSelected(opcion_menu);

    }
}
