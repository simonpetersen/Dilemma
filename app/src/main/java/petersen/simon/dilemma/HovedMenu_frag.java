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
public class HovedMenu_frag extends Fragment implements AdapterView.OnItemClickListener, Runnable {

    private ListView dilemmaListView;
    private ArrayAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.hoved_menu_frag, container, false);
        App.netværksObservatør = this;

        dilemmaListView = (ListView) v.findViewById(R.id.LV);
        run();
        dilemmaListView.setOnItemClickListener(this);
        HovedAktivitet.mNavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(true);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), dilemmaListe.getBeskrivelser().get(position), Toast.LENGTH_LONG).show();
        App.dilemmaListe.selectDilemma(position);
        App.valgtDilemma = App.dilemmaListe.getValgtDilemma();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new VisDilemma_frag())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroyView() {
        App.netværksObservatør = null;
        super.onDestroyView();
    }

    @Override
    public void run() {
        App.setAktiveDilemmaer();
        adapter = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title, App.aktiveDilemmaer.getTitles(),
                App.aktiveDilemmaer.getDilemmaListe());
        dilemmaListView.setAdapter(adapter);
    }
}
