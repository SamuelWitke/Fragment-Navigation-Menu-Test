package com.example.sam.PartyGames.navFragments;

import com.example.sam.PartyGames.MainActivity;
import com.example.sam.PartyGames.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyAbout extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about, container, false);
        MainActivity.myFab.setVisibility(View.VISIBLE);
        return v;
    }
}