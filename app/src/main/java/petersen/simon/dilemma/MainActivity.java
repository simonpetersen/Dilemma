package petersen.simon.dilemma;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NavigationDrawer_frag.NavigationDrawerCallbacks {

    private NavigationDrawer_frag mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        if (position==0)
            fragment = new MainMenu_frag();
        else
            fragment = new CreateTitleDescImg_frag();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, fragment)
                .commit();
    }
}
