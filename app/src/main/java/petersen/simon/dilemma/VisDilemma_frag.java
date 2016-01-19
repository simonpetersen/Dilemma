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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import diverse.App;
import diverse.TimeFormatter;
import model.Dilemma;
import model.Logik;

public class VisDilemma_frag extends Fragment implements View.OnClickListener {

    private Dilemma dilemma;
    private TextView title, beskrivelseTekstView, beskrivelseInfoTekst, seriøsitet, udløb;
    private Button besvar, slet;
    private HorizontalScrollView galleriContainer;
    private LinearLayout galleri;
    private ArrayList<ImageView> billeder;
    public static String ValgtBillede;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.vis_dilemma_frag, container, false);
        //Opsætning af views.

        dilemma = Logik.valgtDilemma;
        Logik.sætTilbagePil(false);

        title = (TextView) v.findViewById(R.id.showTitle);
        beskrivelseTekstView = (TextView) v.findViewById(R.id.beskrivelseTekst);
        beskrivelseInfoTekst = (TextView) v.findViewById(R.id.beskrivelse);
        seriøsitet = (TextView) v.findViewById(R.id.showSeriøsitet);
        udløb = (TextView) v.findViewById(R.id.showUdløb);
        besvar = (Button) v.findViewById(R.id.besvarButton);
        slet = (Button) v.findViewById(R.id.slet);
        galleri = (LinearLayout) v.findViewById(R.id.galleri);
        galleriContainer = (HorizontalScrollView) v.findViewById(R.id.horizontalScrollView);


        if (dilemma.getBeskrivelse().equals("")) beskrivelseInfoTekst.setVisibility(View.INVISIBLE);

        galleriContainer.getLayoutParams().height = 0;
        v.requestLayout();

            //Indæst billeder i Galleri
        for (String s : dilemma.getBilledeUrl()) {
            ImageView iv = new ImageView(getActivity());
            iv.setAdjustViewBounds(true);
            App.downloadBillede(s, iv);
            galleri.addView(iv);
            final String valgtbilledeURL = s;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ValgtBillede = valgtbilledeURL;

                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragmentindhold, new VisStortBillede_frag())
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
        if(App.userID != null && App.userID.equals(dilemma.getOpretterID()))
            besvar.setText("Se besvarelser");
        else
            slet.setVisibility(View.INVISIBLE);


        title.setText(dilemma.getTitel());
        beskrivelseTekstView.setText(dilemma.getBeskrivelse());
        seriøsitet.setText(String.valueOf(dilemma.getSeriøsitet()));

        new CountDownTimer(dilemma.getSvartidspunkt() - new Date().getTime(), 1000) {
            public void onTick(long millisUntilFinished) {
                udløb.setText(TimeFormatter.getCountdown(millisUntilFinished/1000));
            }
            public void onFinish() {
                udløb.setText("Tiden er gået!");
            }
        }.start();

        besvar.setOnClickListener(this);
        slet.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        Boolean check = false;
        Fragment fragment = null;
        if (v == besvar) {
            if(App.userID == null){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Du er ikke logget ind.");
                builder.setMessage("Du er nødt til at være logget ind for at besvare dilemmaer. Vil du logge ind nu?");
                builder.setPositiveButton("Ja", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragmentindhold, new Login_frag())
                                .commit();
                    }
                });
                builder.setNegativeButton("Nej", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                builder.show();
            }
            else if (App.userID.equals(dilemma.getOpretterID())) {
                check = true;
                fragment = new VisStatistik_frag();
            }
            else {
                check = true;
                fragment = new BesvarDilemma_frag();
            }
            if(check) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, fragment)
                        .addToBackStack(null)
                        .commit();
            }

        }
        if (v== slet) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Er du sikker på du vil slette dit dilemma?");
            builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    sletDilemma();
                }
            });
            builder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });
            builder.create().show();
        }

       /* for(int i = 0; i < billeder.size(); i++){
            if( v.getId() == billeder.get(i).getId()){
                Toast.makeText(getActivity(), "Der blev klikket på: " + i, Toast.LENGTH_SHORT).show();
            }
        }*/
    }

    private void sletDilemma(){
        String dilemmaID;
        dilemmaID = String.valueOf(dilemma.getDilemmaID());
        App.dilemmaFirebaseRef.child(dilemmaID).removeValue();
        App.besvarelseFirebaseRef.child(dilemmaID).removeValue();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new MineDilemmaer_frag())
                .addToBackStack(null)
                .commit();
    }
}
