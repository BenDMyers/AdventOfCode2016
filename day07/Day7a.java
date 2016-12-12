import java.io.*;
import java.util.*;

public class Day7a
{
    /**
     * Solves Advent of Code 2016 Day 7a problem
     * @param String[] args command line arguments -- unused
     */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner("BOGUS DECLARATION TO APPEASE THE COMPILER GODS");
        try
        {
            scan = new Scanner(new File("input.txt"));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        int support = 0; // represents the number of IPs which support TLS

        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            String[] split = split(line);
            String notHypernet = split[0];
            String hypernet = split[1];
            if(!abba(hypernet) && abba(notHypernet))
            {
                support++;
                System.out.println(line);
            }
        }
        System.out.println("" + support);
    }

    /**
     * Splits the given string into in-hypernet and not-in-hypernet.
     * @param s string to split
     * @return String array where [0] is all text not in hypernet and [1] is all text in hypernet
     */
    public static String[] split(String s)
    {
        String[] split = new String[2];
        Arrays.fill(split, "");
        boolean inHypernet = false;
        for(int i = 0; i < s.length(); i++)
        {
            if(!inHypernet && s.charAt(i) != '[')
            {
                split[0] += "" + s.charAt(i);
            }
            else if(!inHypernet && s.charAt(i) == '[')
            {
                split[0] += " ";
                inHypernet = true;
            }
            else if(inHypernet && s.charAt(i) != ']')
            {
                split[1] += "" + s.charAt(i);
            }
            else // inHypernet && s.charAt(i) == ']'
            {
                split[1] += " ";
                inHypernet = false;
            }
        }
        return split;
    }

    /**
     * Determines whether any substring of the given string fits the ABBA pattern.
     * @param s string to check ABBA satisfaction of
     * @return true if the string contains an ABBA sequences, false otherwise.
     */
    public static boolean abba(String s)
    {
        for(int i = 0; i <= s.length() - 4; i++)
        {
            if(s.charAt(i) == s.charAt(i+3) && s.charAt(i+1) == s.charAt(i+2) && s.charAt(i) != s.charAt(i+1)) {return true;}
        }
        return false;
    }
}
