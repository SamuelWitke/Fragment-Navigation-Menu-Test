package com.example.sam.PartyGames.gameFragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.navFragments.KingsGame;

public class Floor extends Fragment {
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_floor, container, false);
        name = (TextView) v.findViewById(R.id.FloorName);
        name.setText(KingsGame.getName());
        return v;
    }
}
