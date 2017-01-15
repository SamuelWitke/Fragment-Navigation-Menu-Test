package com.example.sam.PartyGames.FloatingActionBar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sam.PartyGames.DataHolders.NameListHolder;
import com.example.sam.PartyGames.MainActivity;
import com.example.sam.PartyGames.R;

/**
 * Created by sam on 12/30/16.
 */

public class  FloatingActionBarNames extends Fragment{
    Button button;
    EditText editText;
    Integer i;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_name, container, false);
        button = (Button) v.findViewById(R.id.nameSave);
        editText = (EditText) v.findViewById(R.id.nameEditText);
        i= 0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Names",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(i.toString(),msg);
                Log.d(i.toString(),msg);
                editor.commit();
                i++;
                Toast toast = Toast.makeText(getContext(),"Player "+msg+" Added", Toast.LENGTH_SHORT);
                toast.show();
                editText.setText("");
            }
        });
        return v;
    }

}
