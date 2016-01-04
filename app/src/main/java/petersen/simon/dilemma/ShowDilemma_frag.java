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

    static Dilemma newDilemma;
    static MainMenu_frag newMainMenu_frag;
    static DilemmaList dilemmaList;
    private TextView title;
    private TextView  beskrivelse;
    private TextView seriøsitet;
    private TextView udløb;
    private int posi = newMainMenu_frag.getPosition();

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.show_dilemma_frag, container, false);
        //Opsætning af views.

        title = (TextView) v.findViewById(R.id.showTitle);
        beskrivelse = (TextView) v.findViewById(R.id.showBeskrivelse);
        seriøsitet = (TextView) v.findViewById(R.id.showSeriøsitet);
        udløb = (TextView) v.findViewById(R.id.showUdløb);

        title.setText(dilemmaList.getOverskrifter().get(posi));

        return v;

    }
}
