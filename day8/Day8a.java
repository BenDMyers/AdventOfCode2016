import java.io.*;
import java.util.*;

public class Day8a
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner("BOGUS DECLARATION TO APPEASE THE COMPILER CTHULHU");
        try
        {
            scan = new Scanner("input.txt");
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
