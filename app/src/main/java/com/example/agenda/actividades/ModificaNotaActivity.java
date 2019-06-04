package com.example.agenda.actividades;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.actividades.datos.Nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.agenda.actividades.datos.ListaDatos.listaNotas;

public class ModificaNotaActivity extends AppCompatActivity {

    TextView txtNuevaNota;
    EditText etFecha;
    Spinner spCategoria;
    EditText etTexto;
    Button btnCrear;
    Button btnCancelar;

    String myFormat = "dd MMM yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
    Calendar fechaAux;

    Nota notaAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        txtNuevaNota = findViewById(R.id.txtNuevaNota);
        etFecha = findViewById(R.id.etFecha);
        spCategoria = findViewById(R.id.spCategoria);
        etTexto = findViewById(R.id.etTextoNuevaNota);
        btnCrear = findViewById(R.id.btnCrear);
        btnCancelar = findViewById(R.id.btnCancelar);

        Bundle extras = getIntent().getExtras();
        int indiceNota = extras.getInt("nota", 0);

        notaAux = listaNotas.get(indiceNota);

        txtNuevaNota.setText("Modifica nota");
        fechaAux = notaAux.getFecha();
        etFecha.setText(sdf.format(fechaAux.getTime()));

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerFecha(Calendar.getInstance());
            }
        });

        //rellenar el spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Nota.getCategorias());
        spCategoria.setAdapter(dataAdapter);

        spCategoria.setSelection(notaAux.getIndexCategoria());

        etTexto.setText(notaAux.getTexto());

        btnCrear.setText("Modificar");

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = spCategoria.getSelectedItem().toString();
                String texto = etTexto.getText().toString();
                notaAux.setCategoria(categoria);
                notaAux.setTexto(texto);
                notaAux.setFecha(fechaAux);
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void leerFecha(Calendar fecha){
        int year = fecha.get(Calendar.YEAR);
        int month = fecha.get(Calendar.MONTH);
        int day = fecha.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ModificaNotaActivity.this,
                datePickerListener,
                year,
                month,
                day
        );
        datePickerDialog.setTitle("Selecciona la fecha");
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fechaAux.set(Calendar.YEAR, year);
                    fechaAux.set(Calendar.MONTH, month);
                    fechaAux.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    etFecha.setText(sdf.format(fechaAux.getTime()));
                }
            };
}
