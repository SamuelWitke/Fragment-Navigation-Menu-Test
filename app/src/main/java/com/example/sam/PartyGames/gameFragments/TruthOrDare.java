package com.example.sam.PartyGames.gameFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.adapters.FileImport;
import com.example.sam.PartyGames.navFragments.KingsGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TruthOrDare extends Fragment {
    TextView name;
    TextView view;
    Button buttonTruth;
    Button buttonDare;
    int i,j;

    public TruthOrDare() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_truth_or_dare, container, false);
        name = (TextView) v.findViewById(R.id.TDName);
        view = (TextView)v.findViewById(R.id.TDView);

        try {
            name.setText(KingsGame.getName());
        }catch (NullPointerException e){
            name.setText("");
        }

        buttonTruth = (Button) v.findViewById(R.id.TruthButton);
        buttonDare = (Button) v.findViewById(R.id.DareButton);
        if(!com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsSuggestions")) {
            buttonTruth.setVisibility(View.GONE);
            buttonDare.setVisibility(View.GONE);
        }
        i=0;
        j=0;
        final FileImport fileImport = new FileImport(getContext());
        final List<String> list = new ArrayList<String>();
        fileImport.readFile("Truth.txt",list);
        Collections.shuffle(list);
        buttonTruth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText(list.get(i));
                if(i != list.size() -1)
                    i++;
                else
                    i=0;
            }
        });
        final FileImport fileImport2 = new FileImport(getContext());
        final List<String> list2 = new ArrayList<String>();
        fileImport2.readFile("Dare.txt",list2);
        Collections.shuffle(list2);
        buttonDare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText(list2.get(j));
                if(j != list2.size() -1)
                    j++;
                else
                    j=0;
            }
        });
        return v;
    }

}
