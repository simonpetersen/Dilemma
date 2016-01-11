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

/**
 * Created by Blumen on 05-01-2016.
 */
public class Login_frag extends Fragment implements View.OnClickListener{
    EditText Email, Password;
    Button Login;
    TextView NyBruger;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.login_frag, container, false);

        Email = (EditText) v.findViewById(R.id.EmailInsert);
        Password = (EditText) v.findViewById(R.id.PasswordInsert);
        Login = (Button) v.findViewById(R.id.LoginButton);
        NyBruger = (TextView) v.findViewById(R.id.textViewNy);

        Login.setOnClickListener(this);
        NyBruger.setOnClickListener(this);

        return v;
    }

    private boolean checkInputEmail() {
        if (Email.getText().toString().equals("")) {
            return false;
        }
        else if (!Email.getText().toString().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkInputPassword() {
        if (Password.getText().toString().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        if(v == Login){

            if (checkInputEmail() == false) {
                Toast.makeText(getActivity(), "Du skal indtaste din e-mail.", Toast.LENGTH_SHORT).show();
            }
            else if (checkInputPassword() == false) {
                Toast.makeText(getActivity(),"Forkert kode, prøv igen." , Toast.LENGTH_SHORT).show();
            }
            else {
                // Kommer noget til firebase.
            }

        }
        if(v == NyBruger) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new OpretNyBruger_frag())
                    .commit();

        }
    }
}
