package com.example.sam.PartyGames.gameFragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sam.PartyGames.DataHolders.DataHolder;
import com.example.sam.PartyGames.MainActivity;
import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.navFragments.KingsGame;


public class Waterfall extends Fragment {
    private static final String FORMAT = "%02d:%02d:%02d";
    private Button addOn;
    private TextView countDown;
    private TextView name;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.waterfall, container, false);
        MainActivity.turnOffFab();

        addOn = (Button) v.findViewById(R.id.AceButton);
        countDown = (TextView) v.findViewById(R.id.AceTextCount);
        //Log.d("WaterFall", KingsGame.getName());
        name = (TextView) v.findViewById(R.id.WaterFallName);
        name.setText(KingsGame.getName());

        if(!com.example.sam.PartyGames.DataHolders.DataHolder.retrieve("KingsAddOn"))
            addOn.setVisibility(View.GONE);

        addOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    reverseTimer(2,countDown);
            }
        });

        return v;
    }
    public void reverseTimer(int Seconds,final TextView tv){
        new CountDownTimer(Seconds*3000,1) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                seconds = seconds % 60;
                tv.setText("TIME : "+ ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                tv.setText("Start");
            }
        }.start();
    }
}
