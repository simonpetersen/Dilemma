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
 * Created by Blumen on 25-11-2015.
 */
public class HovedMenu_frag extends Fragment implements AdapterView.OnItemClickListener {

    private ListView LV;
    private ArrayAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.hoved_menu_frag, container, false);


        adapter = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title, App.dilemmaListe.getTitles(),
                App.dilemmaListe.getDilemmaListe());

        LV = (ListView) v.findViewById(R.id.LV);
        LV.setAdapter(adapter);
        LV.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), dilemmaListe.getBeskrivelser().get(position), Toast.LENGTH_LONG).show();
        App.dilemmaListe.selectDilemma(position);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new VisDilemma_frag())
                .addToBackStack(null)
                .commit();
    }

}
