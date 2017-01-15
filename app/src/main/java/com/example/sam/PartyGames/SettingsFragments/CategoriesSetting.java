package com.example.sam.PartyGames.SettingsFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sam.PartyGames.R;
import com.example.sam.PartyGames.adapters.FileImport;

import java.util.Vector;

/**
 * Created by sam on 1/7/17.
 */

public class CategoriesSetting extends Fragment{
    Button button;
    EditText editText;
    final String file = "Categories.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_categories_settings, container, false);
        button = (Button) v.findViewById(R.id.CategoriesSettingsButton);
        editText = (EditText) v.findViewById(R.id.CategoriesSettingsEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = editText.getText().toString();
                final FileImport fileImport = new FileImport(getContext());
                Vector<String> list = new Vector<String>();
                fileImport.readFile(file,list);
                if(list.contains(message)){
                    new AlertDialog.Builder(getContext())
                            .setTitle("Entry Already In File")
                            .setMessage("Are you sure you want to add this this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fileImport.writeFile(file,message);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else
                    new AlertDialog.Builder(getContext())
                            .setTitle("Are you sure you want to add this this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fileImport.writeFile(file,message);
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
