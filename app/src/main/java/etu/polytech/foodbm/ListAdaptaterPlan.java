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

import etu.polytech.foodbm.model.Plan;


public class ListAdaptaterPlan extends ArrayAdapter<Plan> {
    public ListAdaptaterPlan(Context context, ArrayList<Plan> planArrayList) {
        super(context, R.layout.conso_layout, planArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Plan plan = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.plan_layout, parent, false);
        }

        TextView planName = convertView.findViewById(R.id.PlanNameTextView);
        TextView planValue = convertView.findViewById(R.id.PlanValueTextView);
        TextView planDate= convertView.findViewById(R.id.PlanDateTextView);

        planName.setText(plan.getName());
        planValue.setText(String.valueOf(plan.getValue()));
        planDate.setText(String.valueOf(plan.getDate()));

        return convertView;
    }


}
