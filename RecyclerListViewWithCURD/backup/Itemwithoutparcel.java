package com.example.mugdha.RecyclerListViewWithCURD;

import java.util.ArrayList;

public class Itemwithoutparcel {

    private String itemName;
    private String itemPrice;
    private String itemDescription;

    public Itemwithoutparcel(String itemName, String itemPrice, String itemDescription) {
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

    public static ArrayList<Itemwithoutparcel> getObjectList() {

        ArrayList<Itemwithoutparcel> listItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listItems.add(new Itemwithoutparcel("item " + i, "" + i, "description" + i));
        }
        return listItems;
    }

  /*  public Item(Parcel in){
        String[] data= new String[3];

        in.readStringArray(data);
        this.itemName= data[0];
        this.itemPrice= data[1];
        this.itemDescription= data[2];
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
    }*/
}

