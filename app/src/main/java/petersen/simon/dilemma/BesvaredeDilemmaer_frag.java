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

    private ListView besvaredeDilemmaer;
    private ArrayAdapter adapter3;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedIntanceState) {
        View v = i.inflate(R.layout.besvarede_dilemmaer_frag, container, false);

        adapter3 = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title, App.besvaredeDilemmaer.getTitles(), App.besvaredeDilemmaer.getDilemmaListe());

        besvaredeDilemmaer = (ListView) v.findViewById(R.id.LV3);
        besvaredeDilemmaer.setAdapter(adapter3);
        besvaredeDilemmaer.setOnItemClickListener(this);

        return  v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        App.besvaredeDilemmaer.selectDilemma(position);
        App.valgtDilemma = App.besvaredeDilemmaer.getValgtDilemma();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new VisDilemma_frag())
                .addToBackStack(null)
                .commit();

    }
}
