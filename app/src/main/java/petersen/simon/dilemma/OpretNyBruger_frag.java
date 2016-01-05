package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sandie on 05-01-2016.
 */
public class OpretNyBruger_frag extends Fragment implements View.OnClickListener {

    TextView registrer;
    EditText username, email, password;
    Button create;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View v = i.inflate(R.layout.opret_ny_bruger, container, false);

        registrer = (TextView) v.findViewById(R.id.veiwRegi);
        registrer.setText("Registrering");

        username = (EditText) v.findViewById(R.id.editUser);
        username.setOnClickListener(this);
        email = (EditText) v.findViewById(R.id.editEmail);
        email.setOnClickListener(this);
        password = (EditText) v.findViewById(R.id.editpassword);
        password.setOnClickListener(this);

        create = (Button) v.findViewById(R.id.bCreate);
        create.setOnClickListener(this);

        return  v;
    }

    @Override
    public void onClick(View v) {

        if(v == create) {

        }

    }
}
