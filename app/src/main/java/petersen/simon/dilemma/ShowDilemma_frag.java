package petersen.simon.dilemma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Dilemma;

public class ShowDilemma_frag extends AppCompatActivity {

    static Dilemma newDilemma;
    private TextView title;
    private TextView  beskrivelse;
    private TextView seriøsitet;
    private TextView udløb;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.show_dilemma_frag, container, false);
        //Opsætning af views.

        title = (TextView) v.findViewById(R.id.showTitle);
        beskrivelse = (TextView) v.findViewById(R.id.showBeskrivelse);
        seriøsitet = (TextView) v.findViewById(R.id.showSeriøsitet);
        udløb = (TextView) v.findViewById(R.id.showUdløb);




        return v;
    }
}
