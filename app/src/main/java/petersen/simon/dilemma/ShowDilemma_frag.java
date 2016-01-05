package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import model.Dilemma;

public class ShowDilemma_frag extends Fragment implements View.OnClickListener {

    private Dilemma dilemma;
    private TextView title;
    private TextView  beskrivelse;
    private TextView seriøsitet;
    private TextView udløb;
    private Button besvar;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.show_dilemma_frag, container, false);
        //Opsætning af views.

        dilemma = MainMenu_frag.dilemmaList.getSelectedDilemma();

        title = (TextView) v.findViewById(R.id.showTitle);
        beskrivelse = (TextView) v.findViewById(R.id.showDescription);
        seriøsitet = (TextView) v.findViewById(R.id.showSeriøsitet);
        udløb = (TextView) v.findViewById(R.id.showUdløb);
        besvar = (Button) v.findViewById(R.id.buttonAnswer);


        title.setText(dilemma.getTitle());
        beskrivelse.setText(dilemma.getDescription());
        seriøsitet.setText(String.valueOf(dilemma.getSerious()));
        udløb.setText(String.valueOf(dilemma.getTime()) + " Minutter tilbage");

        besvar.setOnClickListener(this);



        return v;

    }

    public void onClick(View v) {
        Fragment fragment = null;
        if (v == besvar) {
            fragment = new AnswerDilemma_frag();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold,fragment)
                    .addToBackStack(null)
                    .commit();

        }
    }
}
