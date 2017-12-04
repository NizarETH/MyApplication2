package com.next.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.next.myapplication.Beans.Eleve;
import com.next.myapplication.R;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

/**
 * Created by lenovo on 27/11/2017.
 */

public class EleveFragment extends Fragment {

    private Realm realm;
    private  int id_eleve;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.eleve, container, false);
        realm = Realm.getDefaultInstance();
        Bundle b = getArguments();
        if( b!= null)
        {
            id_eleve = b.getInt("id_eleve");
        }
        if( id_eleve > 0)
        {
            final Eleve eleve = realm.where(Eleve.class).equalTo("id_eleve",id_eleve).findFirst();
            if( eleve != null)
            {
                ((TextView)v.findViewById(R.id.eleve_name)).setText(eleve.getNom());
                ((TextView)v.findViewById(R.id.eleve_address)).setText(eleve.getAdresse());
                ((TextView)v.findViewById(R.id.eleve_ville)).setText(eleve.getVille());
                Picasso.with(getActivity()).load(eleve.getPhoto()).into(((ImageView)v.findViewById(R.id.eleve_image)));


            }
        }
        return  v;
    }
}
