package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import diverse.App;

/**
 * Created by Sandie on 12-01-2016.
 */
public class BesvaredeDilemmaer_frag extends Fragment implements AdapterView.OnItemClickListener {

    private ListView LV3;
    private ArrayAdapter adapter3;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedIntanceState) {
        View v = i.inflate(R.layout.besvarede_dilemmaer_frag, container, false);

        adapter3 = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title, App.dilemmaListe.getTitles(), App.dilemmaListe.getDilemmaListe());

        LV3 = (ListView) v.findViewById(R.id.LV3);
        LV3.setAdapter(adapter3);
        LV3.setOnItemClickListener(this);

        return  v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Her kommer til at være noget lidt andet (dilemmaListe), databasen er ikke klar til dette endnu.
        App.dilemmaListe.selectDilemma(position);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new VisDilemma_frag())
                .addToBackStack(null)
                .commit();

    }
}
