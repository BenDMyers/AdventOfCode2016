import java.io.*;
import java.util.*;
import java.security.*;

public class Day5b
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
        Arrays.fill(password, ' ');
        int index = 0;
        // int lettersCount = 0;
        while(spacesLeft(password))
        {
            // if(index % 1000000 == 0) {System.out.println("Index: "+ index);}
            // String hexedMD5 = hex(md5(doorID + index));
            String md5 = md5(doorID + index);
            char at5 = md5.charAt(5);
            try
            {
                int at5Index = Integer.parseInt("" + at5);
                if(md5.startsWith("00000") && at5Index >= 0 && at5Index < 8 && password[at5Index] == ' ')
                {
                    password[at5Index] = md5.charAt(6);
                    System.out.println(new String(password));
                    // System.out.print(password[lettersCount] + "");
                    // lettersCount++;
                }
            } catch(Exception e){ /* do nothing */ }

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

    public static boolean spacesLeft(char[] pwd)
    {
        for(int i = 0; i < pwd.length; i++)
        {
            if(pwd[i] == ' ') {return true;}
        }
        return false;
    }

    public static void usage()
    {
        System.out.println("USAGE: java Day5b doorID");
    }

}
