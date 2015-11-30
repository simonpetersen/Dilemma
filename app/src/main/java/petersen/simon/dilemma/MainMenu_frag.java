package petersen.simon.dilemma;



import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

import model.DilemmaList;

/**
 * Created by Blumen on 25-11-2015.
 */
public class MainMenu_frag extends Fragment implements AdapterView.OnItemClickListener {

    static DilemmaList dilemmaList;

    ListView LV;
    ArrayAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.main_menu_frag, container, false);

        if (dilemmaList == null) dilemmaList = new DilemmaList();

        adapter = new DilemmaListAdapter(getActivity(), R.layout.main_menu_liste_element, R.id.Overskrift,
                dilemmaList.getOverskrifter(), dilemmaList.getBeskrivelser());

        LV = (ListView) v.findViewById(R.id.LV);
        LV.setAdapter(adapter);
        LV.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), dilemmaList.getBeskrivelser().get(position), Toast.LENGTH_LONG).show();
    }

    private void initArrayLists() {

    }

}
