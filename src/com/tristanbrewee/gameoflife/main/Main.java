package com.tristanbrewee.gameoflife.main;

import com.tristanbrewee.gameoflife.Exceptions.IncorrectNumberException;
import com.tristanbrewee.gameoflife.model.Field;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        int x = getUserInput("How wide would you like the field?");
        int y = getUserInput("How high would you like the field?");
        Field field = new Field(x,y);
        field.play();
    }

    //Gets an Integer from the user > 4
    private static int getUserInput(String message){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                System.out.println(message);
                int input = scanner.nextInt();
                if(input < 5)
                    throw new IncorrectNumberException("Please enter an Integer bigger or equal to 5. A smaller field is kind of pathetic...");
                return input;
            }
            catch(InputMismatchException e){
                System.out.println("Please enter and Integer.");
                scanner.nextLine();
            }
            catch(IncorrectNumberException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
