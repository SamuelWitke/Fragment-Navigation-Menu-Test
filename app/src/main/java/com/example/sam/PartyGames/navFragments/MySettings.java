package com.example.sam.PartyGames.navFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sam.PartyGames.MainActivity;
import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.SettingsFragments.ArtistsSettings;
import com.example.sam.PartyGames.SettingsFragments.CategoriesSetting;
import com.example.sam.PartyGames.SettingsFragments.DareSettings;
import com.example.sam.PartyGames.SettingsFragments.KingsGameSettings;
import com.example.sam.PartyGames.SettingsFragments.MostlikelySettings;
import com.example.sam.PartyGames.SettingsFragments.NeverHaveSettings;
import com.example.sam.PartyGames.SettingsFragments.RhymeSettings;
import com.example.sam.PartyGames.SettingsFragments.RuleSettings;
import com.example.sam.PartyGames.SettingsFragments.TruthSettings;
import com.example.sam.PartyGames.adapters.NavListAdapter;
import com.example.sam.PartyGames.gameFragments.Catagories;
import com.example.sam.PartyGames.gameFragments.MostLikely;
import com.example.sam.PartyGames.models.NavItem;

import java.util.ArrayList;
import java.util.List;


public class MySettings extends Fragment {
    private List<NavItem> listNavItems;
    private ListView lvNav;
    private List<Fragment> listFragments;
    private NavListAdapter navListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        MainActivity.myFab.setVisibility(View.GONE);
        initList();
        lvNav = (ListView) v.findViewById(R.id.settings_list);

        // Initialize Navigation List
        navListAdapter = new NavListAdapter(
                getActivity().getApplicationContext(), R.layout.item_nav_list, listNavItems);
        lvNav.setAdapter(navListAdapter);
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // replace the fragment with the selection correspondingly:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, listFragments.get(position))
                        .commit();

                lvNav.setItemChecked(position, true);
            }
        });
        return v;
    }
    void initList(){
        listNavItems = new ArrayList<NavItem>();
        listFragments = new ArrayList<Fragment>();
        listFragments.add(new KingsGameSettings());
        listNavItems.add(new NavItem("Kings Game Settings","Kings Game Settings",0));
        listFragments.add(new NeverHaveSettings());
        listNavItems.add(new NavItem("Never Have I Ever Settings","Add Entries To File",0));
        listFragments.add(new CategoriesSetting());
        listNavItems.add(new NavItem("Categories Settings","Add Entries To File",0));
        listFragments.add(new MostlikelySettings());
        listNavItems.add(new NavItem("Most Likely Settings","Add Entries To File",0));
        listFragments.add(new DareSettings());
        listNavItems.add(new NavItem("Dare Settings","Add Entries To File",0));
        listFragments.add(new TruthSettings());
        listNavItems.add(new NavItem("Truth Settings","Add Entries To File",0));
        listFragments.add(new RuleSettings());
        listNavItems.add(new NavItem("Rule Settings","Add Entries To File",0));
        listFragments.add(new RhymeSettings());
        listNavItems.add(new NavItem("Rhyme Settings","Add Entries To File",0));
        listFragments.add(new ArtistsSettings());
        listNavItems.add(new NavItem("Artists Settings","Add Entries To File",0));
    }
}
