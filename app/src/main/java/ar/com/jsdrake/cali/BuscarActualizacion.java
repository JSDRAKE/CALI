package ar.com.jsdrake.cali;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuscarActualizacion extends AppCompatActivity {


    /*////////// Declaración de version de APP //////////*/

    String version_actual="1.1.0";
    String version_firebase;
    String url_firebase;

    TextView txtVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_actualizacion);
        txtVersion=(TextView) findViewById(R.id.txt_version);

        txtVersion.setText("Version "+version_actual);
        Obtener_Firebase();

    }

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
                Toast.makeText(BuscarActualizacion.this,"URL "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        referencia_version=database.getReference("version");
        referencia_version.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                version_firebase=dataSnapshot.getValue().toString();

                if(version_firebase.trim().equals(version_actual.trim())){
                    Toast.makeText(BuscarActualizacion.this,"No es Necesario Actualizar ", Toast.LENGTH_SHORT).show();
                    Intent noActualizar = new Intent(BuscarActualizacion.this, Materias.class);
                    startActivity(noActualizar);
                }
                else{

                    Intent pantaActualizar=new Intent(getApplicationContext(),PantallaActualizar.class);
                    pantaActualizar.putExtra("version",version_firebase);
                    pantaActualizar.putExtra("url",url_firebase);
                    finish();
                    startActivity(pantaActualizar);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuscarActualizacion.this,"Version "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
