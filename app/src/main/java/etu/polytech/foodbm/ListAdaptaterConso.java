package etu.polytech.foodbm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import etu.polytech.foodbm.model.Conso;


public class ListAdaptaterConso extends ArrayAdapter<Conso> {
    public ListAdaptaterConso(Context context, ArrayList<Conso> consoArrayList) {
        super(context, R.layout.conso_layout, consoArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Conso conso = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.conso_layout, parent, false);
        }

        TextView consoName = convertView.findViewById(R.id.consoName);
        TextView consoValue = convertView.findViewById(R.id.consoValue);
        consoName.setText(conso.getName());
        consoValue.setText(String.valueOf(conso.getValue()));


        return convertView;
    }
}
