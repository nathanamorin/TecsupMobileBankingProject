package com.example.alumno.mobilebanking;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class AccSummary extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_acc_summary, container, false);


        String user = "";
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null){
            user = extras.getString("username");
        }
        TextView caNum = (TextView) v.findViewById(R.id.lblCaNum);
        caNum.setText(user);


        Button btnRegistrar = (Button) v.findViewById(R.id.btnD1);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(this, CheckingDetail.class));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new CheckingDetails()).commit();

            }
        });

        return v;
    }
}