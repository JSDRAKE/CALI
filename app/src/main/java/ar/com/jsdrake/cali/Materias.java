package ar.com.jsdrake.cali;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.TextView;

public class Materias extends AppCompatActivity {

    String version_actual="1.0.0";
    String version_firebase;
    String url_firebase;

    TextView txtVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        txtVersion=(TextView) findViewById(R.id.txt_version);

        txtVersion.setText("Version "+version_actual);
        Obtener_Firebase();


    }

    /*///////// Update /////////*/

    private  void Obtener_Firebase(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference referencia_version,referencia_url;



        referencia_url=database.getReference("url");
        referencia_url.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                url_firebase=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Materias.this,"URL "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        referencia_version=database.getReference("version");
        referencia_version.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                version_firebase=dataSnapshot.getValue().toString();

                if(version_firebase.trim().equals(version_actual.trim())){
                    Toast.makeText(Materias.this,"No es Necesario Actualizar ", Toast.LENGTH_SHORT).show();

                }
                else{

                    AlertDialog.Builder ventana = new AlertDialog.Builder(Materias.this);
                    ventana.setMessage("Existe una nueva version de la Aplicación");
                    ventana.setTitle("CALI");
                    ventana.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent Actualizar = new Intent(Materias.this, PantallaActualizar.class);
                            Actualizar.putExtra("version",version_firebase);
                            //Actualizar.putExtra("url",url_firebase);
                            finish();
                            startActivity(Actualizar);
                        }
                    });

                    ventana.setNegativeButton("Mas tarde", new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ///No hace nada y se queda en la misma ventana
                                }
                            });

                    ventana.show();


/*
                    Intent pantaActualizar=new Intent(getApplicationContext(),PantallaActualizar.class);
                    pantaActualizar.putExtra("version",version_firebase);
                    pantaActualizar.putExtra("url",url_firebase);
                    finish();
                    startActivity(pantaActualizar);*/
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Materias.this,"Version "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });







    }



    /*////////// Ingreso a Materias //////////*/


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

    public void Informacion(View view){
        Intent Informacion = new Intent(this, Informacion.class);
        startActivity(Informacion);

    }

    public void Actualizar(View view){
        Intent Actualizar = new Intent(this, PantallaActualizar.class);
        startActivity(Actualizar);

    }


    /*//////////// Menu y Opciones /////////*/

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

        if (id == R.id.update){
            Actualizar(null);
            return true;
        }


     return super.onOptionsItemSelected(opcion_menu);

    }
}


