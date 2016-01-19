package petersen.simon.dilemma;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import diverse.App;
import model.BesvarelseListe;
import model.Dilemma;
import model.Logik;

/**
 * Created by Sandie on 04-01-2016.
 */
public class OpretDilemma3Svarmuligheder_frag extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, Runnable {

    CheckBox comment;
    ArrayList<EditText> answerOptionsFields;
    Button finish;
    TextView answerChoice;
    int valgt2 = -1;
    ProgressDialog progressDialog;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_svarmuligheder, container, false);
        Logik.sætTilbagePil(false);

        getActivity().setTitle("Opret");

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

        //Oprettelse af dialogboks
        App.opretDilemmaRun = this;
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Opretter dilemma...");

        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){
            Logik.oprettetDilemma.setKommentarTilladt(true);
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
                    progressDialog.show();
                    Logik.oprettetDilemma.setDilemmaID(Logik.getNytDilemmaID());
                    if(App.imgUris.size() > 0)
                        uploadBilleder();
                    else
                        run();
                    //saveDilemma();
                }


        }

    }

    private void saveDilemma() {

        ArrayList<String> options = new ArrayList<String>();
        for (int n=0; n<answerOptionsFields.size(); n++) {
            if (answerOptionsFields.get(n).getText().toString().equals("")) break;
            options.add(answerOptionsFields.get(n).getText().toString());
        }

        Logik.oprettetDilemma.setSvarmuligheder(options);
        //App.oprettetDilemma.setDilemmaID(App.getNytDilemmaID());
        Logik.oprettetDilemma.setSvartidspunkt(new Date().getTime() + Logik.oprettetDilemma.getSvartidspunkt() * 60 * 1000);
        Logik.oprettetDilemma.setOpretterID(App.userID);
        //uploadBilleder();
        Logik.dilemmaListe.addDilemma(Logik.oprettetDilemma);
        BesvarelseListe besvarelse = new BesvarelseListe(Logik.oprettetDilemma); //Ny liste til besvarelse oprettes og gemmes i Firebase.
        Logik.besvarelser.add(besvarelse);
        App.besvarelseFirebaseRef.child(String.valueOf(besvarelse.getDilemmaID())).setValue(besvarelse);
        App.dilemmaFirebaseRef.child(String.valueOf(Logik.oprettetDilemma.getDilemmaID())).setValue(Logik.oprettetDilemma);
        Logik.oprettetDilemma = new Dilemma(); //Nulstil Dilemma-objekt.
    }

    private void uploadBilleder()
    {
        App.antalBillederTilUpload = App.imgUris.size();
        for (Uri uri : App.imgUris) {
            InputStream is = null;
            try {
                is = getActivity().getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //ID bliver dilemmaID og et tilfældigt nummer. Eks.: 5_4569
            String id = String.valueOf(Logik.oprettetDilemma.getDilemmaID())+"_"+String.valueOf((int) (Math.random()*10000));
            App.uploadBilled(is, id);
        }
    }

    @Override
    public void run(){
        saveDilemma();
        progressDialog.cancel();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new HovedMenu_frag())
                .commit();
    }
}