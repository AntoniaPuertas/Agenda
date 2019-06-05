package com.example.agenda.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.agenda.R;

public class InformacionActivity extends AppCompatActivity {

    TextView txtMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        txtMensaje = findViewById(R.id.txtMensaje);

        Bundle extras = getIntent().getExtras();
        int idTexto = extras.getInt("texto",0);
        txtMensaje.setText(getText(idTexto));
    }
}
