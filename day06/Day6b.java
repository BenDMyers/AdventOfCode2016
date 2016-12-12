import java.util.*;
import java.io.*;

public class Day6b
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner("BOGUS DECLARATION TO APPEASE THE COMPILER GODS");
        try
        {
            scan = new Scanner(new File("input.txt"));
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        String[] columns = new String[8];
        Arrays.fill(columns, "");
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            for(int i = 0; i < 8; i++) // the length of each line is 8
            {
                columns[i] += "" + line.charAt(i);
            }
        }

        String solution = "";
        for(int i = 0; i < 8; i++)
        {
            solution += getLeastFrequentChar(columns[i] + "");
        }

        System.out.println(solution);
    }

    public static char getLeastFrequentChar(String str)
    {
        String s = str;
        char lastRemoved = ' ';
        while(!s.equals(""))
        {
            lastRemoved = getMostFrequentChar(s);
            s = s.replaceAll(lastRemoved + "", "");
        }
        return lastRemoved;
    }

    public static char getMostFrequentChar(String str)
    {
        char mostFrequent = ' ';
        int maxFrequency = 0;
        for(int i = 0; i < 26; i++)
        {
            int frequency = 0;
            for(int j = 0; j < str.length(); j++)
            {
                if(str.charAt(j) == (char)(i + 'a')) { frequency++; }
            }
            if(frequency > maxFrequency)
            {
                maxFrequency = frequency;
                mostFrequent = (char)(i + 'a');
            }
        }
        return mostFrequent;
    }
}
