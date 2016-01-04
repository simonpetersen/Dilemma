package petersen.simon.dilemma;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import model.Dilemma;

/**
 * Created by Simon on 30/11/15.
 */
public class DilemmaListAdapter extends ArrayAdapter {
    ArrayList<Dilemma> newDilemmaList;

    public DilemmaListAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> titles, ArrayList<Dilemma> DilemmaList) {
        super(context, resource, textViewResourceId, titles);
     newDilemmaList = DilemmaList;
    }

    @Override
    public View getView(int position, View cachedView, ViewGroup parent) {
        View view = super.getView(position, cachedView, parent);

        TextView Title = (TextView) view.findViewById(R.id.Title);
        TextView Description = (TextView) view.findViewById(R.id.Description);
        TextView TimeToLive = (TextView) view.findViewById(R.id.TimeToLive);
        TextView Seriousness = (TextView) view.findViewById(R.id.Seriousness);

        Title.setText(newDilemmaList.get(position).getTitle());
        TimeToLive.setText(newDilemmaList.get(position).getTime() + "Tid tilbage");
        Seriousness.setText(newDilemmaList.get(position).getSerious());

        if((newDilemmaList.get(position).getDescription()).length() < 35)
           Description.setText(newDilemmaList.get(position).getDescription());
        else
            Description.setText((newDilemmaList.get(position).getDescription()).substring(0, 32) + "..." );

        return view;
    }
}
