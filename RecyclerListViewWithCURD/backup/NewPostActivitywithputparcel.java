package com.example.mugdha.RecyclerListViewWithCURD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewPostActivitywithputparcel extends AppCompatActivity {


    //BooksHelper mDbHelper = new BooksHelper(this);
    ArrayList<Item> listItems;
    Item newItem;
    TextView tvName, tvPrice, tvDesc;
    String newName, newPrice, newDesc;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1); // create toolBar object
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar(); //create actionBar object
        actionBar.setDisplayHomeAsUpEnabled(true); //display home /back button

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu); // r.meu.menu is menu.xml file
        return true;
    }

    public void getCurrentItem() {

        tvName = (TextView) findViewById(R.id.etTitle);
        tvPrice = (TextView) findViewById(R.id.etPrice);
        tvDesc = (TextView) findViewById(R.id.etDescription);

        String newName = tvName.getText().toString();
        String newPrice = tvPrice.getText().toString();
        String newDesc = tvDesc.getText().toString();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_favorite:


                  Intent intent = new Intent(this, BrowsePostsActivity.class);
                tvName = (TextView) findViewById(R.id.etTitle);
                tvPrice = (TextView) findViewById(R.id.etPrice);
                tvDesc = (TextView) findViewById(R.id.etDescription);

                String newName = tvName.getText().toString();
                String newPrice = tvPrice.getText().toString();
                String newDesc = tvDesc.getText().toString();
                newItem = new Item(newName, newPrice, newDesc);//getCurrentItem();
                String s = newName + " " + newPrice + " " + newDesc;
                String s1 = newItem.getItemName() + " " + newItem.getItemPrice() + " " + newItem.getItemDescription();
                Toast.makeText(this, s,Toast.LENGTH_LONG).show();
                intent.putExtra("name", newItem.getItemName());
                intent.putExtra("price", newItem.getItemPrice());
                intent.putExtra("desc", newItem.getItemDescription());

                setResult(Activity.RESULT_OK, intent);
                finish();
            default :
                return super.onOptionsItemSelected(item);
        }

    }

    public void newPostAdded(View view) {


    }
}

