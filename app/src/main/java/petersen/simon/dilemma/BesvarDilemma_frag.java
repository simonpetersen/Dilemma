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

import javax.security.auth.SubjectDomainCombiner;

import diverse.App;
import model.Dilemma;

/**
 * Created by Joakim on 05-01-2016.
 */



public class BesvarDilemma_frag extends Fragment implements View.OnClickListener {

    private Dilemma dilemma;
    private TextView title, bekommentar;
    private EditText besvarelse;
    private Button bSend;
    ArrayList<Button> answerFields;
    ArrayList<String> sv;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        View v = i.inflate(R.layout.besvar_dilemma_frag, container, false);

        dilemma = App.dilemmaListe.getValgtDilemma();
        title = (TextView) v.findViewById(R.id.showTitle_answer);
        besvarelse = (EditText) v.findViewById(R.id.editTextBesvarlse);
        bekommentar = (TextView) v.findViewById(R.id.textView4);
        bSend = (Button) v.findViewById(R.id.buttonSend);
        answerFields = new ArrayList<>();
        answerFields.add((Button) v.findViewById(R.id.b1));
        answerFields.add((Button) v.findViewById(R.id.b2));
        answerFields.add((Button) v.findViewById(R.id.b3));
        answerFields.add((Button) v.findViewById(R.id.b4));
        answerFields.add((Button) v.findViewById(R.id.b5));

        for (int n=0; n<answerFields.size(); n++) {
            answerFields.get(n).setOnClickListener(this);

        }

        title.setText(dilemma.getTitel());
        besvarelse.setOnClickListener(this);


        if (!dilemma.getComment()) {
            besvarelse.setVisibility(View.INVISIBLE);
            bekommentar.setVisibility(View.INVISIBLE);
        }

        sv = dilemma.getSvarmuligheder();

        for(int t=0; t<answerFields.size(); t++) {
            if(t >= sv.size()) {
                answerFields.get(t).setVisibility(View.INVISIBLE);

            }
            answerFields.get(t).setText(sv.get(t));
        }
        return v;
    }

    public void onClick(View v) {
        if (v == bSend) {

            for (int i=0; i<answerFields.size(); i++) {
                System.out.println(answerFields.get(i).getText());
            }

                if(answerFields == null) {
                    Toast.makeText(getActivity(), "Du skal trykke på en valgmulighed", Toast.LENGTH_SHORT).show();
                }
                else {
                    //gemSvar();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentindhold, new VisDilemma_frag())
                            .addToBackStack(null)
                            .commit();
                }


        }
    }
}