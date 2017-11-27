package com.next.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.next.myapplication.Activities.MainActivity;
import com.next.myapplication.Adapters.EleveAdapter;
import com.next.myapplication.Adapters.ProfAdapter;
import com.next.myapplication.Beans.Eleve;
import com.next.myapplication.Beans.Prof;
import com.next.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by lenovo on 27/11/2017.
 */

public class ListElevesFragment extends Fragment {

  private Realm realm;
  private CheckBox Nomcheckbox;
    private CheckBox Addresscheckbox;
    private CheckBox Villecheckbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_profs, container, false);

        final ListView listView = (ListView) v.findViewById(R.id.list_travels);
        ((TextView)v.findViewById(R.id.name_liste)).append(" élèves");

        Nomcheckbox = v.findViewById(R.id.name_checkbox);
        Addresscheckbox = v.findViewById(R.id.adresse_checkbox);
        Villecheckbox = v.findViewById(R.id.ville_checkbox);
        Villecheckbox.setVisibility(View.VISIBLE);

        final List<Eleve> eleves =  realm.where(Eleve.class).findAll();

        listView.setAdapter(  new EleveAdapter((MainActivity) getActivity(), false, eleves));


        try {

            ((EditText)v.findViewById(R.id.search_agence)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    /***** Insensitive : pour ignore case maj = min ************/


                    List<Eleve> filtredNames = new ArrayList<>();


                    for (int i = 0; i <eleves.size() ; i++) {

                        if(Nomcheckbox.isChecked()) {
                            if ((eleves.get(i).getNom().toLowerCase().contains(s.toString().toLowerCase()))) {

                                filtredNames.add(eleves.get(i));
                            }
                        }

                        if(Addresscheckbox.isChecked()) {
                            if ((eleves.get(i).getAdresse().toLowerCase().contains(s.toString().toLowerCase()))) {

                                filtredNames.add(eleves.get(i));
                            }
                        }
                        if(Villecheckbox.isChecked()) {
                            if ((eleves.get(i).getVille().toLowerCase().contains(s.toString().toLowerCase()))) {

                                filtredNames.add(eleves.get(i));
                            }
                        }
                    }


                    listView.setAdapter( new EleveAdapter((MainActivity) getActivity(),true, filtredNames));
                    if(s != null && s.toString().isEmpty())
                        listView.setAdapter(  new EleveAdapter((MainActivity) getActivity(), false, eleves));

                    ((BaseAdapter)listView.getAdapter()).notifyDataSetInvalidated();

                    ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }catch (Exception e)
        {
            Log.e("",""+e.getMessage());
        }
        return v;
    }

}
