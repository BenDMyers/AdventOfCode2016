import java.io.File;
import java.util.*;
// import java.util.HashMap;

public class Day4a
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner("bogus");
        try
        {
            scan = new Scanner(new File("input.txt"));
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        int idSum = 0;
        while (scan.hasNextLine())
        {
            String line = scan.nextLine();
            String[] tokens = line.split("-");
            String checksum = tokens[tokens.length - 1].substring(tokens[tokens.length - 1].indexOf("["), tokens[tokens.length - 1].indexOf("]"));
            int id = -1;
            try
            {
                id = Integer.parseInt(tokens[tokens.length - 1].substring(0, tokens[tokens.length - 1].indexOf("[")));
            } catch(Exception e)
            {
                e.printStackTrace();
            }

            // Get letter frequencies
            Map<Character, Integer> counts = new HashMap<Character, Integer>();
            for(int i = 0; i < 26; i++)
            {
                counts.put((char)(i+'a'), 0);
            }
            for(int i = 0; i < tokens.length - 1; i++)
            {
                for(int j = 0; j < tokens[i].length(); j++)
                {
                    counts.put(tokens[i].charAt(j), counts.get(tokens[i].charAt(j) + 1));
                }
            }

            // Sort letter frequencies
            List<Map.Entry<Character, Integer>> countsList = new ArrayList<Map.Entry<Character, Integer>>(counts.entrySet());
            Collections.sort(countsList, new Comparator<Map.Entry<Character, Integer>>() {
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2)
                {
                    // return o2.getValue().compareTo(o1.getValue());
                    if(o1.getValue().equals(o2.getValue()))
                    {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    else
                    {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                }
            });
            // printMap(counts);
            printList(countsList);
        }
    }

    public static void printMap(Map<Character, Integer> map)
    {
        for (Map.Entry<Character, Integer> entry : map.entrySet())
        {
            System.out.print(entry.getKey() + entry.getValue() + " ");
        }
    }

    public static void printList(List<Map.Entry<Character, Integer>> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i).getKey() + list.get(i).getValue() + " ");
        }
    }
}
