import java.util.Scanner;
import java.io.File;

public class Day3a
{
    public static void main(String[] args)
    {
        Scanner read = new Scanner("BOGUS INITIALIZATION TO APPEASE COMPILER GODS");
        try
        {
            read = new Scanner(new File(args[0]));
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        int possible = 0;
        while(read.hasNextLine())
        {
            String line = read.nextLine();
            Scanner lineReader = new Scanner(line);
            int[] sortedLine = sortLine(lineReader.nextInt(), lineReader.nextInt(), lineReader.nextInt());
            if(sortedLine[0] + sortedLine[1] > sortedLine[2])
            {
                possible++;
            }
        }

        System.out.println("" + possible);
    }

    public static int[] sortLine(int a, int b, int c)
    {
        if (a > b)
        {
            int temp = a;
            a = b;
            b = temp;
        }

        if (b > c)
        {
            int temp = b;
            b = c;
            c = temp;
        }

        if (a > c)
        {
            int temp = a;
            a = c;
            c = temp;
        }

        int[] sorted = {a, b, c};
        return sorted;
    }
}
