/*
@Name MyAdapter.java
@author MPK
@this i the adapter to connect the activity with the data source.
 */
package com.example.mugdha.RecyclerListViewWithCURD;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private ArrayList<Item> listItems;
    private ArrayList<Item> filteredListItems;
    private Context context;
    private int result =0;
    Activity activity;

    public MyAdapter(ArrayList<Item> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        activity = (Activity) context;


    }


    public ArrayList<Item> getDataSet(){
        return listItems;
    }
    public void addItem(Item item){
        this.listItems.add(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item listItem = listItems.get(i);
        viewHolder.tvItemName.setText(listItem.getItemName());
        viewHolder.tvPrice.setText(listItem.getItemPrice());
        viewHolder.tvDescription.setText(listItem.getItemDescription());

    }



    @Override
    public int getItemCount() {
        return listItems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView tvItemName;
        public TextView tvPrice;
        public TextView tvDescription;
        public Button delButton;
        public Button updateButton;
        public int adapterPosition;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);


            delButton = (Button) itemView.findViewById(R.id.btDel);
            updateButton = (Button) itemView.findViewById(R.id.btUpdate);


            adapterPosition = getAdapterPosition();
            delButton.setOnClickListener(new View.OnClickListener() {
               int answer = 0;
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new  AlertDialog.Builder(context);
                    builder.setTitle("Confirm delete Command");
                    String mesg = listItems.get(getAdapterPosition()).getItemDescription();

                    builder.setMessage("Are Sure You want to delete "+ mesg+" ?" );
                    final EditText input = new EditText(context);

                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "You've choosen to delete all records", Toast.LENGTH_SHORT).show();

                            listItems.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), listItems.size());
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    builder.show();


                }
            });
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Item item =  listItems.get(position);

                   // CallUpdateItemBox(position);
                    Intent updateIntent = new Intent(v.getContext(), ListManagerActivity.class); //since its in view get view's context

                    updateIntent.putExtra("Item", item);
                    updateIntent.putExtra("pos", position);
                    updateIntent.putExtra("option", 2);
                   // updateIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   String s = "i am here";

                    //v.getContext().startActivity(updateIntent); //because calling from adapter

                    ((Activity) context).startActivityForResult(updateIntent,2);

                }
            });


        }

    }





}
