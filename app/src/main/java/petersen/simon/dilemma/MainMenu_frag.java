package petersen.simon.dilemma;



import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Blumen on 25-11-2015.
 */
public class MainMenu_frag extends Fragment implements AdapterView.OnItemClickListener {

    List<String> Overskrifter = Arrays.asList("Hjælp til bukser", "Hjælp til kjoler",
            "Skal hunden dø?", "Hvornår skal jeg sige stop?");
    ArrayList ArrayOverskrifter = new ArrayList(Overskrifter);
    List<String> Beskrivelse = Arrays.asList("Hjælp mig med hvilke busker jeg skal tage på.",
            "Hvilken kjole skal jeg købe til bryllupet?", "Skal min kræftsyge hund aflives?",
            "Min kone bliver ved med at skifte mine ting i lejligheden ud med hendes.. Hvad skal jeg gøre?");
    ArrayList ArrayBeskrivelse = new ArrayList(Beskrivelse);

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.main_menu_frag, container, false);
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.main_menu_liste,R.id.Overskrift){

                 @Override
               public View getView(int position, View cachedView, ViewGroup parent) {
                   View view = super.getView(position, cachedView, parent);

                   TextView beskrivelse = (TextView) view.findViewById(R.id.Beskrivelse);
                   beskrivelse.setText((String) ArrayBeskrivelse.get(position));

                   return view;
               }
            };
        //ListView listView = new ListView(getActivity());
        ListView listView = (ListView) v.findViewById(R.id.LV);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        //View v = i.inflate(R.layout.main_menu_frag, listView, false);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
