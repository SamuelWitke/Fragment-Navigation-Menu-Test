package com.example.sam.PartyGames.SettingsFragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.adapters.FileImport;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 */
public class RuleSettings extends Fragment {

    Button button;
    EditText editText;
    final String file = "Rule.txt";

    public RuleSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rule_settings, container, false);
        button = (Button) v.findViewById(R.id.RuleSettingsButton);
        editText = (EditText) v.findViewById(R.id.RuleSettingsEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<String> list = new Vector<String>();
                final FileImport fileImport = new FileImport(getContext());
                fileImport.readFile(file, list);
                final String message = editText.getText().toString();
                if (list.contains(message)) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Entry Already In File")
                            .setMessage("Are you sure you want to add this this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fileImport.writeFile(file, message);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else
                    new AlertDialog.Builder(getContext())
                            .setTitle("Are you sure you want to add this this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fileImport.writeFile(file, message);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

            }

        });
        return v;
    }
}

