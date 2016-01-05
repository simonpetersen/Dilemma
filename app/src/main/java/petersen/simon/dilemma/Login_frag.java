package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Blumen on 05-01-2016.
 */
public class Login_frag extends Fragment implements View.OnClickListener{
        EditText Email, Password;
    Button Login;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.login_frag, container, false);

        Email = (EditText) v.findViewById(R.id.EmailInsert);
        Password = (EditText) v.findViewById(R.id.PasswordInsert);
        Login = (Button) v.findViewById(R.id.LoginButton);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == Login){

        }
    }
}
