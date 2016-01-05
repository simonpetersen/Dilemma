package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import diverse.App;
import model.Dilemma;

/**
 * Created by Sandie on 04-01-2016.
 */
public class OpretDilemma3Svarmuligheder_frag extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    CheckBox comment;
    EditText answer1, answer2, answer3, answer4, answer5;
    ArrayList<EditText> answerOptionsFields;
    Button finish;
    TextView answerChoice;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_svarmuligheder, container, false);

        comment = (CheckBox) v.findViewById(R.id.checkedComments);
        comment.setOnCheckedChangeListener(this);

        answerOptionsFields = new ArrayList<>();
        answerOptionsFields.add((EditText) v.findViewById(R.id.editText1));
        answerOptionsFields.add((EditText) v.findViewById(R.id.editText2));
        answerOptionsFields.add((EditText) v.findViewById(R.id.editText3));
        answerOptionsFields.add((EditText) v.findViewById(R.id.editText4));
        answerOptionsFields.add((EditText) v.findViewById(R.id.editText5));

        for (int n=0; n<answerOptionsFields.size(); n++) {
            answerOptionsFields.get(n).setOnClickListener(this);
        }

        finish = (Button) v.findViewById(R.id.buttonFærdig);
        finish.setOnClickListener(this);

        answerChoice = (TextView) v.findViewById(R.id.svarmu);
        answerChoice.setText("Svarmuligheder");

        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){
        }
        else {
        }
    }

    @Override
    public void onClick(View v) {
        for (int i=0; i<answerOptionsFields.size(); i++) {
            System.out.println(answerOptionsFields.get(i).getText());
        }

        if(v == finish) {
            saveDilemma();
            getFragmentManager().beginTransaction()
                  .replace(R.id.fragmentindhold, new HovedMenu_frag())
                  .commit();
        }


    }

    private void saveDilemma() {

        /* Overflødig kode
        OpretDilemma1Titel_frag.oprettetDilemma.setAnswer1(answer1.getText().toString());
        OpretDilemma1Titel_frag.oprettetDilemma.setAnswer2(answer2.getText().toString());
        OpretDilemma1Titel_frag.oprettetDilemma.setAnswer3(answer3.getText().toString());
        OpretDilemma1Titel_frag.oprettetDilemma.setAnswer4(answer4.getText().toString());
        OpretDilemma1Titel_frag.oprettetDilemma.setAnswer5(answer5.getText().toString());
        */

        ArrayList<String> options = new ArrayList<String>();
        for (int n=0; n<answerOptionsFields.size(); n++) {
            if (answerOptionsFields.get(n).getText().equals("")) break;
            options.add(answerOptionsFields.get(n).getText().toString());
        }

        App.oprettetDilemma.setSvarmuligheder(options);
        App.dilemmaListe.addDilemma(App.oprettetDilemma);
        App.oprettetDilemma = new Dilemma(); //Nulstil Dilemma-objekt.
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}