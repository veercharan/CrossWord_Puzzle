/*
    Programmer - Charanvir Singh
    Date - 03/07/2022
    Purpose - This program will create a cross word puzzle
    This class has the main method and the switch case that
    call the other methods in it
    this program will ask the user what they want to do
    if they enter 'g' then it will ask how many words they would
    like to enter (there is a try catch if they enter a String instead of an int it starts over)
    then it asks the user the word they want to add to the crossword puzzle
    if the word is too big it will give a warning and ask the user to enter again.
    then after the words are added it presents the main menu again
    if the user says 'p', it prints out the puzzle with random character added to the puzzle
    at places where there is no character and if the users says 's' the program prints out the
    puzzle with only the user-enter word and no random characters
    if 'q' is entered the program quits the game
 */

package CS_Crosswords;//package the classes together

import java.util.Scanner;

public class WordSearchGenerator {//CrosswordsTest class begins

    public static void main(String[] args) {//main method begins
        runWordSearchGenerator(20);
    }//end of the main method

    public static void printIntro()
    {//printIntro begins
        //this method has simple print statements for the Intro
        System.out.println("Please select an option");
        System.out.println("Generate a new word search (g)");
        System.out.println("Print out your word search (p)");
        System.out.println("Show the solution to your word search (s)");
        System.out.println("Quit the program (q)");
    }//end of the printIntro method

    public static void runWordSearchGenerator(int size){//runWordSearchGenerator starts
        System.out.println("Welcome to my word search generator!");
        System.out.println("This program will allow you to generate your own word search puzzle");

        CrosswordGame game = new CrosswordGame(size);
        String choice;
        Scanner input = new Scanner(System.in);

        do {//loop though as long as the input is not "q"
            printIntro();//print the intro
            choice = input.next();
            //covert the input to lowercase
            choice = choice.toLowerCase();
            switch (choice) {//switch case begins
                case "g":
                    game.generate();
                    break;
                case "p":
                    game.printWord();
                    break;
                case "s":
                    game.displaySolution();
                    break;
                case "q":
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");

            }//end of switch case
        } while (!choice.equals("q"));//end of the do while loop
    }//end of doStuff method

}//end of the CrosswordsTest class
