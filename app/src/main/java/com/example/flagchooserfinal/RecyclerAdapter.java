package com.example.flagchooserfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

/*
RecyclerAdapter "bind" values/data stored in memory to the visual components we see on screen.
In other words, it's the glue between JAVA and XML. More accurately, it's a translation layer.
As you can, this class extends from a class called CountryViewHolder (the class we create below)
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder>{

    int index = 0;
    String selectionNames;
    private Context context;
    //ArrayList of that holds all data fields
    private ArrayList<CountryList> list = new ArrayList<>();
    private ArrayList<String>selectionList = new ArrayList<>();
    ArrayList<CountryList> chosenList = new ArrayList<>();

    //Constructor
    public RecyclerAdapter(ArrayList list, Context context) {
        this.list = list;
        this.context = context;

    }

    /*
    grabs the views from our card_design.xml layout file
    similar to onCreate() methods - initializes the views - connects the Java and XML sides together
     */
    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCountryName, textViewDetails;
        private ImageView imageView;
        private CardView cardView;
        private CheckBox checkBox;
        public  CountryViewHolder(@NonNull View itemView){
            super(itemView);

            textViewCountryName = itemView.findViewById(R.id.textViewCountryName);
            textViewDetails = itemView.findViewById(R.id.textViewDetail);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);
            checkBox = itemView.findViewById(R.id.checkBox);


        }

    }

    @NonNull
    @Override
    //this where you inflate ("draw" the view on screen) the layout
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new CountryViewHolder((view));
        //return null;
    }

    @Override
    //assigning values/data to the views we created in the card_design.xml layout file
    //based on position (row)
    public void onBindViewHolder(@NonNull CountryViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.textViewCountryName.setText(list.get(position).getCountryName());
        holder.textViewDetails.setText(list.get(position).getCountryDetails());
        holder.imageView.setImageResource(list.get(position).getImage());

        holder.checkBox.setChecked(list.get(position).isChecked());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                if (checked == true){
                    list.get(position).setChecked(true);
                }
                else{
                    list.get(position).setChecked(false);
                }

                for (int i = 0; i < list.size(); i++){
                    System.out.println(list.get(i).toString());
                }


                notifyDataSetChanged();

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //if the CardView is clicked by the user, the checkbox will get filled
                holder.checkBox.setChecked(true);
                //retrieve the CountryList object at that index and add it to chosenList
                chosenList.add(index, list.get(holder.getAdapterPosition()));
                //increment the index so we're not overwriting anything
                index++;

                //if user selects 5 countries.
                //Authors note: I originally wanted to include a 'confirm' button but I couldn't easily find an easy way to implement that!
                if (index >= 5){

                    //for this example, the author has used an ArrayList and intented it to another
                    //activity.
                    //See the data model class (CountryList.java) to see the implementation
                    Intent intent = new Intent(context, ChosenActivity.class);
                    //this is the "secret sauce" to intenting ArrayLists. Again, check CountryList.java for details.
                    intent.putParcelableArrayListExtra("Bundle", chosenList);
                    //start the activity
                    context.startActivity(intent);

                }
            }
        });

    }

    @Override
    //recycler view wants to know how many items(rows) will be displayed
    public int getItemCount() {
        return list.size();
    }


}
