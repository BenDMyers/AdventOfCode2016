import java.util.*;
import java.io.*;

public class Day4b
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

        // int idSum = 0;

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
                // idSum += id;
                // System.out.println(id + " IS VALID");
                System.out.println(shift(tokens, id).replaceAll("null", "") + " " + id);
            }
        }

        // System.out.println("" + idSum);
    }

    public static String shift(String[] tokens, int id)
    {
        String[] shiftedTokens = new String[tokens.length - 1];
        for(int i = 0; i < tokens.length - 1; i++)
        {
            for(int j = 0; j < tokens[i].length(); j++)
            {
                char orig = tokens[i].charAt(j);
                char shiftBy = (char)(id % 26);
                char shift = (char)(orig + shiftBy);
                if(shift > 'z')
                {
                    shift -= 26;
                }
                shiftedTokens[i] += "" + shift;
            }
        }
        String shifted = "";
        for(int i = 0; i < shiftedTokens.length; i++)
        {
            shifted += shiftedTokens[i];
            if(i != shiftedTokens.length - 1) {shifted += " ";}
        }
        return shifted;
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
