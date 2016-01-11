package petersen.simon.dilemma;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Blumen on 11-01-2016.
 */
public class Splash extends AppCompatActivity{
    private final int Splash_længde = 1000;
    private TextView loader;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        loader = (TextView) findViewById(R.id.LoadText);
        loader.setText("Her skal stå loade processen!");

        //opretter Handler
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Starter hovedmenuen her
                startActivity(new Intent(Splash.this, HovedAktivitet.class));
                finish();
            }
        },Splash_længde);
    }
}
