package com.example.sam.PartyGames.gameFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sam.PartyGames.DataHolders.DataHolder;
import com.example.sam.PartyGames.DataHolders.NameListHolder;
import com.example.sam.PartyGames.MainActivity;
import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.adapters.FileImport;
import com.example.sam.PartyGames.navFragments.KingsGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeverHaveIEver extends Fragment {
    Button button;
    TextView view;
    TextView name;
    int i;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_never_have, container, false);
        name = (TextView) v.findViewById(R.id.NeverHaveName);
        button = (Button)v.findViewById(R.id.NeverHaveButton);
        try {
            name.setText(KingsGame.getName());
        }catch (NullPointerException e){
            name.setText("");
        }
       final FileImport fileImport = new FileImport(getContext());
       final List<String> list = new ArrayList<String>();
        fileImport.readFile("NeverHave.txt",list);
        Collections.shuffle(list);
        if(!com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsSuggestions"))
            button.setVisibility(View.GONE);
        view = (TextView)v.findViewById(R.id.NeverHaveView);
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
