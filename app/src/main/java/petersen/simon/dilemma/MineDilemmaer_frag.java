package petersen.simon.dilemma;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
public class MineDilemmaer_frag extends Fragment implements AdapterView.OnItemClickListener {
    private ListView LV2;
    private ArrayAdapter adapter2;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        View v = i.inflate(R.layout.mine_dilemmaer_frag,container,false);

        adapter2 = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title, App.getEgneDilemmaer().getTitles(),App.getEgneDilemmaer().getDilemmaListe());

        LV2 = (ListView) v.findViewById(R.id.LV2);
        LV2.setAdapter(adapter2);
        LV2.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        App.getEgneDilemmaer().selectDilemma(position);
                getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new VisDilemma_frag())
                .addToBackStack(null)
                .commit();

    }
}
