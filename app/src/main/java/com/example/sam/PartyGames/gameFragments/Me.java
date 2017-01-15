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
public class Me extends Fragment {
    TextView name;

    public Me() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_me, container, false);
        name = (TextView) v.findViewById(R.id.MeName);
        name.setText(KingsGame.getName());
        return v;
    }

}
