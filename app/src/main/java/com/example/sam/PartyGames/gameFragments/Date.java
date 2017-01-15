package com.example.sam.PartyGames.gameFragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sam.PartyGames.DataHolders.DataHolder;
import com.example.sam.PartyGames.DataHolders.NameListHolder;
import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.adapters.NavListAdapter;
import com.example.sam.PartyGames.models.NavItem;
import com.example.sam.PartyGames.navFragments.KingsGame;

import org.w3c.dom.NameList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * A simple {@link Fragment} subclass.
 */
public class Date extends Fragment {
    private List<String> values;
    private Button names;
    private ListView listView;
    private TextView name;
    public Date() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_date, container, false);
        listView = (ListView) v.findViewById(R.id.date_list);
        names = (Button) v.findViewById(R.id.dateNames);
        name = (TextView) v.findViewById(R.id.DateName);
        final String tempName=KingsGame.getName();
        name.setText(tempName);
        values = NameListHolder.getNames();

        final List<String> temp = new ArrayList<>(values);
        for (Iterator<String> iterator = temp.iterator(); iterator.hasNext(); ) {
            String value = iterator.next();
            if (value.equals(tempName)){
                iterator.remove();
            }
        }
        for(int i=0;i<temp.size();i++){
            if(temp.get(i).contains("-")){
                int z = temp.get(i).indexOf("-");
                temp.set(i,temp.get(i).substring(0,z));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1,temp);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listView.getChildCount(); i++) {
                    if(position == i )
                        listView.getChildAt(i).setBackgroundColor(0xcc0022cc);
                    else
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                final int pos = position;
                names.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String content  = ( String) listView.getItemAtPosition(pos);
                        NameListHolder.replaceName(tempName,content);
                        Toast.makeText(getContext(), String.format("<%s><%s>",tempName, content), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return v;
    }

}
