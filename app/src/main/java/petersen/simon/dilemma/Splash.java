package petersen.simon.dilemma;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import diverse.App;

/**
 * Created by Blumen on 11-01-2016.
 */
public class Splash extends AppCompatActivity implements Runnable{
    private TextView loader;
    private ProgressBar progressSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        App.splash = this;
        progressSpinner = (ProgressBar) findViewById(R.id.progressSpinner);
        loader = (TextView) findViewById(R.id.LoadText);
        loader.setText("Henter dilemmaer");
        progressSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void run() {
        startActivity(new Intent(Splash.this, HovedAktivitet.class));
        finish();
    }
}
