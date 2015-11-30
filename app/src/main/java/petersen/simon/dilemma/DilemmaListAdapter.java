package petersen.simon.dilemma;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaListAdapter extends ArrayAdapter {

    ArrayList<String> beskrivelser;

    public DilemmaListAdapter(Context context, int resource, int textViewResourceId, List<String> objects, ArrayList<String> beskrivelser) {
        super(context, resource, textViewResourceId, objects);
        this.beskrivelser = beskrivelser;
    }

    @Override
    public View getView(int position, View cachedView, ViewGroup parent) {
        View view = super.getView(position, cachedView, parent);

        TextView beskrivelse = (TextView) view.findViewById(R.id.Beskrivelse);
        if((beskrivelser.get(position)).length() < 35)
            beskrivelse.setText(beskrivelser.get(position));
        else
            beskrivelse.setText((beskrivelser.get(position)).substring(0, 32) + "..." );

        return view;
    }
}
