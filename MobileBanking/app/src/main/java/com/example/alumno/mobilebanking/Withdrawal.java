package com.example.alumno.mobilebanking;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Withdrawal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Withdrawal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Withdrawal extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_withdrawal, container, false);


        return v;
    }
}