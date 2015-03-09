package com.csis290.learnfragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView tvGreen;
    private TextView tvBlue;
    private TextView tvRed;

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            Bundle bundle = new Bundle();
            bundle.putString("key", "value");

            GreenFragment greenFragment = new GreenFragment();
            greenFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, greenFragment)
                    .addToBackStack("").commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGreen = (TextView) findViewById(R.id.tv_green);
        tvBlue = (TextView) findViewById(R.id.tv_blue);
        tvRed = (TextView) findViewById(R.id.tv_red);

        tvGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Bundle bundle = new Bundle();
                bundle.putString("key", "value");

                GreenFragment greenFragment = new GreenFragment();
                greenFragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, greenFragment)
                        .addToBackStack("").commit();
                */
                Handler handler = new Handler();
                handler.postDelayed(r, 1500);
            }
        });

        tvBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BlueTransactionTask().execute();
            }
        });

        tvRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RedTransactionTask().execute();
            }//end onClick
        });//end tvRed.setOnClickListener
    }//end method onCreate

    // AsyncTask<Params, Progress, Result>
    class BlueTransactionTask extends AsyncTask<Void, Void, Void> {

        private Handler handler;

        Runnable blueRunnable = new Runnable() {
            @Override
            public void run() {

                Bundle bundle = new Bundle();
                bundle.putString("message", "Looking a little blue");

                BlueFragment blueFragment = new BlueFragment();
                blueFragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, blueFragment)
                        .addToBackStack("").commit();
            }//end run
        };//end inner inner class Runnable

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.handler = new Handler();
            Log.d("tag", "onPreExecute()");
        }//end method onPreExecute

        @Override
        protected Void doInBackground(Void... params) {
            handler.postDelayed(blueRunnable, 2000);

            return null;
        }//end method doInBackground

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d("tag", "onPostExecute()");
        }//end method onPostExecute
    }//end inner class BlueTransactionTask

    class RedTransactionTask extends AsyncTask<Void, Void, Void> {
        private Handler handler;

        private Runnable redRunnable = new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new RedFragment())
                        .addToBackStack("").commit();
            }//end run
        };//end Runnable

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            this.handler = new Handler();
        }//end method onPreExecute

        @Override
        protected Void doInBackground(Void... params) {
            handler.postDelayed(redRunnable, 2500);

            return null;
        }
    }//end inner class RedTransactionTask
}//end class MainActivity
