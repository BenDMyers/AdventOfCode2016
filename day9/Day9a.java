import java.io.*;
import java.util.*;

public class Day9a
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner("BOGUS DECLARATION TO APPEASE THE COMPILER DEMONS");
        try
        {
            scan = new Scanner(new File("input.txt"));
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        String line = scan.nextLine();
        String decompressed = "";
        boolean inMarker = false;
        String marker = "";
        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) == '(')
            {
                inMarker = true;
            }
            else if(inMarker)
            {
                if(line.charAt(i) == ')')
                {
                    inMarker = false;
                    String num1 = marker.substring(0, marker.indexOf('x'));
                    String num2 = marker.substring(marker.indexOf('x') + 1);
                    marker = "";
                    try
                    {
                        int charsToRepeat = Integer.parseInt(num1);
                        int repeatTimes = Integer.parseInt(num2);
                        String repeat = line.substring(i + 1, i + charsToRepeat + 1);
                        i += charsToRepeat;
                        for(int j = 0; j < repeatTimes; j++)
                        {
                            decompressed += repeat;
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }
                else
                {
                    marker += "" + line.charAt(i);
                }
            }
            else if(line.charAt(i) != ' ')
            {
                decompressed += "" + line.charAt(i);
            }
        }

        System.out.println("" + decompressed.length());
    }
}
