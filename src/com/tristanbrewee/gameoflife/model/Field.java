package com.tristanbrewee.gameoflife.model;

public class Field{

    //cells is a 2D-array of Cells (same package). These represent the field.
    private Cell[][] cells;

    //Defines the size of the field and initializes the individual Cell's.
    public Field(int x, int y){
        cells = new Cell[y][x];
        randomInitialize();
    }

    //Keeps on playing rounds as long as there are living cells.
    public void play(){
        while(cellsStillAlive()){
            printField();
            checkCells();
            updateCells();
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("Sleep failed... ");
            }
        }
        System.out.println("All cells are dead...");
    }

    //Checks if the cells are still alive. True if at least 1 Cell is still alive. False if none are.
    private boolean cellsStillAlive(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                if(cells[i][j].getIsAlive())
                    return true;
            }
        }
        return false;
    }

    //Prepares all cells for the next round. isAlive becomes the value of willLiveNextRound, and willLiveNextRound is false.
    private void updateCells(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j].setIsAlive(cells[i][j].getWillLiveNextRound());
                cells[i][j].setWillLiveNextRound(false);
            }
        }
    }

    //Checks each Cell for what value willLiveNextRound should be.
    //The number of neighbors is calculated.
    //If a cell has <2 or >3 neighbors it will die.
    //If a cell isAlive and 2 or 3 neighbors it will live.
    //If a cell !isAlive and has 3 neighbors it will live.
    //Else it will (remain) dead.
    private void checkCells(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                int neighbors = getLivingNeighbors(j, i);
                if(neighbors < 2 || neighbors > 3)
                    cells[i][j].setWillLiveNextRound(false);
                else if(cells[i][j].getIsAlive())
                    cells[i][j].setWillLiveNextRound(true);
                else if(!cells[i][j].getIsAlive() && neighbors == 3)
                    cells[i][j].setWillLiveNextRound(true);
            }
        }
    }

    //Check how many neighbors a cell at given index has (x-axis, y-axis)(j, i, in a standard for loop)
    //It stops at 4 cause a cell dies as soon as it has 4 or more neighbors.
    private int getLivingNeighbors(int x, int y){
        int neighbors = 0;
        for(int i = y-1; i <= y+1; i++){
            for(int j = x-1; j <= x+1; j++){
                if(i == y && j == x)
                    continue;
                try{
                    if(cells[i][j].getIsAlive())
                        neighbors++;
                }
                catch(IndexOutOfBoundsException e){ //When x or y are at the edge of the field, the program will check outsode of the bounds.
                    continue;
                }
                if(neighbors > 3)
                    return neighbors;
            }
        }
        return neighbors;
    }

    //Populates cells with actual Cell-objects. isAlive is chosen by random.
    private void randomInitialize(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                int choice = (int)(Math.random() * 2);
                if (choice == 0)
                    cells[i][j] = new Cell(true);
                else
                    cells[i][j] = new Cell(false);
            }
        }
    }

    //Prints the cells to the console as a grid, followed by a separation-line, which is the size of the width of the field.
    private void printField(){
        System.out.println(this);
        StringBuilder separation = new StringBuilder();
        for(int i = 0; i < cells[0].length; i++)
            separation.append("-");
        System.out.println(separation);
    }

    //Returns a String with the cells in the Field. Each line is split with a "\n" (new line).
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                output.append(cells[i][j]);
            }
            output.append("\n");
        }
        return output.toString();
    }
}