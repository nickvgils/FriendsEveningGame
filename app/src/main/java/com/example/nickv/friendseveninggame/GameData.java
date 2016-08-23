package com.example.nickv.friendseveninggame;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nickv on 22/08/2016.
 */
public class GameData implements Serializable{

    private ArrayList<Integer> chosenNumbers = new ArrayList<Integer>();
    private ArrayList<String> players;
    private ArrayList<Integer> bgcolors = new ArrayList<Integer>();

    public GameData(ArrayList<String> players) {
        this.players = players;
        bgcolors.add(Color.DKGRAY);
        bgcolors.add(Color.MAGENTA);
        bgcolors.add(Color.BLACK);
        bgcolors.add(Color.GRAY);
        for (int i = 0; i < 2; i++) {
            chosenNumbers.add(i);
        }
    }

    public ArrayList<Integer> getChosenNumbers()
    {
        return chosenNumbers;
    }

    public ArrayList<String> getPlayers()
    {
        return players;
    }

    public ArrayList<Integer> getBgcolors()
    {
        return bgcolors;
    }

    public void setPlayers(ArrayList<String> players){
        this.players = players;
    }

}
