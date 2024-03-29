package com.example.agenda.actividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.actividades.datos.Nota;

import java.util.ArrayList;

import static com.example.agenda.actividades.datos.ListaDatos.listaNotas;

public class MainActivity extends AppCompatActivity {

    ListView lstNotas;
    ListadoNotasAdapter listadoNotasAdapter;

    FloatingActionButton fbNuevaNota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstNotas = findViewById(R.id.lstNotas);
        fbNuevaNota = findViewById(R.id.fbNuevaNota);
        GestionFicheros.leerDatos(this);
        /*
        listaNotas.add(new Nota("Dar un paseo", "Importante"));
        listaNotas.add(new Nota("Cortarme el pelo", "Urgente"));
        listaNotas.add(new Nota("Comprar pan", "Normal"));
        listaNotas.add(new Nota("Ir a Cebanc", "Urgente"));
*/
        //creamos un adapter pasandole el contexto y el arraylist con los datos
        listadoNotasAdapter = new ListadoNotasAdapter(this,listaNotas);

        lstNotas.setAdapter(listadoNotasAdapter);

        fbNuevaNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        NuevaNotaActivity.class);
                startActivity(intent);
            }
        });

        lstNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createSimpleDialog(position).show();

                return true;
            }
        });

        lstNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ModificaNotaActivity.class);
                intent.putExtra("nota", position);
                startActivity(intent);
            }
        });
    }

    public AlertDialog createSimpleDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Atención")
                .setMessage("¿Seguro que quieres eliminar esta nota?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //eliminar nota
                        listaNotas.remove(position);
                        listadoNotasAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cancelar operación
                    }
                });
        return builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.it_acerca_de:
                //mostrar pantalla acerca de
                Intent intent = new Intent(MainActivity.this, InformacionActivity.class);
                intent.putExtra("texto", R.string.texto_acerca_de);
                startActivity(intent);

                return true;
            case R.id.it_privacidad:
                //mostrar pantalla con politica de privacidad
                Intent intent2 = new Intent(MainActivity.this, InformacionActivity.class);
                intent2.putExtra("texto", R.string.politica_privacidad);
                startActivity(intent2);

                return true;
            case R.id.it_sincroniza:
                //TODO:sincronizar datos
                Toast.makeText(this, "Has pulsado sincroniza", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        listadoNotasAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GestionFicheros.guardarDatos(this);
    }
}
