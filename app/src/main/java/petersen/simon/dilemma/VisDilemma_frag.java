package petersen.simon.dilemma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import diverse.App;
import diverse.TimeFormatter;
import model.Dilemma;

public class VisDilemma_frag extends Fragment implements View.OnClickListener {

    private Dilemma dilemma;
    private TextView title, beskrivelse, seriøsitet, udløb;
    private Button besvar, slet;
    private LinearLayout galleri;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.vis_dilemma_frag, container, false);
        //Opsætning af views.

        dilemma = App.dilemmaListe.getValgtDilemma();

        title = (TextView) v.findViewById(R.id.showTitle);
        beskrivelse = (TextView) v.findViewById(R.id.showDescription);
        seriøsitet = (TextView) v.findViewById(R.id.showSeriøsitet);
        udløb = (TextView) v.findViewById(R.id.showUdløb);
        besvar = (Button) v.findViewById(R.id.besvarButton);
        slet = (Button) v.findViewById(R.id.slet);
        galleri = (LinearLayout) v.findViewById(R.id.galleri);

        //Indæst billeder i Galleri
        if (dilemma.getBilledeUrl() != null) {
            for (String s : dilemma.getBilledeUrl()) {
                ImageView iv = new ImageView(getActivity());
                App.downloadBillede(s, iv);
                galleri.addView(iv);
            }
        }
        if(App.userID != null)
            besvar.setVisibility(View.VISIBLE);
        else
            besvar.setVisibility(View.INVISIBLE);

        if(App.userID != dilemma.getOpretterID())
            slet.setVisibility(View.VISIBLE);
        else
            slet.setVisibility(View.INVISIBLE);

        title.setText(dilemma.getTitel());
        beskrivelse.setText(dilemma.getBeskrivelse());
        seriøsitet.setText(String.valueOf(dilemma.getSeriøsitet()));

        new CountDownTimer(dilemma.getSvartidspunkt() - new Date().getTime(), 1000) {
            public void onTick(long millisUntilFinished) {
                udløb.setText(TimeFormatter.getCountdown(millisUntilFinished/1000));
            }
            public void onFinish() {
                udløb.setText("Tiden er gået!");
            }
        }.start();
        //galleri;

        //new AQuery(v).id(R.id.Billede).image(url1);

        besvar.setOnClickListener(this);
        slet.setOnClickListener(this);

        return v;
    }

    private void delete(){
        String dilemmaID;
        dilemmaID = String.valueOf(dilemma.getDilemmaID());

        App.myFirebaseRef.child(dilemmaID).removeValue();

    }

    private void fortryd() {
    }

    public void onClick(View v) {

        Fragment fragment = null;
        if (v == besvar) {

            fragment = new BesvarDilemma_frag();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold,fragment)
                    .addToBackStack(null)
                    .commit();

        }
        if (v== slet) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Er du sikker på du vil slette dit dilemma?");
            builder.setPositiveButton("Nej", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    fortryd();
                }
            });
            builder.setNegativeButton("Ja", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    delete();
                }
            });
            builder.create().show();

            fragment = new MineDilemmaer_frag();

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, fragment)
                    .addToBackStack(null)
                    .commit();

        }
    }
}
