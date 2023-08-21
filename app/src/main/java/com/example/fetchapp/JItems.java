package com.example.fetchapp;

import com.google.gson.annotations.SerializedName;

public class JItems {
    @SerializedName("id")
    private int itemId;
    @SerializedName("listId")
    private int itemListId;
    @SerializedName("name")
    private String itemName;

    public JItems(int id, int listId, String name)
    {
        this.itemId = id;
        this.itemListId = listId;
        this.itemName = name;
    }

    public int getId()
    {
        return itemId;
    }

    public int getListId()
    {
        return itemListId;
    }

    public String getName()
    {
        if (itemName != null)
        {
            return itemName;
        }
        else
        {
            return ("");
        }
    }


}
