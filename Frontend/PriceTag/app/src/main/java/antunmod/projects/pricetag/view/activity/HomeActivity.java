package antunmod.projects.pricetag.view.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import antunmod.projects.pricetag.service.HomeService;
import antunmod.projects.pricetag.transfer.UserInformation;
import antunmod.projects.pricetag.view.fragment.AddProductFragment;
import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.model.User;
import antunmod.projects.pricetag.view.fragment.EnterBarcodeFragment;
import antunmod.projects.pricetag.view.fragment.ProfileFragment;
import antunmod.projects.pricetag.view.fragment.RecentProductsFragment;
import antunmod.projects.pricetag.view.fragment.SearchFragment;
import antunmod.projects.pricetag.view.fragment.SelectFragment;
import antunmod.projects.pricetag.view.fragment.UpdateProductFragment;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SelectFragment.OnFragmentInteractionListener, UpdateProductFragment.OnFragmentInteractionListener,
        AddProductFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener{

    public static User user;
    private HomeService homeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (User) getIntent().getSerializableExtra("user");
        homeService = new HomeService();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set default fragment
        if (savedInstanceState == null) {
            setFragment(new EnterBarcodeFragment());
        }


    }

    @Override
    public void onBackPressed() {

        List fragmentList = getSupportFragmentManager().getFragments();

        boolean handled = false;
        for (Fragment f : (List<Fragment>) fragmentList) {
            if (f instanceof SelectFragment) {
                handled = ((SelectFragment) f).onBackPressed();

                if (!handled)
                    super.onBackPressed();

            } else if (f instanceof AddProductFragment ||f instanceof UpdateProductFragment) {
                getSupportFragmentManager().popBackStack();
            }
        }


        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        Integer count = getSupportFragmentManager().getBackStackEntryCount();

        if (count > 1) {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Integer id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(HomeActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(HomeActivity.this);
            }
            builder.setTitle("Odjava")
                    .setMessage("Jeste li sigurni da se želite odjaviti?")
                    .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // do nothing

                        }
                    })
                    .setIcon(R.drawable.ic_logout);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Integer id = item.getItemId();

        FragmentManager manager = getSupportFragmentManager();

        switch (id) {
            case (R.id.add_product):
                EnterBarcodeFragment findProductForBarcodeFragment = new EnterBarcodeFragment();
                manager.beginTransaction().replace(R.id.layout_for_fragment, findProductForBarcodeFragment).commit();
                break;
            case (R.id.recent):
                RecentProductsFragment recentProductsFragment = new RecentProductsFragment();
                manager.beginTransaction().replace(R.id.layout_for_fragment, recentProductsFragment).commit();
                break;
            case (R.id.search):
                SearchFragment searchFragment = new SearchFragment();
                manager.beginTransaction().replace(R.id.layout_for_fragment, searchFragment).commit();
                break;
            case (R.id.profile):
                homeService.findUserInformation(this, user.getId());
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(EnterBarcodeFragment enterBarcodeFragmetFragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            if (getSupportFragmentManager().findFragmentById(R.id.layout_for_fragment) == null) {
                ft.add(R.id.layout_for_fragment, enterBarcodeFragmetFragment);
            } else {
                ft.replace(R.id.layout_for_fragment, enterBarcodeFragmetFragment);
            }
            ft.addToBackStack(null);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(getApplicationContext(), "Tu sam", Toast.LENGTH_SHORT).show();
    }

    public void foundUserInformation(UserInformation userInformation) {
        FragmentManager manager = getSupportFragmentManager();
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("userInformation", userInformation);
        profileFragment.setArguments(bundle);

        manager.beginTransaction().replace(R.id.layout_for_fragment, profileFragment).commit();
    }

    public interface OnBackPressedListener {

        /**
         * Callback, which is called if the Back Button is pressed.
         * Fragments that extend MainFragment can/should override this Method.
         *
         * @return true if the App can be closed, false otherwise
         */
        boolean onBackPressed();
    }
}
