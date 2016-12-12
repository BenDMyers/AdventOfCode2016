// GENERALIZED CLASS FOR SOLVING DAY 2'S PROBLEMS

import java.io.File;
import java.util.Scanner;

public class Day2
{
    private static char[][] keypad;

    public static void main(String[] args)
    {
        if(args.length != 2)
        {
            usage();
            System.exit(0);
        }

        Scanner input = new Scanner("THIS IS JUST A DUMMY DECLARATION TO APPEASE THE COMPILER GODS");

        try
        {
            setKeypad(args[0]);
            input = new Scanner(new File(args[1]));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        // CHECK THAT A 5 EXISTS IN THE KEYPAD
        int[] fiveLocation = findTheFive();
        if(fiveLocation[0] == -1)
        {
            System.out.println("ERROR: Keypad contains no fives");
            System.exit(0);
        }

        // DECLARING THESE BEFORE WHILE LOOP BECAUSE EACH LINE STARTS FROM THE PREVIOUS BUTTON
        int row = fiveLocation[0];
        int col = fiveLocation[1];

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
                        if(row != keypad.length - 1 && keypad[row + 1][col] != ' ') {row++;}
                        break;
                    case 'L':
                        if(col != 0 && keypad[row][col - 1] != ' ') {col--;}
                        break;
                    case 'R':
                        if(col != keypad[0].length - 1 && keypad[row][col + 1] != ' ') {col++;}
                        break;
                }
            }

            //code += "" + keypad[row][col];
            System.out.print(keypad[row][col]);
        }

        // System.out.println(code);
    }

    /**
     * Prints a usage statement.
     */
    public static void usage()
    {
        System.out.println("USAGE: java Day2 keypad.txt input.txt");
    }

    /**
     * Sets the keypad using the layout from a file.
     * @param filename the name of the file which stores the keypad layout
     */
    public static void setKeypad(String filename) throws Exception
    {
        Scanner preliminaryScanner = new Scanner(new File(filename));
        int keypadRows = 0;
        int keypadCols = 0;

        while(preliminaryScanner.hasNextLine())
        {
            keypadRows++;
            keypadCols = Math.max(keypadCols, preliminaryScanner.nextLine().length());
        }

        keypad = new char[keypadRows][keypadCols];

        Scanner keypadInput = new Scanner(new File(filename));

        int i = 0;
        while(keypadInput.hasNextLine())
        {
            String line = keypadInput.nextLine();
            for(int j = 0; j < line.length(); j++)
            {
                keypad[i][j] = line.charAt(j);
            }
            if(keypad[i].length > line.length())
            {
                for(int j = line.length(); j < keypad[i].length; j++)
                {
                    keypad[i][j] = ' ';
                }
            }
            i++;
        }
    }

    /**
     * Finds the first 5 in the keypad and returns the location.
     * There should be exactly one 5 in the keypad
     * @return an array such that [0] = the 5's row and [1] = the 5's column, and {-1, -1} if no 5 found.
     */
    public static int[] findTheFive()
    {
        int[] fiveLocation;
        for(int i = 0; i < keypad.length; i++)
        {
            for(int j = 0; j < keypad[i].length; j++)
            {
                if(keypad[i][j] == '5')
                {
                    fiveLocation = new int[]{i, j};
                    return fiveLocation;
                }
            }
        }
        fiveLocation = new int[]{-1, -1};
        return fiveLocation;
    }
}
