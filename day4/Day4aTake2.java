import java.util.*;
import java.io.*;

public class Day4aTake2
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

        int idSum = 0;

        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            // System.out.println(line);
            String[] tokens = line.split("-");
            int id = -1;
            try
            {
                id = Integer.parseInt(tokens[tokens.length - 1].substring(0, tokens[tokens.length - 1].indexOf("[")));
                // System.out.println("" + id);
            } catch(Exception e)
            {
                e.printStackTrace();
                System.exit(0);
            }
            String checksum = tokens[tokens.length - 1].substring(tokens[tokens.length - 1].indexOf("[") + 1, tokens[tokens.length - 1].indexOf("]"));
            // System.out.println(checksum);
            String name = "";
            for(int i = 0; i < tokens.length - 1; i++)
            {
                name += tokens[i];
            }
            boolean valid = true;
            for(int i = 0; (i < 5 && valid); i++)
            {
                char mostFrequent = getMostFrequentChar(name);
                if(checksum.charAt(i) != mostFrequent)
                {
                    valid = false;
                }
                else
                {
                    name = name.replace("" + mostFrequent, "");
                }
            }
            if(valid) {
                idSum += id;
                // System.out.println(id + " IS VALID");
            }
        }

        System.out.println("" + idSum);
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
