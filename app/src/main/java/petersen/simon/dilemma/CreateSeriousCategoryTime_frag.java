package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Simon on 24/11/15.
 */
public class CreateSeriousCategoryTime_frag extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner CategorySpinner;
    final String[] Category = new String[] {"Personlig", "Fest", "Hobby", "Begivenhed", "Mode", "Mad", "Karriere", "Andet"};

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.create_serious_frag, container, false);

        CategorySpinner = (Spinner) v.findViewById(R.id.Kategori);
        CategorySpinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.liste_elementer_serioeritetsspinner, R.id.Spinner_text, Category);
        adapter.setDropDownViewResource(R.layout.liste_elementer_serioeritetsspinner);

        CategorySpinner.setAdapter(adapter);
        CategorySpinner.setPrompt("Choose a Category.");
        return v;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
