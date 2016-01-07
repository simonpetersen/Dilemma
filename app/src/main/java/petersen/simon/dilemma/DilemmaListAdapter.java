package petersen.simon.dilemma;

import android.content.Context;
import android.media.Rating;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import diverse.TimeFormatter;
import model.Dilemma;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaListAdapter extends ArrayAdapter {
    private ArrayList<Dilemma> newDilemmaList;
    private TextView beskrivelse, tidTilbage;
    private RatingBar seriøsitet;

    public DilemmaListAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> titles, ArrayList<Dilemma> DilemmaList) {
        super(context, resource, textViewResourceId, titles);
     newDilemmaList = DilemmaList;
    }

    @Override
    public View getView(int position, View cachedView, ViewGroup parent) {
        View view = super.getView(position, cachedView, parent);

        beskrivelse = (TextView) view.findViewById(R.id.Description);
        tidTilbage = (TextView) view.findViewById(R.id.TimeToLive);
        seriøsitet = (RatingBar) view.findViewById(R.id.Seriousness);

        //Title.setText(newDilemmaList.get(position).getTitel());
        tidTilbage.setText(TimeFormatter.getTidString(newDilemmaList.get(position).getSvartidspunkt()) + " tilbage");
        seriøsitet.setRating(newDilemmaList.get(position).getSeriøsitet());

        if((newDilemmaList.get(position).getBeskrivelse()).length() < 35)
           beskrivelse.setText(newDilemmaList.get(position).getBeskrivelse());
        else
            beskrivelse.setText((newDilemmaList.get(position).getBeskrivelse()).substring(0, 32) + "..." );

        return view;
    }
}
