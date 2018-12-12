/*
     @Name   ListManagerActivity.java
     @Author MPK
     @this activity adds and updates teh records in the list of Items
 */
package com.example.mugdha.RecyclerListViewWithCURD;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

public class ListManagerActivity extends AppCompatActivity {

    private Context context;
    private boolean toggle = true;

    //Arrays hold display text for add and udate option
    public static final String[] MENU_TITLE = {"Add New Item", "Update Item"};
    public static final String[] BUTTON_ACTION = {"Add Item", "Update"};


    ArrayList<Item> listItems;  //Array thst holds lists of Items
    Item newItem;   //The item that is passed in intent
    int position, option;       //Adapter position
    TextView tvTitle, tvName, tvPrice, tvDesc, result;
    Button btAction;
    EditText etName, etPrice, etDesc, etErrMsg;
    String newName, newPrice, newDesc;


    public ListManagerActivity( ) {
          this.context =  ListManagerActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmanager);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1); // create toolBar object
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();        //create actionBar object
        actionBar.setDisplayHomeAsUpEnabled(true);          //display home /back button

        getValuesFromTextView();                            //extract values from the activity view
        newItem = getIntents();                             //Get the data passed in Intent

        btAction.setOnClickListener(new ButtonClick(context));
    }

    //This function generates the errorcode
    public int GetErrCode(){
        int errCode = 0;

        if(etName.getText().toString().isEmpty()) { //Item Name is not entered
            errCode += 1;
        }
        if(etPrice.getText().toString().isEmpty()) { //Item Price is not entered
           errCode +=  2;
        }
        if(etDesc.getText().toString().isEmpty()){ //Item Description is entered
           errCode+=4;
        }

        return errCode;
    }



    //Get the values of the textView in local Variables
    private void getValuesFromTextView() {

        //get values from TextView and EditText
        tvTitle = (TextView) findViewById(R.id.textView_Title);
        result = (TextView) findViewById(R.id.resultView);
        etName = (EditText) findViewById(R.id.etTitle);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etDesc = (EditText) findViewById(R.id.etDescription);

        //Put Values in Strings
        newName = etName.getText().toString();

        /*Different way of checking validity of input values
        if(TextUtils.isEmpty(newName)) {
            etName.setError("You must enter Item name", );

           // return;
        }*/
        newPrice = etPrice.getText().toString();
        newDesc = etPrice.getText().toString();

        btAction = (Button) findViewById(R.id.btnPost);

    }

    public Item getIntents(){

        Intent intent = getIntent();    //Get intent

        // get the option add/updat from intent
        if(intent.hasExtra("option")) {
            option = intent.getIntExtra("option", 0);
            this.setTitle(MENU_TITLE[option - 1]);  //set menu title
            btAction.setText(BUTTON_ACTION[option - 1]); //set action button action

        }
        else        // something is wrong Option not available
            return null;

        switch (option){

            case 1: return null; // add new item
            case 2:
                    // Get Adapter/ listItem Position
                    position = intent.getIntExtra("pos", 0);

                    // get an existing item details in textView

                    Item newItem = (Item) intent.getParcelableExtra("Item");

                    //set textview to show orginal values of item passed for update
                    etName.setText(newItem.getItemName());
                    etPrice.setText(newItem.getItemPrice());
                    etDesc.setText((newItem.getItemDescription()));
                    return newItem;

            default: return null;



        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu); // r.meu.menu is menu.xml file

        // set the action_addItem to fals so it does not disply on add and update menu
        menu.findItem(R.id.action_addItem).setVisible(false);
        return true;
    }

    //This function gets the values from text view and creates new item
    public void getCurrentItem() {

        tvName = (TextView) findViewById(R.id.etTitle);
        tvPrice = (TextView) findViewById(R.id.etPrice);
        tvDesc = (TextView) findViewById(R.id.etDescription);

        String newName = tvName.getText().toString();
        String newPrice = tvPrice.getText().toString();
        String newDesc = tvDesc.getText().toString();
        newItem = new Item(newName, newPrice, newDesc);    //getCurrentItem();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }

    }

    // This is the function that is called when activity button is clicked
    public void performActionPost(View view){

        getCurrentItem();       //Create Item object to pass back in intent
        if(option==1)           //   btPost is clicked with add intent
            newPostAdded(view);
        else if(option==2)      //   btPost is clicked with update intent
            updatePost(view);

    }


    // Crate an intent to send to main Activity
    // and add new Item data to add to list
    public void newPostAdded(View view) {

       // getCurrentItem();

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("Item", newItem);
        setResult(Activity.RESULT_OK,  intent); // so onactivity result will trigger

        finish();

    }

    // Crate an intent to send to main Activity and add updated item data
    public void updatePost(View view) {

        //getCurrentItem();
        Intent intent = new Intent(this,  MainActivity.class);
        intent.putExtra("Item", newItem);
        intent.putExtra("pos", this.position);
        setResult(Activity.RESULT_OK,  intent);

        finish();

    }

    //This function displays the dialog box and displays msg as message
    public void DisplayDialog(View v, Context context, String msg){
        if(!msg.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(ListManagerActivity.this);
            builder.setTitle("Warning")
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            etName.requestFocus();
            builder.show();
         }


    }

    //this function is executed when add ot update button is clicked
     class ButtonClick implements View.OnClickListener{
         private Context context;
         private int errCode;
         public ButtonClick(Context context){
             this.context = context;
             //errCode = GetErrCode();
         }

         @Override
         public void onClick(View v) {
             Context context=getApplicationContext();  // get ApplicationContext
             errCode = GetErrCode();            //Validate imput and Generate the error code

             //Extarct the message from String array depending on the errorcode
             String[] Error_array = context.getResources().getStringArray(R.array.errorCode);
             if(errCode >0) {   //in case of error display dialogbox
                 String msg = Error_array[errCode - 1];
                 DisplayDialog(v, context, msg);
             }
             else  // if you are here then input is valid
                 performActionPost(v);

         }
     }
}

