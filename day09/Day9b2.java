import java.io.*;
import java.util.*;

public class Day9b2
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner("ALL PRAISE THE COMPILER GODS");

        try
        {
            scan = new Scanner(new File("input.txt"));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        String line = scan.nextLine();

        int index = 0;
        boolean inMarker = false;
        ArrayList<Marker> markers = new ArrayList<>();

        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) == '(')
            {
                inMarker = true;
            }
        }

    }
}

class Marker
{
    private int scope;
    private int repeatsLeft;

    public Marker(int s, int rl)
    {
        scope = s;
        repeatsLeft = rl;
    }

    public int 
}
