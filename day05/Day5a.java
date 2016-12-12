import java.io.*;
import java.util.*;
import java.security.*;

public class Day5a
{
    private static MessageDigest md;

    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            usage();
            System.exit(0);
        }
        try {md = MessageDigest.getInstance("MD5");} catch( Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        String doorID = args[0];
        char[] password = new char[8];
        int index = 0;
        int lettersCount = 0;
        while(lettersCount < 8)
        {
            // if(index % 1000000 == 0) {System.out.println("Index: "+ index);}
            // String hexedMD5 = hex(md5(doorID + index));
            String md5 = md5(doorID + index);
            if(md5.startsWith("00000"))
            {
                password[lettersCount] = md5.charAt(5);
                System.out.print(password[lettersCount] + "");
                lettersCount++;
            }
            index++;
        }
        // System.out.println(new String(password));
    }

    public static String md5(String str)
    {
        try
        {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        // byte[] message = str.getBytes();
        md.update(str.getBytes());
        byte[] digested = md.digest();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < digested.length; i++)
        {
            // This was helpful: http://stackoverflow.com/questions/25838473/what-does-0xff-do-and-md5-structure
            buffer.append(Integer.toString((digested[i] & 0xff) + 0x100, 16).substring(1));
        }
        return buffer.toString();
    }

    public static void usage()
    {
        System.out.println("USAGE: java Day5a doorID");
    }
}
