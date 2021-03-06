package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import diverse.App;
import model.Logik;

/**
 * Created by Simon on 24/11/15.
 */
public class OpretDilemma2Kategori_frag extends Fragment
        implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener,
        View.OnClickListener{

    Spinner CategorySpinner;
    final String[] Category = new String[] {"Vælg kategori", "Personlig", "Fest", "Hobby", "Begivenhed", "Mode", "Mad", "karriere", "Andet"};
    String CategoryChosen = "";
    SeekBar Seriousness, Timer;
    Button Ok;
    TextView SeriousnessView, TimerView;
    int SeriousnessChosen, TimerChosen;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_dilemma_kategori, container, false);
        Logik.sætTilbagePil(false);
        getActivity().setTitle("Opret");

        CategorySpinner = (Spinner) v.findViewById(R.id.Kategori);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.liste_elementer_serioeritetsspinner, R.id.Spinner_text, Category){
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);
                ImageView billede = (ImageView) view.findViewById(R.id.kategoriView);
                switch (position){
                    case 0: billede.setVisibility(View.INVISIBLE);
                        break;
                    case 1: billede.setImageResource(R.mipmap.personlig);
                        break;
                    case 2: billede.setImageResource(R.mipmap.fest);
                        break;
                    case 3: billede.setImageResource(R.mipmap.hobby);
                        break;
                    case 4: billede.setImageResource(R.mipmap.begivenhed);
                        break;
                    case 5: billede.setImageResource(R.mipmap.mode);
                        break;
                    case 6: billede.setImageResource(R.mipmap.mad);
                        break;
                    case 7: billede.setImageResource(R.mipmap.karriere);
                        break;
                    case 8: billede.setImageResource(R.mipmap.andet);
                        break;
                    default: billede.setVisibility(View.INVISIBLE);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(R.layout.liste_elementer_serioeritetsspinner);

        CategorySpinner.setAdapter(adapter);
        CategorySpinner.setOnItemSelectedListener(this);
        CategorySpinner.setPrompt("Vælg en kategori.");

        Seriousness = (SeekBar) v.findViewById(R.id.SeriousnessBar);
        Seriousness.setOnSeekBarChangeListener(this);

        SeriousnessView = (TextView) v.findViewById(R.id.VælgSeriøsitet);
        SeriousnessView.setText("Seriøsitetsgrad: " + Seriousness.getProgress() + " ud af 5.");

        Timer = (SeekBar) v.findViewById(R.id.TimerBar);
        Timer.setOnSeekBarChangeListener(this);

        TimerView = (TextView) v.findViewById(R.id.VælgTidsinterval);
        TimerView.setText("Tidsinterval: " + Timer.getProgress() + " minutter.");

        Ok = (Button) v.findViewById(R.id.buttonOK);
        Ok.setOnClickListener(this);
        return v;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    CategoryChosen = Category[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity(), "Vælg venligst en kategori.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == Seriousness) {
            SeriousnessView.setText("Seriøsitetsgrad: " + Seriousness.getProgress() + " ud af 5.");
            Timer.setMax(Seriousness.getProgress() * 15);
        }
        if(seekBar == Timer)
            TimerView.setText("Tidsinterval: " + Timer.getProgress() + " minutter.");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        if(v == Ok) {

            if(CategoryChosen.equals("Vælg kategori")) {
                Toast.makeText(getActivity(), "Du mangler at vælge kategori.", Toast.LENGTH_SHORT).show();
            }
            else if(Seriousness.getProgress() == 0) {
                Toast.makeText(getActivity(), "Du mangler at vælge seriøsitetsgrad.", Toast.LENGTH_SHORT).show();
            }
            else if(Timer.getProgress() == 0) {
                Toast.makeText(getActivity(), "Du mangler at vælge tiden.", Toast.LENGTH_SHORT).show();
            }

            else {
                SeriousnessChosen = Seriousness.getProgress();
                TimerChosen = Timer.getProgress();

                Logik.oprettetDilemma.setKategori(CategorySpinner.getItemAtPosition(
                        CategorySpinner.getSelectedItemPosition()).toString());
                Logik.oprettetDilemma.setSeriøsitet(SeriousnessChosen);
                Logik.oprettetDilemma.setSvartidspunkt(TimerChosen);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new OpretDilemma3Svarmuligheder_frag())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
}
