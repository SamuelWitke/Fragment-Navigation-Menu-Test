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
import android.widget.Toast;

import com.example.sam.PartyGames.DataHolders.DataHolder;
import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.adapters.FileImport;
import com.example.sam.PartyGames.navFragments.KingsGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by sam on 1/7/17.
 */

public class MostLikely extends Fragment {
    Button button;
    TextView view;
    TextView name;
    TextView players;
    List<String> names;
    TextView placeHolder;
    int i;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_most_likely, container, false);
        name = (TextView) v.findViewById(R.id.MostLikelyName);
        players = (TextView) v.findViewById(R.id.MostLikelyPlayers);
        view = (TextView)v.findViewById(R.id.MostLikelyView);
        placeHolder = (TextView) v.findViewById(R.id.MostLikelyPlaceHolder);
        placeHolder.setVisibility(View.GONE);
        try {
            name.setText(KingsGame.getName());
        }catch (NullPointerException e){
            name.setText("");
        }
        button = (Button)v.findViewById(R.id.MostLikelyButton);
        names = new ArrayList();
        final FileImport fileImport = new FileImport(getContext());
        final List<String> list = new ArrayList<String>();
        fileImport.readFile("MostLikely.txt",list);
        Collections.shuffle(list);
        if(!com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsSuggestions"))
            button.setVisibility(View.GONE);
        i=0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeHolder.setVisibility(View.VISIBLE);
                players.setText(getNames());
                names.clear();
                view.setText(list.get(i));
                if(i != list.size() -1)
                    i++;
                else
                    i=0;
            }
        });
        return v;
    }
    public String getNames(){
        String name="";
        Map<String,?> keys = getContext().getSharedPreferences("Names",getContext().MODE_PRIVATE).getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()) {
            names.add(entry.getValue().toString());
        }
        Collections.shuffle(names);
        Random rand = new Random();
        if(names.size()<2) {
            Toast toast = Toast.makeText(getContext(),"Add More Than 2 Players", Toast.LENGTH_SHORT);
            toast.show();
            return "";
        }
        int  n = rand.nextInt(names.size()-1) + 2;
        for(int i=0;i<n;i++)
            name += names.get(i) +' ';
        return name;
    }
}
