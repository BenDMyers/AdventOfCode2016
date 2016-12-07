import java.io.*;
import java.util.*;

public class Day7b
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
            String supernet = split[0];
            String hypernet = split[1];
            // if(!abba(hypernet) && abba(notHypernet))
            // {
            //     support++;
            //     System.out.println(line);
            // }
            ArrayList<String> supernetABA = aba(supernet);
            ArrayList<String> hypernetABA = aba(hypernet);
            if(bab(supernetABA, hypernetABA)) {support++;}
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
    // public static boolean abba(String s)
    // {
    //     for(int i = 0; i <= s.length() - 4; i++)
    //     {
    //         if(s.charAt(i) == s.charAt(i+3) && s.charAt(i+1) == s.charAt(i+2) && s.charAt(i) != s.charAt(i+1)) {return true;}
    //     }
    //     return false;
    // }

    /**
     * Finds all of the ABA sequences in the given string
     * @param s string to check ABA satisfaction of
     * @return arraylist of all ABA sequences in the given string
     */
    public static ArrayList<String> aba(String s)
    {
        ArrayList<String> aba = new ArrayList<>();
        for(int i = 0; i <= s.length() - 3; i++)
        {
            if(s.charAt(i) == s.charAt(i+2) && s.charAt(i) != s.charAt(i+1)) {aba.add(s.substring(i, i+3));}
        }
        return aba;
    }

    /**
     * Determines whether any ABA sequences in a correspond to any BAB sequences in b
     * @param  a supernet ABA sequences
     * @param  b hypernet ABA sequences
     * @return true if any ABA sequences in a have a BAB counterpart in b
     */
    public static boolean bab(ArrayList<String> a, ArrayList<String> b)
    {
        for(int i = 0; i < a.size(); i++)
        {
            for(int j = 0; j < b.size(); j++)
            {
                if(a.get(i).charAt(0) == b.get(j).charAt(1) && a.get(i).charAt(1) == b.get(j).charAt(0) && a.get(i).charAt(1) == b.get(j).charAt(2))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
