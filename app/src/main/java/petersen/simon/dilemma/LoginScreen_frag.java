package petersen.simon.dilemma;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Blumen on 05-01-2016.
 */
public class LoginScreen_frag extends Fragment {
    EditText Email, Password;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View v = i.inflate(R.layout.login_screen_frag, container, false);

        Email = (EditText) v.findViewById(R.id.EmailInsert);
        Password = (EditText) v.findViewById(R.id.PasswordInsert);

        return v;
    }
}
