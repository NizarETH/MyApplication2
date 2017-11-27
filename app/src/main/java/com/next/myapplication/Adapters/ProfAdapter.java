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
import com.next.myapplication.Beans.Prof;
import com.next.myapplication.Fragments.PdfViewerFragment;
import com.next.myapplication.Fragments.ProfFragment;
import com.next.myapplication.R;

import com.next.myapplication.Widgets.AutoResizeTextView;

import java.util.List;

/**
 * Created by lenovo on 27/11/2017.
 */

public class ProfAdapter extends BaseAdapter {
    private MainActivity mainActivity;
    private boolean searchEnabled;

    private List<Prof> profs;


    public ProfAdapter(MainActivity mainActivity, boolean searchEnabled, List<Prof> profs) {
        this.mainActivity = mainActivity;
        this.searchEnabled = searchEnabled;
        this.profs = profs;
    }

    @Override
    public int getCount() {
        if (searchEnabled)
            return	profs.size();
        else
            return	profs.size();
    }

    @Override
    public Object getItem(int i) {
        return profs.get(i);
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
            row = inflater.inflate(R.layout.prof_item, viewGroup, false);
            holder = new ViewHolder();
            holder.prof_name = (AutoResizeTextView) row.findViewById(R.id.prof_name);
            holder.prof_adresse = (AutoResizeTextView) row.findViewById(R.id.prof_address);
            holder.prof_image = (ImageView) row.findViewById(R.id.prof_image);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        final Prof prof = profs.get(i);
        if(prof != null ) {

            holder.prof_name.setText("" + prof.getNom());

            holder.prof_adresse.setText("" + prof.getAdresse());

            Glide.with(mainActivity).load(prof.getPhoto()).into(holder.prof_image);


            row.findViewById(R.id.open_prof).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();

                    b.putInt("id_prof", prof.getId_prof());
                    PdfViewerFragment pdfViewerFragment = new PdfViewerFragment();
                    pdfViewerFragment.setArguments(b);

                    ((inApplication) mainActivity.getApplication()).setUpFragment(mainActivity, pdfViewerFragment, R.id.fragment_container);

                }
            });
        }

        return row;
    }

    static class ViewHolder {

        AutoResizeTextView prof_name;
        AutoResizeTextView prof_adresse;
        ImageView prof_image;
    }




}