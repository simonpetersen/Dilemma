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
import model.Logik;

/**
 * Created by Sandie on 12-01-2016.
 */
public class BesvaredeDilemmaer_frag extends Fragment implements AdapterView.OnItemClickListener, Runnable {

    private ListView besvaredeDilemmaer;
    private ArrayAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedIntanceState) {
        View v = i.inflate(R.layout.besvarede_dilemmaer_frag, container, false);
        getActivity().setTitle("Besvarede dilemmaer");
        HovedAktivitet.mNavigationDrawerFragment.mDrawerToggle.setDrawerIndicatorEnabled(true);
        App.netværksObservatør = this;

        besvaredeDilemmaer = (ListView) v.findViewById(R.id.LV3);
        run();
        besvaredeDilemmaer.setOnItemClickListener(this);

        return  v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Logik.besvaredeDilemmaer.selectDilemma(position);
        Logik.valgtDilemma = Logik.besvaredeDilemmaer.getValgtDilemma();
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
        Logik.setBesvaredeDilemmaer();
        adapter = new DilemmaListAdapter(getActivity(), R.layout.hoved_menu_liste_element, R.id.Title,
                Logik.besvaredeDilemmaer.getTitles(), Logik.besvaredeDilemmaer.getDilemmaListe());
        besvaredeDilemmaer.setAdapter(adapter);
    }
}
