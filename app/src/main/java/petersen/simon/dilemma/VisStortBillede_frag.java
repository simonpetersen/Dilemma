package petersen.simon.dilemma;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidquery.AQuery;

import diverse.App;

public class VisStortBillede_frag extends Fragment {

    public ImageView stortBillede;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {
        View v = i.inflate(R.layout.vis_stort_billede, container, false);
        stortBillede = (ImageView) v.findViewById(R.id.StortBillede);

        App.downloadBillede(VisDilemma_frag.ValgtBillede, stortBillede);

        return v;
    }
}