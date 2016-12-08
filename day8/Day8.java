import java.io.*;
import java.util.*;

public class Day8
{
    private static boolean[][] screen = new boolean[6][50];

    public static void main(String[] args)
    {
        Scanner scan = new Scanner("BOGUS DECLARATION TO APPEASE THE COMPILER CTHULHU");
        try
        {
            scan = new Scanner(new File("input.txt"));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            // System.out.println("LINE: " + line);
            if(line.startsWith("rect "))
            {
                String num1 = line.substring(5, line.indexOf('x'));
                String num2 = line.substring(line.indexOf('x') + 1);
                int a = -1;
                int b = -1;
                try
                {
                    a = Integer.parseInt(num1);
                    b = Integer.parseInt(num2);
                } catch(Exception e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }
                System.out.println("RECTANGLING FROM " + a + " TO " + b);
                rect(a, b);
            }
            else if(line.startsWith("rotate row y="))
            {
                String num1 = line.substring(line.indexOf('=') + 1, line.indexOf(" by"));
                String num2 = line.substring(line.indexOf(" by ") + 4);
                int row = -1;
                int by = -1;
                try
                {
                    row = Integer.parseInt(num1);
                    by = Integer.parseInt(num2);
                } catch(Exception e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }
                System.out.println("ROTATING ROW " + row + " BY " + by);
                rotateRow(row, by);
            }
            else if(line.startsWith("rotate column x="))
            {
                String num1 = line.substring(line.indexOf('=') + 1, line.indexOf(" by"));
                String num2 = line.substring(line.indexOf(" by ") + 4);
                int col = -1;
                int by = -1;
                try
                {
                    col = Integer.parseInt(num1);
                    by = Integer.parseInt(num2);
                } catch(Exception e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }
                System.out.println("ROTATING COL " + col + " BY "+ by);
                rotateCol(col, by);
            }
        }

        int lit = 0;
        for(int i = 0; i < screen.length; i++)
        {
            for(int j = 0; j < screen[i].length; j++)
            {
                if(screen[i][j]) {lit++;}
            }
        }

        printScreen();

        System.out.println("\n" + lit + " lit pixels");
    }

    public static void rect(int wide, int tall)
    {
        for(int i = 0; i < tall; i++)
        {
            for(int j = 0; j < wide; j++)
            {
                screen[i][j] = true;
            }
        }
    }

    public static void rotateRow(int row, int by)
    {
        boolean[][] newScreen = new boolean[6][50];
        for(int i = 0; i < screen.length; i++)
        {
            for(int j = 0; j < screen[i].length; j++)
            {
                newScreen[i][j] = screen[i][j];
            }
        }
        int BY = by % screen[row].length;
        for(int i = BY; i < newScreen[row].length; i++)
        {
            newScreen[row][i] = screen[row][i - BY];
        }
        if(BY != 0)
        {
            for(int i = 0; i < BY; i++)
            {
                newScreen[row][i] = screen[row][screen[row].length - BY + i];
            }
        }
        screen = newScreen;
    }

    public static void rotateCol(int col, int by)
    {
        boolean[][] newScreen = new boolean[6][50];
        for(int i = 0; i < screen.length; i++)
        {
            for(int j = 0; j < screen[i].length; j++)
            {
                newScreen[i][j] = screen[i][j];
            }
        }
        int BY = by % screen.length;
        for(int i = BY; i < newScreen.length; i++)
        {
            newScreen[i][col] = screen[i - BY][col];
        }
        if(BY != 0)
        {
            for(int i = 0; i < BY; i++)
            {
                newScreen[i][col] = screen[screen.length - BY + i][col];
            }
        }
        screen = newScreen;
    }

    public static void printScreen()
    {
        for(int i = 0; i < screen.length; i++)
        {
            for(int j = 0; j < screen[i].length; j++)
            {
                if(screen[i][j]) {System.out.print("#");}
                else {System.out.print(".");}
            }
            System.out.println();
        }
    }
}
