package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Sandie on 11-01-2016.
 */
public class VisStatistik_frag extends Fragment implements View.OnClickListener {

    GraphView graph;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedIntanceState) {
        View v = i.inflate(R.layout.vis_statistik_frag, container, false);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
