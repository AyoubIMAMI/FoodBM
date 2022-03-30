package etu.polytech.foodbm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NavBarFragment extends Fragment {
    private Button homeButton;
    private Button voucherButton;
    private Button planButton;
    private Button settingButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_bar, container, false);
        homeButton = view.findViewById(R.id.homeButton);
        voucherButton = view.findViewById(R.id.voucherButton);
        planButton = view.findViewById(R.id.planButton);
        settingButton = view.findViewById(R.id.settingButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}