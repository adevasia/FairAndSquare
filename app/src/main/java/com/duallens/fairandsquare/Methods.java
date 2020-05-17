package com.duallens.fairandsquare;

//Used for pickMethod and DetailsActivity
public class Methods {
    private int mId;
    private String mName;
    private String mDescription;
    private String mGame;

    public Methods() {}

    public Methods(int id, String name, String description, String game) {
        mId = id;
        mName = name;
        mDescription = description;
        mGame = game;
    }

    //Gets the array position for the methodList
    public int getId() {
        return mId;
    }

    //Sets the Id
    public void setId(int id) {
        this.mId = id;
    }

    //Gets the actual name of the requested choosing method
    public String getName() {
        return mName;
    }

    //Sets the name to the actual name
    public void setName(String name) {
        this.mName = name;
    }

    //Gets the "how to" string array for the given choosing method
    public String getDescription() {
        return mDescription;
    }

    //Gets the "game" string array for the given choosing method
    public String getGame() {
        return mGame;
    }
/*
    public void setDescription(String description) {
        this.mDescription = description;
    }*/
}
