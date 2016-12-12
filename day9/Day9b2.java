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

    }

    public static int decompress(String line) throws Exception
    {
        int length = line.indexOf('('); // chars up until first marker
        String marker = line.substring(line.indexOf('('), line.indexOf(')') + 1);
        int scope = Integer.parseInt(marker.substring(marker.indexOf('(') + 1, marker.indexOf('x')));
    }
}
