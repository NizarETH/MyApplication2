package com.next.myapplication.Activities;

import android.app.Application;
import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.next.myapplication.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by nizar on 07/12/16.
 */
public class inApplication extends Application {



    @Override
    public void onCreate() {

        super.onCreate();


        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);

    }




    public static inApplication getApplication(Context context) {
        return (inApplication) context.getApplicationContext();
    }


    public void setUpFragment(FragmentActivity fragmentActivity, Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();
        // Replace fragmentCotainer with your container id
        Fragment currentFragment = fragmentActivity.getSupportFragmentManager().findFragmentById(layout_id);
// Return if the class are the same
        if (currentFragment != null && currentFragment.isVisible()) {
            if (currentFragment.getClass().equals(fm.getClass())) {
                return;
            } else {

                fragmentActivity.getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                        .replace(layout_id, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commitAllowingStateLoss();
            }
        }
        else
            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                    .replace(layout_id, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commitAllowingStateLoss();
    }

   }