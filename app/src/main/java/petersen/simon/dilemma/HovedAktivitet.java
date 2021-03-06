package petersen.simon.dilemma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import diverse.App;

public class HovedAktivitet extends AppCompatActivity implements NavigationDrawer_frag.NavigationDrawerCallbacks {

    public static NavigationDrawer_frag mNavigationDrawerFragment;
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

    public Fragment gåTilLogin(){
        Toast.makeText(this, "Du skal være logget ind før du kan oprette dilemmaer. Log venligst ind, eller opret dig" +
                " som bruger og prøv igen.", Toast.LENGTH_LONG).show();
        return new Login_frag();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment = null;

        switch (position){
            case 0: fragment = new HovedMenu_frag();
                break;
            case 1: if(App.userID != null) fragment = new OpretDilemma1Titel_frag();
                else fragment = gåTilLogin();
                break;
            case 2: if(App.userID != null) fragment = new MineDilemmaer_frag();
                else fragment = gåTilLogin();
                break;
            case 3: if(App.userID != null) fragment = new BesvaredeDilemmaer_frag();
                else fragment = gåTilLogin();
                break;
            case 4: if(App.userID == null) fragment = new Login_frag();
                    else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Er du sikker på, at du vil logge ud?");
                builder.setPositiveButton("Ja", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        App.logout();
                        mNavigationDrawerFragment.updateDrawer();
                        logUdOgHjem();
                    }
                });
                builder.setNegativeButton("Nej", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                builder.show();
            }

                break;
            default: fragment = new HovedMenu_frag();
                break;
        }
        if(fragment != null){
            if (fragment instanceof HovedMenu_frag) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, fragment)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentindhold, fragment)
                        .commit();
            }
        }
    }

    public Fragment logUdOgHjem(){
        Toast.makeText(this, "Du er nu blevet logget ud.", Toast.LENGTH_SHORT).show();
        return new HovedMenu_frag();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount()!=1)
            getSupportFragmentManager().popBackStack();
    }

}
