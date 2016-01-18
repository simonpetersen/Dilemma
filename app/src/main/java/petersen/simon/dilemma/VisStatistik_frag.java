package petersen.simon.dilemma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import diverse.App;
import model.BesvarelseListe;

/**
 * Created by Sandie on 11-01-2016.
 */
public class VisStatistik_frag extends Fragment implements View.OnClickListener {

    GraphView graph;
    TextView titelView;
    BesvarelseListe besvarelseListe;
    FloatingActionButton svar;
    EditText kommentarTekst;
    ListView kommentar;
    private ArrayAdapter adp;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedIntanceState) {
        View v = i.inflate(R.layout.vis_statistik_frag, container, false);
        titelView = (TextView) v.findViewById(R.id.titelView);
        titelView.setText(App.valgtDilemma.getTitel());

        ArrayAdapter adp = new ArrayAdapter(getActivity(), R.layout.vis_statistik_liste_element, R.id.besvarelse2, App.getBesvarelser(App.valgtDilemma.getDilemmaID()).getKommentarer());

        kommentar = (ListView) v.findViewById(R.id.kommentarer);
        kommentar.setAdapter(adp);

        graph = (GraphView) v.findViewById(R.id.graph);
        besvarelseListe = App.getBesvarelsesListe(App.valgtDilemma.getDilemmaID());
        int[] colors = {Color.parseColor("#FF0001D1"), Color.parseColor("#FFFF4743"), Color.parseColor("#FFFF7600"),
                Color.DKGRAY, Color.parseColor("#FFBF00C1")};
        svar = (FloatingActionButton) v.findViewById(R.id.actionButton);
        svar.setOnClickListener(this);
        kommentarTekst = new EditText(getActivity());

        DataPoint[] dataPoints = new DataPoint[1];
        BarGraphSeries<DataPoint> series;
        for (int n=0; n < besvarelseListe.getSvar().length; n++) {
            dataPoints[0] = new DataPoint(n+1, besvarelseListe.getSvar()[n]);
            series = new BarGraphSeries<>(dataPoints);
            series.setTitle(App.valgtDilemma.getSvarmuligheder().get(n));
            graph.addSeries(series);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.BLACK);
            series.setColor(colors[n]);
        }

        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getViewport().setScalable(true);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.0);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0.0);
        graph.getViewport().setMaxX(besvarelseListe.getSvar().length + 1);

        graph.getLegendRenderer().setVisible(true);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v==svar) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Skriv dit endelige valg på dilemmaet: ");

            kommentarTekst.setHint("Skriv et svar");
            builder.setView(kommentarTekst);
            builder.setPositiveButton("Send", new AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendDilemma();
                }
            });
            builder.setNegativeButton("Annuller", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });
            builder.create().show();
        }
    }

    public void sendDilemma() {
        App.valgtDilemma.setSvarkommentar(kommentarTekst.getText().toString());
        App.dilemmaFirebaseRef.child(String.valueOf(App.valgtDilemma.getDilemmaID())).setValue(App.valgtDilemma);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new MineDilemmaer_frag())
                .addToBackStack(null)
                .commit();
    }
}
