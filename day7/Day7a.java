import java.io.*;
import java.util.*;

public class Day7a
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
}
