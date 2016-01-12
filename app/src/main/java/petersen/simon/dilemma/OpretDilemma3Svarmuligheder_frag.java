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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import diverse.App;
import model.Dilemma;

/**
 * Created by Sandie on 04-01-2016.
 */
public class OpretDilemma3Svarmuligheder_frag extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    CheckBox comment;
    ArrayList<EditText> answerOptionsFields;
    Button finish;
    TextView answerChoice;
    int valgt2 = -1;


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
            App.oprettetDilemma.setKommentarTilladt(true);
        }
        else {
        }
    }

    @Override
    public void onClick(View v) {
        for (int i=0; i<answerOptionsFields.size(); i++) {
            System.out.println(answerOptionsFields.get(i).getText());

            if(v == answerOptionsFields.get(i)) {
                System.out.println(answerOptionsFields.get(i).getText() + "På position: " + valgt2);
            }
        }

        if(v == finish) {

            for(int l=0; l<answerOptionsFields.size(); l++) {
                //int h = 0;
                if (answerOptionsFields.get(l).getText().length() != 0) {
                    valgt2++;
                }
            }
                if(valgt2 <= 0) {
                    Toast.makeText(getActivity(), "Du skal udfylder mindst to knapper.", Toast.LENGTH_SHORT).show();
                }
                //if(h >= 4) {
                  //  Toast.makeText(getActivity(), "Du skal skrive mindst to valgmuligheder.", Toast.LENGTH_SHORT).show();
                //}
                else {
                    saveDilemma();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentindhold, new HovedMenu_frag())
                            .commit();
                }


        }

    }

    private void saveDilemma() {

        ArrayList<String> options = new ArrayList<String>();
        for (int n=0; n<answerOptionsFields.size(); n++) {
            if (answerOptionsFields.get(n).getText().equals("")) break;
            options.add(answerOptionsFields.get(n).getText().toString());
        }

        App.oprettetDilemma.setSvarmuligheder(options);
        App.dilemmaListe.addDilemma(App.oprettetDilemma);
        App.oprettetDilemma.setDilemmaID(App.dilemmaListe.getDilemmaListe().size());
        App.oprettetDilemma.setSvartidspunkt(new Date().getTime()+App.oprettetDilemma.getSvartidspunkt()*60*1000);
        App.oprettetDilemma.setOpretterID(App.userID);
        App.myFirebaseRef.child(String.valueOf(App.oprettetDilemma.getDilemmaID())).setValue(App.oprettetDilemma);
        App.oprettetDilemma = new Dilemma(); //Nulstil Dilemma-objekt.
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}