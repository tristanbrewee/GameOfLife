package com.tristanbrewee.gameoflife.model;

public class Cell{

    //isAlive is the current state of the cell. True for alive, false for dead.
    private boolean isAlive;
    //willLiveNextRound is the state the cell will be in the following round. True for alive, False for dead.
    private boolean willLiveNextRound;

    public Cell(boolean isAlive){
        this.isAlive = isAlive;
        willLiveNextRound = false;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public boolean getWillLiveNextRound(){
        return willLiveNextRound;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public void setWillLiveNextRound(boolean willLiveNextRound){
        this.willLiveNextRound = willLiveNextRound;
    }

    //A living cell is represented as "*", and a dead one as " " (space).
    @Override
    public String toString(){
        if(isAlive)
            return "*";
        return " ";
    }
}