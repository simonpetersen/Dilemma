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
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import model.Dilemma;

/**
 * Created by Sandie on 04-01-2016.
 */
public class CreateChoiseOfAnswers_frag extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    CheckBox comment;
    EditText answer1, answer2, answer3, answer4, answer5;
    Button finish;
    TextView answerchoise;
    String an1, an2, an3, an4, an5;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.create_choise_of_answrs, container, false);

        comment = (CheckBox) v.findViewById(R.id.checkedComments);
        comment.setOnCheckedChangeListener(this);


        answer1 = (EditText) v.findViewById(R.id.editText1);
        answer1.setOnClickListener(this);
        answer2 = (EditText) v.findViewById(R.id.editText2);
        answer2.setOnClickListener(this);
        answer3 = (EditText) v.findViewById(R.id.editText3);
        answer3.setOnClickListener(this);
        answer4 = (EditText) v.findViewById(R.id.editText4);
        answer4.setOnClickListener(this);
        answer5 = (EditText) v.findViewById(R.id.editText5);
        answer5.setOnClickListener(this);

        finish = (Button) v.findViewById(R.id.buttonFærdig);
        finish.setOnClickListener(this);

        answerchoise = (TextView) v.findViewById(R.id.svarmu);
        answerchoise.setText("Svarmuligheder");

        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(v == isChecked){
        }
        else {
        }
    }

    @Override
    public void onClick(View v) {

        if(v == finish) {
            if(answer1.equals("") && answer2.equals("")) {
                Toast.makeText(getActivity(), "Vælg valgmulighed", Toast.LENGTH_SHORT).show();
            }
            else {
                saveDilemma();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new MainMenu_frag())
                        .commit();
            }
        }

    }

    private void saveDilemma() {

        CreateTitleDescImg_frag.newDilemma.setAnswer1(answer1.getText().toString());
        CreateTitleDescImg_frag.newDilemma.setAnswer2(answer2.getText().toString());
        CreateTitleDescImg_frag.newDilemma.setAnswer3(answer3.getText().toString());
        CreateTitleDescImg_frag.newDilemma.setAnswer4(answer4.getText().toString());
        CreateTitleDescImg_frag.newDilemma.setAnswer5(answer5.getText().toString());

        MainMenu_frag.dilemmaList.addDilemma(CreateTitleDescImg_frag.newDilemma);

        CreateTitleDescImg_frag.newDilemma = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}