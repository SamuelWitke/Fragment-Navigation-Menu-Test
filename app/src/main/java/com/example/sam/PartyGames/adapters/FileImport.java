package com.example.sam.PartyGames.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by sam on 12/31/16.
 */

public class FileImport {
    public static Context mContext;
    public FileImport(Context context) {
        mContext = context;
    }
    public void readFile(String fileName,List<String> items){
    	File file = new java.io.File(Environment
        	.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
			+ fileName);
        FileInputStream is=null;
        BufferedReader reader;
        InputStream in=null;
        try {
            if (!file.exists()) {
                AssetManager am = mContext.getAssets();
                in = am.open(fileName);
                reader = new BufferedReader(new InputStreamReader(in));
            }else{
                is = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(is));
            }
            String line;
            while ((line = reader.readLine()) != null)
            	items.add(line);
            if(is != null && in != null) {
                is.close();
                in.close();
                reader.close();
            }
            } catch (IOException e) {
                Log.d("Read File", "IO Error");
                e.printStackTrace();
        }
    }
    private void copyAssets(String filename) {
        AssetManager assetManager = mContext.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            File file = new java.io.File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + filename);
            out = new FileOutputStream(file);
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            Log.e("tag", "Failed to copy asset file: " + filename, e);
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
    public void writeFile(String fileName,String data) {
        FileWriter outputStream;
        Log.d("Write File "+fileName, data);
        try {
            copyAssets(fileName);
        }catch (Exception e){
            Log.d("Error in copying file,","Ep");
        }
        String file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getAbsolutePath() + fileName;
        try {
            outputStream = new FileWriter(file,true);
            outputStream.write(data);
            outputStream.close();
            Log.d("Write File Comp"+fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
