package com.example.sam.PartyGames.SettingsFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
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
 * Activities that contain this fragment must implement the
 * {@link ArtistsSettings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArtistsSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistsSettings extends Fragment {

    public ArtistsSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArtistsSettings.
     */


        Button button;
        EditText editText;
        final String file = "Artists.txt";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_artists_settings, container, false);
            button = (Button) v.findViewById(R.id.ArtistsSettingsButton);
            editText = (EditText) v.findViewById(R.id.ArtistsSettingsEdit);
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
