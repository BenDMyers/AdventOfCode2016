import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Day1b
{
    private static ArrayList<int[]> locations = new ArrayList<>(); // locations that have already been visited

    private static int northSouth = 0; // number of blocks north/south, where north is positive and south is negative
    private static int eastWest = 0; // number of blocks east/west, where east is positive and west is negative
    private static char direction = 'N'; // start off facing north

    public static void main(String[] args)
    {
        Scanner input = new Scanner("THIS IS A DUMMY DECLARATION TO APPEASE THE COMPILER GODS");

        if(args.length != 1)
        {
            usage();
            System.exit(0);
        }

        try
        {
            input = new Scanner(new File(args[0]));
        } catch (Exception e)
        {
            usage();
            System.exit(0);
        }

        String line = input.nextLine();

        String[] moves = line.split(", ");

        try
        {
            int[] start = {0, 0};
            locations.add(start);
            for(int i = 0; i < moves.length; i++)
            {
                turn(moves[i].charAt(0));
                move(Integer.parseInt(moves[i].substring(1)));
            }
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Prints a usage statement.
     */
    public static void usage()
    {
        System.out.println("USAGE: java Day1b input.txt");
    }

    /**
     * Determines which compass direction we should face given a direction to turn.
     * @param turnDirection the  direction we should turn. 'R' for clockwise, 'L' for counterclockwise.
     */
    public static void turn(char turnDirection)
    {
        String turnOrder = "NESW";
        int currentDirection = turnOrder.indexOf(direction);
        if(turnDirection == 'R')
        {
            currentDirection++;
            if(currentDirection == 4) {currentDirection = 0;}
        }
        else if(turnDirection == 'L')
        {
            currentDirection--;
            if(currentDirection == -1) {currentDirection = 3;}
        }
        direction = turnOrder.charAt(currentDirection);
    }

    /**
     * Moves us the given number of blocks in our current direction.
     * @param blocks number of blocks to move.
     */
    public static void move(int blocks)
    {
        for(int i = 0; i < blocks; i++)
        {
            switch(direction)
            {
                case 'N':
                    northSouth++;
                    break;
                case 'S':
                    northSouth--;
                    break;
                case 'E':
                    eastWest++;
                    break;
                case 'W':
                    eastWest--;
                    break;
            }

            int[] location = {northSouth, eastWest};
            if(beenHereBefore())
            {
                System.out.println("N/S: " + northSouth);
                System.out.println("E/W: " + eastWest);
                System.out.println("Blocks: " + (Math.abs(northSouth) + Math.abs(eastWest)));

                System.exit(0);
            }
            else
            {
                locations.add(location);
            }
        }
    }

    /**
     * Determines whether we've been here before.
     * @return true if we've been here, false otherwise.
     */
    public static boolean beenHereBefore()
    {
        for(int[] location : locations)
        {
            if(location[0] == northSouth && location[1] == eastWest) {return true;}
        }
        return false;
    }
}
