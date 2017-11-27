package com.next.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.next.myapplication.Activities.inApplication;
import com.next.myapplication.R;

/**
 * Created by lenovo on 27/11/2017.
 */

public class SearchFragment extends Fragment {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       final View v = inflater.inflate(R.layout.search, container, false);

        v.findViewById(R.id.search_agence).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                v.findViewById(R.id.items).setVisibility(View.VISIBLE);
                return false;
            }
        });

        v.findViewById(R.id.open_list_eleves).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((inApplication)getActivity().getApplication()).setUpFragment(getActivity(), new ListElevesFragment(), R.id.fragment_container);
            }
        });

        v.findViewById(R.id.open_list_profs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((inApplication)getActivity().getApplication()).setUpFragment(getActivity(), new ListProfFragments(), R.id.fragment_container);

            }
        });
        return v;
    }

}
