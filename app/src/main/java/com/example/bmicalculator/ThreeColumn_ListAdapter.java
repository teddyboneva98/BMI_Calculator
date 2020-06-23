package com.example.bmicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<Calculations> {

    private LayoutInflater mInflater;
    private ArrayList<Calculations> calculations;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Calculations> calculations) {
        super(context, textViewResourceId, calculations);
        this.calculations = calculations;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;

    }
    public View getView (int position, View convertView, ViewGroup parents) {
        convertView = mInflater.inflate(mViewResourceId,null);
        //Взима изчисленията за тази позиция
        Calculations bmicalculations = calculations.get(position);

        if(calculations != null) {
            TextView weight = (TextView) convertView.findViewById(R.id.weightValue);
            TextView height = (TextView) convertView.findViewById(R.id.heightValue);
            TextView calculate  = (TextView) convertView.findViewById(R.id.calculatedResult);

            weight.setText(bmicalculations.getWeight());
            height.setText(bmicalculations.getHeight());
            calculate.setText(bmicalculations.getCalculatedResult());
        }
        return convertView; //Връща генерирания изглед
    }
}
