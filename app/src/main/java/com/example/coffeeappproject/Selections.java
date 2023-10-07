package com.example.coffeeappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Selections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);
        

        ListView listView = findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        list.add("Latte $1.00");
        list.add("Mocha $2.25");
        list.add("Cappuccino $3.50");




        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplication(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //clicked Coffee
                }else if(position==1){

                }else{

                }
            }
        });
    }
}