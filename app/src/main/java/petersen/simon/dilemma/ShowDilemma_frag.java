package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Dilemma;
import model.DilemmaList;

public class ShowDilemma_frag extends Fragment {

    private Dilemma dilemma;
    private TextView title;
    private TextView  beskrivelse;
    private TextView seriøsitet;
    private TextView udløb;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.show_dilemma_frag, container, false);
        //Opsætning af views.

        dilemma = MainMenu_frag.dilemmaList.getSelectedDilemma();

        title = (TextView) v.findViewById(R.id.showTitle);
        beskrivelse = (TextView) v.findViewById(R.id.showDescription);
        seriøsitet = (TextView) v.findViewById(R.id.showSeriøsitet);
        udløb = (TextView) v.findViewById(R.id.showUdløb);

        title.setText(dilemma.getTitle());
        beskrivelse.setText(dilemma.getDescription());
        seriøsitet.setText(String.valueOf(dilemma.getSerious()));
        udløb.setText(String.valueOf(dilemma.getTime()) + " Minutter tilbage");


        return v;

    }
}
