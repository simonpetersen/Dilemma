package petersen.simon.dilemma;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import diverse.App;
import model.BesvarelseListe;

/**
 * Created by Sandie on 11-01-2016.
 */
public class VisStatistik_frag extends Fragment{

    GraphView graph;
    TextView titelView;
    BesvarelseListe besvarelseListe;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedIntanceState) {
        View v = i.inflate(R.layout.vis_statistik_frag, container, false);
        titelView = (TextView) v.findViewById(R.id.titelView);
        titelView.setText(App.valgtDilemma.getTitel());

        graph = (GraphView) v.findViewById(R.id.graph);
        besvarelseListe = App.getBesvarelsesListe(App.valgtDilemma.getDilemmaID());
        int[] colors = {Color.parseColor("#FF0001D1"), Color.parseColor("#FFFF4743"), Color.parseColor("#FFFF7600"), Color.DKGRAY};

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
        graph.getViewport().setScalable(true);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.0);
        graph.getViewport().setMaxX(besvarelseListe.getSvar().length+1);

        graph.getLegendRenderer().setVisible(true);

        return v;
    }
}
