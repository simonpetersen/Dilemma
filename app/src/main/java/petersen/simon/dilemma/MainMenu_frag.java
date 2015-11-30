package petersen.simon.dilemma;



import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Blumen on 25-11-2015.
 */
public class MainMenu_frag extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    static List<String> overskrifter = Arrays.asList("Hjælp til bukser", "Hjælp til kjoler",
            "Skal hunden dø?", "Hvornår skal jeg sige stop?");
    static ArrayList arrayOverskrifter = new ArrayList(overskrifter);
    static List<String> beskrivelse = Arrays.asList("Hjælp mig med hvilke bukser jeg skal tage på.",
            "Hvilken kjole skal jeg købe til bryllupet?", "Skal min kræftsyge hund aflives?",
            "Min kone bliver ved med at skifte mine ting i lejligheden ud med hendes.. Hvad skal jeg gøre?");
    static ArrayList arrayBeskrivelse = new ArrayList(beskrivelse);
    ListView LV;
    FloatingActionButton fab;
    ArrayAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.main_menu_frag, container, false);
            adapter = new ArrayAdapter(getActivity(), R.layout.main_menu_liste,R.id.Overskrift, arrayOverskrifter){

                 @Override
               public View getView(int position, View cachedView, ViewGroup parent) {
                   View view = super.getView(position, cachedView, parent);

                   TextView beskrivelse = (TextView) view.findViewById(R.id.Beskrivelse);
                   if(((String) arrayBeskrivelse.get(position)).length() < 35)
                     beskrivelse.setText((String) arrayBeskrivelse.get(position));
                   else
                    beskrivelse.setText((String) ((String) arrayBeskrivelse.get(position)).substring(0, 32) + "..." );

                   return view;
               }
            };

        LV = (ListView) v.findViewById(R.id.LV);
        LV.setAdapter(adapter);
        LV.setOnItemClickListener(this);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), (String) arrayBeskrivelse.get(position), Toast.LENGTH_LONG).show();


    }

    @Override
    public void onClick(View v) {
        if(v == fab)
            getFragmentManager().beginTransaction()
            .replace(R.id.fragmentindhold, new CreateTitleDescImg_frag())
            .addToBackStack(null)
            .commit();
    }
}
