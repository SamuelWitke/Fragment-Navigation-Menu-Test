package com.example.sam.PartyGames;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.sam.PartyGames.DataHolders.DataHolder;
import com.example.sam.PartyGames.DataHolders.NameListHolder;
import com.example.sam.PartyGames.NavFragMap.MapFragNavItem;
import com.example.sam.PartyGames.FloatingActionBar.FloatingActionBarNames;
import com.example.sam.PartyGames.SettingsFragments.NeverHaveSettings;
import com.example.sam.PartyGames.adapters.NavListAdapter;
import com.example.sam.PartyGames.gameFragments.Artists;
import com.example.sam.PartyGames.gameFragments.Catagories;
import com.example.sam.PartyGames.gameFragments.MostLikely;
import com.example.sam.PartyGames.gameFragments.NeverHaveIEver;
import com.example.sam.PartyGames.gameFragments.TruthOrDare;
import com.example.sam.PartyGames.navFragments.MyAbout;
import com.example.sam.PartyGames.navFragments.KingsGame;
import com.example.sam.PartyGames.navFragments.MyHome;
import com.example.sam.PartyGames.navFragments.MySettings;
import com.example.sam.PartyGames.models.NavItem;

public class MainActivity extends AppCompatActivity {
    public static Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private RelativeLayout drawerPane;
    private ListView lvNav;

    private List<NavItem> listNavItems;
    private List<Fragment> listFragments;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    public  MapFragNavItem mapFragNavItem;
    private NavListAdapter navListAdapter;
    private FragmentManager fragmentManager;

    public static FloatingActionButton myFab;


    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fragmentManager = getSupportFragmentManager();
        com.example.sam.PartyGames.DataHolders.DataHolder.save("KingsAddOn",Boolean.TRUE);
        com.example.sam.PartyGames.DataHolders.DataHolder.save("KingsSuggestions",Boolean.TRUE);



        setSupportActionBar(toolbar);

        try{
            verifyStoragePermissions(this);
        }catch (Exception e){
            Log.d("verifyStorage","Error");
        }

        myFab = (FloatingActionButton) findViewById(R.id.FAB);
        myFab.setVisibility(View.VISIBLE);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_content);
                if (fragment != null && fragment.isMenuVisible()) {
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.main_content, new FloatingActionBarNames(), "Edit Names")
                                .commit();
                    }
            }});
        initMainActivity();

        // set listener for navigation items:
        lvNav.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // replace the fragment with the selection correspondingly:

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, listFragments.get(position),listNavItems.get(position).getTitle())
                        .commit();

                toolbar.setTitle(listNavItems.get(position).getTitle());
                lvNav.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerPane);

            }
        });

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            // create listener for drawer layout
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                    R.string.drawer_opened, R.string.drawer_closed) {

                @Override
                public void onDrawerOpened(View drawerView) {
                    // TODO Auto-generated method stub
                    invalidateOptionsMenu();
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    // TODO Auto-generated method stub
                    invalidateOptionsMenu();
                    super.onDrawerClosed(drawerView);
                }

            };
        }
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the MyHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                Log.d("Case","action Settings");
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content,new MySettings())
                        .commit();

                toolbar.setTitle("Settings");
                drawerLayout.closeDrawer(drawerPane);
                return true;
            case R.id.action_cached:
                Log.d("Case","action catched");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                com.example.sam.PartyGames.DataHolders.DataHolder.Clear();
                SharedPreferences sharedPref = this.getSharedPreferences("Names",Context.MODE_PRIVATE);
                sharedPref.edit().clear().commit();
                NameListHolder.clear();
            case android.R.id.home:
                    Log.d("Case", "action bar true");
                    actionBarDrawerToggle.onOptionsItemSelected(item);
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    public void initMainActivity() {
        // Initialize Navigation Menu with corresponding fragments
        initMapFragment();
        initListNavItems();
        initListFragments();
        //
        // Initialize Navigation List
        navListAdapter = new NavListAdapter(
                getApplicationContext(), R.layout.item_nav_list, listNavItems);
        lvNav.setAdapter(navListAdapter);
        //

        // load first fragment as default:
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, listFragments.get(0)).commit();
        toolbar.setTitle(listNavItems.get(0).getTitle());
        //

        lvNav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerPane);
    }
    public void initMapFragment() {
        mapFragNavItem = new MapFragNavItem();
        mapFragNavItem.add("Home",new NavItem("Home", "Home page",
                R.drawable.ic_home_black_36dp),new MyHome());
        mapFragNavItem.add("About",new NavItem("About", "Rules",
                R.drawable.ic_info_black_36dp),new MyAbout());
        mapFragNavItem.add("Kings",new NavItem("Kings Game","Kings Full Game",R.drawable.ic_casino_black_36dp),new KingsGame());
        mapFragNavItem.add("Never Have I Ever", new NavItem("Never Have I Ever","Semi Game",1),new NeverHaveIEver());
        mapFragNavItem.add("Most Likely", new NavItem("Most Likely","Semi Game",1),new MostLikely());
        mapFragNavItem.add("Truth Or Dare", new NavItem("Truth Or Dare","Semi Game",1),new TruthOrDare());
        mapFragNavItem.add("Categories", new NavItem("Categories","Semi Game",1),new Catagories());
        mapFragNavItem.add("Artists", new NavItem("Artists","Semi Game",1),new Artists());

    }
    public void initListNavItems() {
        listNavItems = new ArrayList<NavItem>();
        listNavItems.add(mapFragNavItem.getFirst("Home"));
        listNavItems.add(mapFragNavItem.getFirst("Kings"));
        listNavItems.add(mapFragNavItem.getFirst("About"));
        listNavItems.add(mapFragNavItem.getFirst("Never Have I Ever"));
        listNavItems.add(mapFragNavItem.getFirst("Most Likely"));
        listNavItems.add(mapFragNavItem.getFirst("Truth Or Dare"));
        listNavItems.add(mapFragNavItem.getFirst("Categories"));
        listNavItems.add(mapFragNavItem.getFirst("Artists"));
    }

    public void initListFragments() {
        listFragments = new ArrayList<Fragment>();
        listFragments.add(mapFragNavItem.getSecond("Home"));
        listFragments.add(mapFragNavItem.getSecond("Kings"));
        listFragments.add(mapFragNavItem.getSecond("About"));
        listFragments.add(mapFragNavItem.getSecond("Never Have I Ever"));
        listFragments.add(mapFragNavItem.getSecond("Most Likely"));
        listFragments.add(mapFragNavItem.getSecond("Truth Or Dare"));
        listFragments.add(mapFragNavItem.getSecond("Categories"));
        listFragments.add(mapFragNavItem.getSecond("Artists"));

    }

    public static void turnOffFab(){
        myFab.setVisibility(View.GONE);
    }

}
