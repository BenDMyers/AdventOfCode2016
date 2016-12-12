import java.io.File;
import java.util.Scanner;

public class Day2b
{
    private static char[][] keypad = new char[][]{
        {' ', ' ', '1', ' ', ' '},
        {' ', '2', '3', '4', ' '},
        {'5', '6', '7', '8', '9'},
        {' ', 'A', 'B', 'C', ' '},
        {' ', ' ', 'D', ' ', ' '}};
    // private static String code = "";

    public static void main(String[] args)
    {
        Scanner input = new Scanner("THIS IS JUST A DUMMY DECLARATION TO APPEASE THE COMPILER GODS");

        try
        {
            input = new Scanner(new File("input.txt"));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        // DECLARING THESE BEFORE WHILE LOOP BECAUSE EACH LINE STARTS FROM THE PREVIOUS BUTTON
        int row = 2; // focus starts on middle row
        int col = 0; // focus starts on left column

        while(input.hasNextLine())
        {
            String line = input.nextLine();

            for(int i = 0; i < line.length(); i++)
            {
                char direction = line.charAt(i);
                // System.out.print(direction);
                switch(direction)
                {
                    // "IF A MOVE DOESN'T LEAD TO A BUTTON, IGNORE IT."
                    case 'U':
                        if(row != 0 && keypad[row - 1][col] != ' ') {row--;}
                        break;
                    case 'D':
                        if(row != 4 && keypad[row + 1][col] != ' ') {row++;}
                        break;
                    case 'L':
                        if(col != 0 && keypad[row][col - 1] != ' ') {col--;}
                        break;
                    case 'R':
                        if(col != 4 && keypad[row][col + 1] != ' ') {col++;}
                        break;
                }
            }

            //code += "" + keypad[row][col];
            System.out.print(keypad[row][col]);
        }

        // System.out.println(code);
    }
}
