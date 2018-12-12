/*
@name Item.java
@Author MPK
@this class holds the items to displays. If you want to change the type of Display items change the
data here nd the xml files.
 */

package com.example.mugdha.RecyclerListViewWithCURD;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Item implements Parcelable {

    private String itemName;
    private String itemPrice;
    private String itemDescription;

    public Item(String itemName, String itemPrice, String itemDescription) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public static ArrayList<Item> getObjectList() {

        ArrayList<Item> listItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listItems.add(new Item("item " + i, "" + i, "description" + i));
        }
        return listItems;
    }

    public Item(Parcel in){

       // Item newItem = new Item(in.readString(), in.readString(), in.readString());
        this.itemName =in.readString();
        this.itemPrice = in.readString();
        this.itemDescription =in.readString();
        /*String[] data= new String[3];
        in.readStringArray(data);
        this.itemName= data[0];
        this.itemPrice= data[1];
        this.itemDescription= data[2];*/
        //this.itemDescription= Integer.parseInt(data[2]); this is how you will pass integer

    }
    public static final Parcelable.Creator<Item> CREATOR= new Parcelable.Creator<Item>() {

        @Override
        public Item createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new Item(source);  //using parcelable constructor
        }

        @Override
        public Item[] newArray(int size) {
        // TODO Auto-generated method stub
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeStringArray(new String[]{this.itemName,this.itemPrice, this.itemDescription});
        dest.writeString(this.itemName);
        dest.writeString(this.itemPrice);
        dest.writeString(this.itemDescription);
    }
}

