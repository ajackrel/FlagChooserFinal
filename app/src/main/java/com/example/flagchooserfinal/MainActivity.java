package com.example.flagchooserfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    //XML component that holds the RecyclerView
    private RecyclerView recyclerView;
    //Adapter for the RecyclerView -- see class of same name
    private RecyclerAdapter recyclerAdapter;

    String countryName1;
    private ArrayList<CountryList> list = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the XML component
        recyclerView = findViewById(R.id.recyclerView);
        //sets the layout for the recyclerView. NOTE: There is a way to horizontal recycler views. check documentation for details
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //converts image files to a XML friendly format. AUTHOR NOTE: the country names and country
        //flag image file names do not match. Thus, the images appear, but are not matched.
        stringArrayXMLFormatter();

        //converts the XML string arrays to a Java array list
        setUpArrays();

        //creates an instance of the adapter class from this context
        recyclerAdapter = new RecyclerAdapter(list, MainActivity.this);
        //sets the adapter
        recyclerView.setAdapter(recyclerAdapter);

    }

    /*
    This method takes the String parsed by the stringArrayXMLFormatter() method and converts
    it to an Java arraylist.

    NOTE: Android Studio requires using a TypedArray for images. See below.
     */
    private void setUpArrays(){
        String[] countryNames = getResources().getStringArray(R.array.countryNames);
        String[] countryDetails = getResources().getStringArray((R.array.countryDetails));
        TypedArray countryFlag = getResources().obtainTypedArray(R.array.countryFlag);

        for (int i = 0; i < countryNames.length; i++){
            list.add(new CountryList(countryNames[i], countryDetails[i],countryFlag.getResourceId(i,0)));

        }
    }

    /*
    This method converts file names to a XML friendly format to use in the strings.xml
    file. Any file names and extensions will work. However, you will need to change the extension
    below. See comments for details.
     */
    public void stringArrayXMLFormatter() {
        String temp = null;
        try {
            //opens the file located in the assets folder
            InputStream is = getAssets().open("countryNames.txt");
            int size = is.available();
            //putting the file raw data into an array
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            //taking the buffer, converting it to a string and storing it
            temp = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //algorithm to create an XML formatted string array using the string.xml
        //if you wish to modify for your own files - change .png to another extension
        temp = temp.replaceAll("\\.png", "</item>");
        temp = temp.replace("\n", "<item>@drawable/");

        temp = temp.substring(9,temp.length());
        int hit = 0;

        for (int i = 0; i < temp.length(); i++){

            if (temp.charAt(i) == '>' || temp.charAt(i) == '<'){

                hit++;

                if (hit == 4){

                    String temp1 = temp.substring(0, i+1);
                    String temp2 = temp.substring(i+1, temp.length());
                    temp = temp1 + '\n' + temp2;

                    hit = 0;

                }
            }
        }
    }
}