/*
this class contains all the methods called in the main method
*/
package CS_Crosswords;//package with CrosswordsTest class

import java.util.*;

public class CrosswordGame
{//Crossword_game begins
    //ArrayList that will have user input words
    private ArrayList<String> list;
    //2D array with the Random characters added
    private char[][] wordSearch;
    //2D array with only the user added words
    private char[][] solution;
    //the size of the grid
    private int size;

    public CrosswordGame(int size)
    {//constructor begins
        this.size = size;
        list = new ArrayList<>();
        wordSearch = new char[size][size];
        solution = new char[size][size];

        //this for loop fills up both 2D arrays with "_"
        for(int i = 0; i < size; i++)
        {//end of the inner for loop
            for(int j = 0; j < size;j++)
            {//end of the inner for loop
                wordSearch[i][j]= '_';
                solution[i][j]= '_';
            }//end of the inner for loop
        }//end of the outer for loop

    }//end of the constructor

    public void generate()
    {//generate method begins
        Scanner input = new Scanner(System.in);
        String userInput;
        String totalWords;
        int numOfWords = 0;
        System.out.println("How many words do you want to add? ");
        totalWords = input.nextLine();

        try {
            //do something
            numOfWords = Integer.parseInt(totalWords);
        } catch(Exception e) {
            System.out.println("Not a valid input\n"); //this will be printed out for non-int inputs

        }
        //this for loop adds user entered words to the  arraylist totalWords times
        for(int i = 0; i < numOfWords; i++)
        {
            System.out.println("Enter the word: ");
            userInput = input.next();
            //this if else statement checks if the size of the word enter is too long
            if(userInput.length() > size){
                //this error/warning is shown to the user
                System.out.print("Word too long, Please try another word!");
            }
            else {
                //add to the arrayList
                list.add(userInput.toLowerCase());
            }
        }
        //sort the list so that longer words are in the front
        list.sort((a, b) -> (b.length() - a.length()));
        createGrid(list);
    }//end of the generate method

    private void createGrid(List<String> list)
    {//createGrid method begins
        //for x and y coordinates
        int xcod;
        int ycod;
        //for random direction
        int direction;
        String userword;
        //the random number generator
        Random rand = new Random();
        //these will allow the program to try again and again if the word doesn't fit the first time
        int maxgenerate = 100;
        int maxtry = 0;

        while (maxgenerate != 0 && maxtry == 0) {
            maxtry = 1000;
            for(int i = 0; i < list.size() && maxtry != 0; i++)
            {
                userword = list.get(i);
                //randomly generate the coordinates for the word to print
                xcod = rand.nextInt(size);
                ycod = rand.nextInt(size);
                //random number for the direction of the words
                direction = rand.nextInt(3);
                //if the word doesn't fit, try again
                if(!placeWord(userword, xcod, ycod, direction)) {
                    i--;
                    maxtry--;
                }
                else {
                    maxtry = 1000;
                }
            }
            if(maxtry == 0) {
                maxgenerate--;
            }
        }
        //if after many tries the puzzle can't fit word, it will reset the puzzle and print an error
        if(maxgenerate == 0) {
            for(int i = 0; i < size; i++)
            {//end of the inner for loop
                for(int j = 0; j < size;j++)
                {//end of the inner for loop
                    wordSearch[i][j]= '_';
                    solution[i][j]= '_';
                }//end of the inner for loop
            }//end of the outer for loop
            System.out.println("Cannot fit the words in the puzzle! Puzzle reset!");
        }
        //otherwise it will and random chars to the places where the values in '_' in the wordSearch array only
        else {
            for(int i = 0; i < size; i++)
            {//end of the inner for loop
                for(int j = 0; j < size;j++)
                {//end of the inner for loop
                    if(wordSearch[i][j] == '_')
                    {
                        wordSearch[i][j] = (char) (rand.nextInt(26) + 'a');
                    }
                }//end of the inner for loop
            }//end of the outer for loop
        }
    }//end of createGrid

    private boolean placeWord(String userword, int xcod, int ycod, int direction) {
        int dx;
        int dy;
        int check = 0;
        //the word entered by user is divided into different chars called letter
        char letter;
        /*
        The following if else statement take the random number generated from 0 to 2 in the createGrid method
        and define what each on the random number would do when multiplied in the for loop later
        */

        if(direction == 0) {
            //the direction to print along the y-axis
            dy = 1;
            dx = 0;
        }
        else if(direction == 1) {
            //the direction to print along the x-axis
            dy = 0;
            dx = 1;
        }
        else {
            //the direction to print diagonally
            dy = 1;
            dx = 1;
        }
        if( (dy*ycod + userword.length() > size) || (dx*xcod + userword.length() > size) ) {
            //if x and y coordinates don't allow the word to fit
            return false;
        }
        for (int i = 0; i < userword.length(); i++)
        {
            letter = userword.charAt(i);
            if (wordSearch[xcod+(dx*i)][ycod+(dy*i)] == '_' || wordSearch[xcod+(dx*i)][ycod+(dy*i)] == letter)
            {
                check++;
            }
        }
        if(check == userword.length()) {
            for(int i = 0; i < userword.length(); i++)
            {
                letter = userword.charAt(i);
                //add the word character by character to both arrays on xcod and ycod
                //dx and dy determine the direction
                wordSearch[xcod+(dx*i)][ycod+(dy*i)] = letter;
                solution[xcod+(dx*i)][ycod+(dy*i)] = letter;
            }
        }
        else {
            return false;
        }
        return true;
    }//end of the placeWord method

    public void printWord() {//printWord begins
        for(int i = 0; i < size; i++)
        {
            System.out.println(Arrays.toString(wordSearch[i]).replace(",", "|"));
        }
    }//end of printWord

    public void displaySolution() {//displaySolution begins
        for(int i = 0; i < size; i++)
        {
            System.out.println(Arrays.toString(solution[i]).replace(",", "|"));
        }
    }//displaySolution ends

}//end of the class


