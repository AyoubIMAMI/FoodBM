package etu.polytech.foodbm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import etu.polytech.foodbm.model.ButtonModel;
import etu.polytech.foodbm.model.ButtonsModel;
import etu.polytech.foodbm.model.NotificationComparable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavFragment extends Fragment {
    // The fragment initialization parameters, e.g. ARG_INT_ARRAY
    private static final String ARG_INT = "argInt";

    // Parameters
    private int buttonId = 0;
    private List<TextView> buttons;
    ConstraintLayout layout;

    public NavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param buttonId The list of buttons to display on the nav bar.
     * @return A new instance of fragment NavFragment.
     */
    public static NavFragment newInstance(int buttonId) {
        NavFragment fragment = new NavFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INT, buttonId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (getArguments() != null) {
            buttonId = getArguments().getInt(ARG_INT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_nav, container, false);

        List<ButtonModel> buttonModels = new ButtonsModel().constructButtonModelList();
        this.buttons = new ArrayList<>();
        //swipeControle(v, getActivity());


        for (ButtonModel buttonModel : buttonModels) {
            TextView button = v.findViewById(buttonModel.id);
            this.buttons.add(button);

            if (buttonModel.id == buttonId)
                button.setTextColor(Color.parseColor("#FFFFFF"));

            button.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), buttonModel.redirection);
                startActivity(intent);
            });
        }
        // Inflate the layout for this fragment
        return v;
    }
}