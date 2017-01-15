package com.example.sam.PartyGames.gameFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.navFragments.KingsGame;

/**
 * A simple {@link Fragment} subclass.
 */
public class Heaven extends Fragment {


    public Heaven() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_heaven, container, false);
        TextView name = (TextView) v.findViewById(R.id.HeavenName);
        name.setText(KingsGame.getName());
        return v;
    }

}
