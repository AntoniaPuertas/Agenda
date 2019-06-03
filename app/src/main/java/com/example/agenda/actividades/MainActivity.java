package com.example.agenda.actividades;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

        listaNotas.add(new Nota("Dar un paseo", "Importante"));
        listaNotas.add(new Nota("Cortarme el pelo", "Urgente"));
        listaNotas.add(new Nota("Comprar pan", "Normal"));
        listaNotas.add(new Nota("Ir a Cebanc", "Urgente"));

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
                listaNotas.remove(position);
                listadoNotasAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listadoNotasAdapter.notifyDataSetChanged();
    }
}
