package com.example.sam.PartyGames.SettingsFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.sam.PartyGames.DataHolders.DataHolder;
import com.example.sam.PartyGames.R;

import java.util.Map;

/**
 * Created by sam on 12/29/16.
 */

public class KingsGameSettings extends Fragment {
    Switch toggle;
    Switch toggleSuggestion;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_kings_game_settings, container, false);
        toggle = (Switch) v.findViewById(R.id.KingsSettingAddOnButton);
        toggleSuggestion = (Switch) v.findViewById(R.id.KingsSettingSuggestions);

        // Check Switch Add On
        if(com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsAddOn"))
            toggle.setChecked(true);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    com.example.sam.PartyGames.DataHolders.DataHolder.save("KingsAddOn",Boolean.TRUE);
                    Log.d("Toogle","Set Kings Add On True");
                    toggle.setChecked(true);
                } else {
                    com.example.sam.PartyGames.DataHolders.DataHolder.save("KingsAddOn",Boolean.FALSE);
                    Log.d("Toogle","Set Kings Add On False");
                    toggle.setChecked(false);
                }
            }
        });

        // Check Switch Suggestions
        if(com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsSuggestions"))
            toggleSuggestion.setChecked(true);
        toggleSuggestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    com.example.sam.PartyGames.DataHolders.DataHolder.save("KingsSuggestions",Boolean.TRUE);
                    toggleSuggestion.setChecked(true);
                } else {
                    com.example.sam.PartyGames.DataHolders.DataHolder.save("KingsSuggestions",Boolean.FALSE);
                    toggleSuggestion.setChecked(false);
                }
            }
        });

        return v;
    }
}
