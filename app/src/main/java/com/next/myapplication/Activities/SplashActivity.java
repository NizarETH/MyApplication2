package com.next.myapplication.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.next.myapplication.Beans.ApplicationBean;
import com.next.myapplication.Beans.Prof;
import com.next.myapplication.Fragments.SearchFragment;
import com.next.myapplication.R;

import org.json.JSONObject;

import java.util.List;

import io.realm.Realm;

/**
 * Created by lenovo on 27/11/2017.
 */

public class SplashActivity extends AppCompatActivity {



    private Realm realm;
    public ProgressDialog pd;
    private parsingData pj;
    public static RequestQueue mRequestQueue;
    private String url = "https://api.myjson.com/bins/zpdl7";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        realm = Realm.getDefaultInstance();
        pd = new ProgressDialog(this);
        mRequestQueue = Volley.newRequestQueue(this);



            pd.setProgress(0);
            pd.setMessage("Please wait ...");
            pd.setMax(100);

            JsonObjectRequest request = new JsonObjectRequest
                    (Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            pj = new parsingData(response);
                            pj.execute();
                        }
                    }, new com.android.volley.Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub

                        }
                    });

            request.setRetryPolicy(new DefaultRetryPolicy(
                    9000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mRequestQueue.add(request);
        }
    public class parsingData extends AsyncTask<Void,Integer,Void>
    {

        JSONObject jsonObject;

        private parsingData(JSONObject jsonObject)
        {
            this.jsonObject = jsonObject;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            realm.createOrUpdateObjectFromJson(ApplicationBean.class, jsonObject);
            realm.commitTransaction();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pd.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
         }
    }

}
