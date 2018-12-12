package com.example.mugdha.RecyclerListViewWithCURD;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private Item newItem;                   // to hols new item
    private ArrayList<Item> listItems;      // List to hold the items


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1); // create toolBar object
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar(); //create actionBar object
        actionBar.setDisplayHomeAsUpEnabled(true); //display home /back button

        this.setTitle("Browse List");             // Set the title to the Activity
        recyclerView = findViewById(R.id.myRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(Item.getObjectList(), this);   //fill the adapter with th data
        recyclerView.setAdapter(adapter);   // connect the adapter with recyclerView
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu); // r.meu.menu is menu.xml file
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_addItem:  // Respond to the action + button

                Intent intent = new Intent(this, ListManagerActivity.class); // create intent to send to acitvity
                intent.putExtra("option", 1); //put option 1 to add new record

                //start activity for result so record can be added when return to this activity
                startActivityForResult(intent, 1);

                break;

        }
      /*  startActivity(new Intent(getApplicationContext(), NewBooksActivity.class));
        //return true; */
        return super.onOptionsItemSelected(item);
    }


    // this function is called when the control come back from the activity started with start for result Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE 1 for add recrd and 2 for update record
       super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK ) { // check id result is ok

            // Extract object from result extras
            // Make sure the key here matches the one specified in the result passed from ActivityTwo.java
            Item newItem = (Item) data.getParcelableExtra("Item");

            // add the data to the adapter
            if (adapter instanceof MyAdapter) { // get to myAdapter function through recyclerview.adapter
                ((MyAdapter) adapter).addItem(newItem);
            }

            adapter.notifyDataSetChanged();
        }

        if (requestCode == 2 && resultCode == RESULT_OK ) {
            Item newItem = (Item) data.getParcelableExtra("Item");
            int position =0;
            position = data.getIntExtra("pos", 0); //get the position of the adapter


            if (adapter instanceof MyAdapter) { // get to myAdapter function through recyclerview.adapter
               MyAdapter adpt = ((MyAdapter) adapter);

               // update the item details with new details
               Item item =  adpt.getDataSet().get(position);
               item.setItemName(newItem.getItemName());
               item.setItemPrice(newItem.getItemPrice());
               item.setItemDescription(newItem.getItemDescription());
            }

            adapter.notifyDataSetChanged();
        }


    }
    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }




/*
    public void newPostAdded(View view){
        Intent myIntent = new Intent(getBaseContext(),  ListManagerActivity.class);
        //String title = etTitle.getText().toString();
       // myIntent.putExtra("value1", "Here you go");//add the date to be passed
        startActivity(myIntent);
    }
*/

}
