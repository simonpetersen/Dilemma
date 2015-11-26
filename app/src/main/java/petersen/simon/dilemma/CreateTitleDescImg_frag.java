package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import model.Dilemma;

/**
 * Created by Simon on 24/11/15.
 */
public class CreateTitleDescImg_frag extends Fragment implements View.OnClickListener {

    private EditText titleEdit, descEdit;
    private Button detailsButton;

    static Dilemma newDilemma;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.create_title_frag, container, false);
        //Opsætning af views.

        if (newDilemma == null)
            newDilemma = new Dilemma();

        titleEdit = (EditText) v.findViewById(R.id.titleEdit);
        descEdit = (EditText) v.findViewById(R.id.descEdit);
        detailsButton = (Button) v.findViewById(R.id.detaljerButton);
        detailsButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        newDilemma.setTitle(titleEdit.getText().toString());
        newDilemma.setDescription(descEdit.getText().toString());

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new CreateSeriousCategoryTime_frag())
                .addToBackStack(null)
                .commit();
    }
}
