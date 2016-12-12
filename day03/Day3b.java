import java.util.Scanner;
import java.io.File;

public class Day3b
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
            String line1 = read.nextLine();
            String line2 = read.nextLine();
            String line3 = read.nextLine();
            Scanner line1Reader = new Scanner(line1);
            Scanner line2Reader = new Scanner(line2);
            Scanner line3Reader = new Scanner(line3);
            int[] sortedLine1 = sortLine(line1Reader.nextInt(), line2Reader.nextInt(), line3Reader.nextInt());
            int[] sortedLine2 = sortLine(line1Reader.nextInt(), line2Reader.nextInt(), line3Reader.nextInt());
            int[] sortedLine3 = sortLine(line1Reader.nextInt(), line2Reader.nextInt(), line3Reader.nextInt());
            if(sortedLine1[0] + sortedLine1[1] > sortedLine1[2])
            {
                possible++;
            }
            if(sortedLine2[0] + sortedLine2[1] > sortedLine2[2])
            {
                possible++;
            }
            if(sortedLine3[0] + sortedLine3[1] > sortedLine3[2])
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
