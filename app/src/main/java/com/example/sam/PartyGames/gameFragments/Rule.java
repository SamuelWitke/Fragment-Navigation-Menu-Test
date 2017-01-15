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
public class Rule extends Fragment {
    Button button;
    TextView view;
    TextView name;
    int i;

    public Rule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rule, container, false);
        name = (TextView) v.findViewById(R.id.RuleName);
        view = (TextView)v.findViewById(R.id.RuleView);

        name.setText(KingsGame.getName());

        button = (Button)v.findViewById(R.id.RuleButton);
        if(!com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsSuggestions"))
            button.setVisibility(View.GONE);
        final FileImport fileImport = new FileImport(getContext());
        final List<String> list = new ArrayList<String>();
        fileImport.readFile("Rule.txt",list);
        Collections.shuffle(list);
        i=0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText(list.get(i));
                if(i != list.size() -1)
                    i++;
                else
                    i=0;
            }
        });
        return v;
    }

}
