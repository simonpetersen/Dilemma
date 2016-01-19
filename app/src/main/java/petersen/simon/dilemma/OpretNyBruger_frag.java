package petersen.simon.dilemma;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import diverse.App;

/**
 * Created by Sandie on 05-01-2016.
 */
public class OpretNyBruger_frag extends Fragment implements View.OnClickListener, Runnable {

    EditText email, password, email2, password2;
    Button create;
    ProgressDialog dialog;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_ny_bruger, container, false);
        getActivity().setTitle("Registrering");
        HovedAktivitet.sætTilbagePil();

        App.netværksObservatør = this;
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Opretter");

        //username = (EditText) v.findViewById(R.id.editUser);
        //username.setOnClickListener(this);
        email = (EditText) v.findViewById(R.id.editEmail);
        email.setOnClickListener(this);
        email2 = (EditText) v.findViewById(R.id.editEmail2);
        email2.setOnClickListener(this);
        password = (EditText) v.findViewById(R.id.editpassword);
        password.setOnClickListener(this);
        password2 = (EditText) v.findViewById(R.id.editpassword2);
        password2.setOnClickListener(this);

        create = (Button) v.findViewById(R.id.bCreate);
        create.setOnClickListener(this);

        return  v;
    }

    @Override
    public void onClick(View v) {

        if(v == create) {
            if(!email.getText().toString().equals(email2.getText().toString())) {
                Toast.makeText(getActivity(), "De to e-mails er ikke ens, prøv igen.", Toast.LENGTH_SHORT).show();
            }
            else if(!password.getText().toString().equals(password2.getText().toString())) {
                Toast.makeText(getActivity(), "De to adgangskoder er ikke ens, prøv igen.", Toast.LENGTH_SHORT).show();
            }
            else {
                // Kommer noget til firebase.
                App.createUser(email.getText().toString(), password.getText().toString());
                dialog.show();
            }
        }

    }

    @Override
    public void run() {
        dialog.cancel();
        Toast.makeText(getActivity(), App.opretBrugerResultat, Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new HovedMenu_frag())
                .commit();

    }
}
