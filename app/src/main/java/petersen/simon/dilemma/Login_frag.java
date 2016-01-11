package petersen.simon.dilemma;

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
 * Created by Blumen on 05-01-2016.
 */
public class Login_frag extends Fragment implements View.OnClickListener{
    private EditText email, kodeord;
    private Button loginKnap;
    private TextView nyBruger;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.login_frag, container, false);

        email = (EditText) v.findViewById(R.id.EmailInsert);
        kodeord = (EditText) v.findViewById(R.id.PasswordInsert);
        loginKnap = (Button) v.findViewById(R.id.LoginButton);
        nyBruger = (TextView) v.findViewById(R.id.textViewNy);

        loginKnap.setOnClickListener(this);
        nyBruger.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == loginKnap){
            App.login(email.getText().toString(), kodeord.getText().toString());
            if (App.userID == null) Toast.makeText(getActivity(), App.fejlBesked, Toast.LENGTH_SHORT).show();
            else {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, new HovedMenu_frag())
                        .commit();
            }
        }
        if(v == nyBruger) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new OpretNyBruger_frag())
                    .commit();

        }
    }
}
