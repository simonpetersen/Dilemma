package petersen.simon.dilemma;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import diverse.App;

public class HovedAktivitet extends AppCompatActivity implements NavigationDrawer_frag.NavigationDrawerCallbacks {

    private NavigationDrawer_frag mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoved_aktivitet);

        mNavigationDrawerFragment = (NavigationDrawer_frag)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment;

        /*if (position==0)
            fragment = new HovedMenu_frag();
        else if(position == 1)
            fragment = new OpretDilemma1Titel_frag();
        else (position == 2)
            fragment = new Login_frag();
            */
        switch (position){
            case 0: fragment = new HovedMenu_frag();
                break;
            case 1: if(App.userID != null) fragment = new OpretDilemma1Titel_frag();
                else{
                Toast.makeText(this, "Du skal være logget ind før du kan oprette dilemmaer. Log venligst ind, eller opret dig" +
                        "som bruger og prøv igen.", Toast.LENGTH_SHORT).show();
                fragment = new Login_frag();
            }
                break;
            case 2: fragment = new MineDilemmaer_frag();
                break;
            case 3: fragment = new BesvaredeDilemmaer_frag();
                break;
            case 4: if(App.userID != null) fragment = new Login_frag();
                    else{
                App.logout();
                App.userID = null;
                fragment = new HovedMenu_frag();
            }
                break;
            default: fragment = new HovedMenu_frag();
                break;
        }

        if(position == 0) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, fragment)
                    .commit();
        }
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, fragment).addToBackStack(null)
                    .commit();
        }
    }
}
