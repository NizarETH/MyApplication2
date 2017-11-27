package com.next.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.next.myapplication.Beans.Prof;
import com.next.myapplication.R;

import io.realm.Realm;

/**
 * Created by lenovo on 27/11/2017.
 */

public class PdfViewerFragment extends Fragment {

    WebView webview;
    ProgressBar progressbar;
    String pdf = "";
    private int id_prof;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pdf_viewer, container, false);
        webview = (WebView) v.findViewById(R.id.webview);
        progressbar = (ProgressBar) v.findViewById(R.id.progressbar);
        realm = Realm.getDefaultInstance();

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setAllowFileAccess(true);
        Bundle b = getArguments();
        if (b != null) {
            id_prof = b.getInt("id_prof");
        }
        if (id_prof > 0) {
            final Prof prof = realm.where(Prof.class).equalTo("id_prof", id_prof).findFirst();
            if (prof != null) {
                pdf = prof.getPdf();
                String doc = "http://docs.google.com/gview?embedded=true&url=" + pdf;
                if (!pdf.isEmpty())
                    webview.loadUrl(doc);

                webview.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        // do your stuff here
                        progressbar.setVisibility(View.GONE);
                    }
                });

            }
        }
        return v;
    }
}
