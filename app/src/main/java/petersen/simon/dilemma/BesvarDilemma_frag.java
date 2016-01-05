package petersen.simon.dilemma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import model.Dilemma;

/**
 * Created by Joakim on 05-01-2016.
 */



public class BesvarDilemma_frag extends Fragment implements View.OnClickListener {

    private Dilemma dilemma;
    private TextView title;
    private Button annuler;
    private EditText besvarelse;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        View v = i.inflate(R.layout.besvar_dilemma_frag, container, false);

        dilemma = HovedMenu_frag.dilemmaListe.getSelectedDilemma();
        title = (TextView) v.findViewById(R.id.showTitle_answer);
        annuler = (Button) v.findViewById(R.id.buttonTilbage);
        besvarelse = (EditText) v.findViewById(R.id.editTextBesvarlse);

        title.setText(dilemma.getTitel());
        annuler.setOnClickListener(this);
        besvarelse.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        if (v == annuler) {
            getActivity().getSupportFragmentManager().popBackStack();

        }else if (v == besvarelse){

        }
    }
}