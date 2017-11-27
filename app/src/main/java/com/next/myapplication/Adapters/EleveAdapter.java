package com.next.myapplication.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.next.myapplication.Activities.MainActivity;
import com.next.myapplication.Activities.inApplication;
import com.next.myapplication.Beans.Eleve;
import com.next.myapplication.Fragments.EleveFragment;

import com.next.myapplication.R;
import com.next.myapplication.Widgets.AutoResizeTextView;

import java.util.List;

/**
 * Created by lenovo on 27/11/2017.
 */

public class EleveAdapter extends BaseAdapter {
    private MainActivity mainActivity;
    private boolean searchEnabled;

    private List<Eleve> eleves;


    public EleveAdapter(MainActivity mainActivity, boolean searchEnabled, List<Eleve> eleves) {
        this.mainActivity = mainActivity;
        this.searchEnabled = searchEnabled;
        this.eleves = eleves;
    }

    @Override
    public int getCount() {
        if (searchEnabled)
            return	eleves.size();
        else
            return	eleves.size();
    }

    @Override
    public Object getItem(int i) {
        return eleves.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((MainActivity) mainActivity).getLayoutInflater();
            row = inflater.inflate(R.layout.eleve_item, viewGroup, false);
            holder = new ViewHolder();
            holder.eleve_name = (AutoResizeTextView) row.findViewById(R.id.eleve_name);
            holder.eleve_adresse = (AutoResizeTextView) row.findViewById(R.id.eleve_address);
            holder.eleve_image = (ImageView) row.findViewById(R.id.eleve_image);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }


        final Eleve eleve = eleves.get(i);

        if(eleve != null ) {

            holder.eleve_name.setText("" + eleve.getNom());

            holder.eleve_adresse.setText("" + eleve.getVille());

            Glide.with(mainActivity).load(eleve.getPhoto()).into(holder.eleve_image);


            row.findViewById(R.id.open_eleve).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();

                    b.putInt("id_eleve", eleve.getId_eleve());
                    EleveFragment eleveFragment = new EleveFragment();
                    eleveFragment.setArguments(b);

                    ((inApplication) mainActivity.getApplication()).setUpFragment(mainActivity, eleveFragment, R.id.fragment_container);

                }
            });
        }
        return row;
    }

    static class ViewHolder {

        AutoResizeTextView eleve_name;
        AutoResizeTextView eleve_adresse;
        ImageView eleve_image;
    }




}