package com.example.sam.PartyGames.gameFragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.adapters.FileImport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Artists extends Fragment {


    public Artists() {
        // Required empty public constructor
    }


    Button button;
    Button buttonTime;
    TextView viewArt;
    TextView count;

    int i;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artists, container, false);
        viewArt = (TextView)v.findViewById(R.id.ArtistView);
        count = (TextView)v.findViewById(R.id.ArtistTextCount);
        button = (Button) v.findViewById(R.id.ArtistButton);
        buttonTime = (Button) v.findViewById(R.id.buttonArtists);
        buttonTime.setVisibility(View.GONE);
        if(!com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsSuggestions"))
            button.setVisibility(View.GONE);
        final FileImport fileImport = new FileImport(getContext());
        final List<String> list = new ArrayList<String>();
        fileImport.readFile("Artists.txt",list);
        Collections.shuffle(list);
        i=0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewArt.setVisibility(View.VISIBLE);
                viewArt.setText(list.get(i));
                if(i != list.size() -1)
                    i++;
                else
                    i=0;
                buttonTime.setVisibility(View.VISIBLE);
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewArt.setVisibility(View.GONE);
                count.setVisibility(View.VISIBLE);
                reverseTimer(3,count);
            }
        });
        return v;
    }

    public void reverseTimer(int Seconds,final TextView tv){
        new CountDownTimer(Seconds*10000,1) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                seconds = seconds % 60;
                tv.setText("TIME : "+ ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                tv.setText("Drink");
            }
        }.start();
    }
}
