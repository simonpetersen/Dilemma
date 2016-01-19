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
 * Created by Blumen on 25-11-2015.
 */
public class HovedMenu_frag extends Fragment implements AdapterView.OnItemClickListener, Runnable {

    private ListView dilemmaListView;
    private ArrayAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.hoved_menu_frag, container, false);
        getActivity().setTitle(R.string.app_name);
        App.netværksObservatør = this;

        dilemmaListView = (ListView) v.findViewById(R.id.LV);
        run();
        dilemmaListView.setOnItemClickListener(this);
        HovedAktivitet.mNavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(true);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), dilemmaListe.getBeskrivelser().get(position), Toast.LENGTH_LONG).show();
        Logik.dilemmaListe.selectDilemma(position);
        Logik.valgtDilemma = Logik.dilemmaListe.getValgtDilemma();
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
        Logik.setAktiveDilemmaer();
        adapter = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title, Logik.aktiveDilemmaer.getTitles(),
                Logik.aktiveDilemmaer.getDilemmaListe());
        dilemmaListView.setAdapter(adapter);
    }
}
