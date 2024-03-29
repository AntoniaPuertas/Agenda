package com.example.agenda.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.actividades.datos.Nota;

import java.util.ArrayList;

public class ListadoNotasAdapter extends ArrayAdapter<Nota> {

    private ArrayList<Nota> listaNotas;
    private Context context;

    private String[] categorias;
    private String categoria;

    public ListadoNotasAdapter(Context context,
                               ArrayList<Nota> listaNotaArrayList) {

        super(context, R.layout.activity_main, listaNotaArrayList);

        this.context = context;
        this.listaNotas = listaNotaArrayList;
        this.categorias = Nota.getCategorias();
    }

    /**
     * Método que se ejecuta una vez por cada elemento de la lista
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position,
                        View convertView,
                         ViewGroup parent) {

        //variable nota guardamos los datos de la nota que se muestra en este item
        Nota nota = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nota,
                    parent,
                    false);
        }



        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgCategoria);
        TextView txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);
        TextView txtTexto = (TextView) convertView.findViewById(R.id.txtTexto);

        txtFecha.setText(nota.getFechaToString());
        txtTexto.setText(nota.getTexto());

        categoria = nota.getCategoria();

        if(categoria.equalsIgnoreCase(categorias[0])){
            imageView.setImageResource(R.drawable.ic_nota_urgente);
        }else if(categoria.equalsIgnoreCase(categorias[1])){
            imageView.setImageResource(R.drawable.ic_nota_amarillo);
        }else{
            imageView.setImageResource(R.drawable.ic_nota_verde);
        }

        return convertView;

    }
}
