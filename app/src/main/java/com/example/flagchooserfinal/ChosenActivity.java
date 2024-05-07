package com.example.flagchooserfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChosenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen);

System.out.println("here");

        button = findViewById(R.id.button2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChosenActivity.this));

        //get the intent
        Intent intent = getIntent();
        //retrieve the ArrayList, cast it, and store it a new ArrayList
        ArrayList<CountryList> list = (ArrayList<CountryList>) intent.getSerializableExtra("Bundle");
        //just a debug message to verify data is all good!
        System.out.println("list is: " +list.get(0).toString());

        //create a new instance of the RecyclerAdapter used previously, but now we're feeding it a different ArrayList
        recyclerAdapter = new RecyclerAdapter(list, ChosenActivity.this);
        //set the adapter
        recyclerView.setAdapter(recyclerAdapter);

        //back button - goes back to Main
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}