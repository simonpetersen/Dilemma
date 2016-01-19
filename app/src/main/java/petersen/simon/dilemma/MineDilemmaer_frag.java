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
import model.Logik;

/**
 * Created by Sandie on 12-01-2016.
 */
public class MineDilemmaer_frag extends Fragment implements AdapterView.OnItemClickListener, Runnable {
    private ListView egneDilemmaerListView;
    private ArrayAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        View v = i.inflate(R.layout.mine_dilemmaer_frag,container,false);
        HovedAktivitet.mNavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(true);
        App.netværksObservatør = this;
        egneDilemmaerListView = (ListView) v.findViewById(R.id.LV2);
        run();
        egneDilemmaerListView.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Logik.egneDilemmaer.selectDilemma(position);
        Logik.valgtDilemma = Logik.egneDilemmaer.getValgtDilemma();
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
        Logik.setEgneDilemmaer();
        adapter = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title, Logik.egneDilemmaer.getTitles(),
                Logik.egneDilemmaer.getDilemmaListe());
        egneDilemmaerListView.setAdapter(adapter);
    }
}
