package petersen.simon.dilemma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import diverse.App;
import model.Dilemma;

/**
 * Created by Joakim on 05-01-2016.
 */



public class BesvarDilemma_frag extends Fragment implements View.OnClickListener {

    private Dilemma dilemma;
    private TextView title, kommentarTextView;
    private EditText kommentar;
    private Button besvarKnap;
    ArrayList<Button> svarKnapper;
    ArrayList<String> svarmuligheder;
    int valgt = -1;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        View v = i.inflate(R.layout.besvar_dilemma_frag, container, false);

        HovedAktivitet.sætTilbagePil();
        dilemma = App.dilemmaListe.getValgtDilemma();
        title = (TextView) v.findViewById(R.id.showTitle_answer);
        kommentar = (EditText) v.findViewById(R.id.editTextBesvarlse);
        kommentarTextView = (TextView) v.findViewById(R.id.textView4);
        besvarKnap = (Button) v.findViewById(R.id.buttonSend);
        svarKnapper = new ArrayList<>();
        svarKnapper.add((Button) v.findViewById(R.id.b1));
        svarKnapper.add((Button) v.findViewById(R.id.b2));
        svarKnapper.add((Button) v.findViewById(R.id.b3));
        svarKnapper.add((Button) v.findViewById(R.id.b4));
        svarKnapper.add((Button) v.findViewById(R.id.b5));

        svarmuligheder = dilemma.getSvarmuligheder();

        besvarKnap.setOnClickListener(this);

        for (int n=0; n< svarKnapper.size(); n++) svarKnapper.get(n).setOnClickListener(this);

        title.setText(dilemma.getTitel());
        kommentar.setOnClickListener(this);


        if (!dilemma.getKommentarTilladt()) {
            kommentar.setVisibility(View.INVISIBLE);
            kommentarTextView.setVisibility(View.INVISIBLE);
        }

        for(int t=0; t< svarKnapper.size(); t++) {
            if(t >= svarmuligheder.size()) {
                svarKnapper.get(t).setVisibility(View.INVISIBLE);

            }
            else {
                svarKnapper.get(t).setText(svarmuligheder.get(t));
            }

        }
        return v;
    }

    public void onClick(View v) {

        System.out.println(App.besvarelser);

        if (svarKnapper.contains(v)) {
            valgt = svarKnapper.indexOf(v)+1;
            System.out.println("Valgt = "+valgt);
        }

        else if (v == besvarKnap) {

            if(valgt == -1) {
                Toast.makeText(getActivity(), "Du skal trykke på en valgmulighed", Toast.LENGTH_SHORT).show();
            }
            else {
                App.tilføjBesvarelse(dilemma.getDilemmaID(), valgt, kommentar.getText().toString());
                getFragmentManager().popBackStack();
            }

        }
    }
}