package petersen.simon.dilemma;

import android.content.Context;
import android.media.Rating;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    private ImageView kategori;

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
        kategori = (ImageView) view.findViewById(R.id.kategori);
        tidTilbage.setText(TimeFormatter.getTidString(newDilemmaList.get(position).getSvartidspunkt()) + " tilbage");
        seriøsitet.setRating(newDilemmaList.get(position).getSeriøsitet());

        switch (newDilemmaList.get(position).getKategori()){
            case "Fest": kategori.setImageResource(R.mipmap.fest);
                break;
            case "Hobby": kategori.setImageResource(R.mipmap.hobby);
                break;
            case "Personligt": kategori.setImageResource(R.mipmap.personlig);
                break;
            case "Begivenhed": kategori.setImageResource(R.mipmap.begivenhed);
                break;
            case "Mode": kategori.setImageResource(R.mipmap.mode);
                break;
            case "mad": kategori.setImageResource(R.mipmap.mad);
                break;
            case "karriere": kategori.setImageResource(R.mipmap.karriere);
                break;
            case "Andet": kategori.setImageResource(R.mipmap.andet);
                break;
            default: System.out.print("Der er noget galt med koden :S");
                break;
        }

        if((newDilemmaList.get(position).getBeskrivelse()).length() < 35)
           beskrivelse.setText(newDilemmaList.get(position).getBeskrivelse());
        else
            beskrivelse.setText((newDilemmaList.get(position).getBeskrivelse()).substring(0, 32) + "..." );

        return view;
    }
}
