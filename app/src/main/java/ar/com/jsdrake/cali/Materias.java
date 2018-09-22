package ar.com.jsdrake.cali;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Materias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
    }


    public void Regresar(View view) {
        Intent Regresar = new Intent(this, MainActivity.class);
        startActivity(Regresar);

    }

    public void Algoritmica(View view) {
        Intent Algoritmica = new Intent(this, Algoritmica.class);
        startActivity(Algoritmica);
    }


        public void Analisis (View view){
            Intent Analisis = new Intent(this, Analisis.class);
            startActivity(Analisis);

        }
    public void Logica (View view){
        Intent Logica = new Intent(this, Logica.class);
        startActivity(Logica);

    }

    public void Horarios(View view){
        Intent Horarios = new Intent(this, Horarios.class);
        startActivity(Horarios);
    }

}
