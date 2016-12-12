import java.io.*;
import java.util.*;

public class Day10a
{
    public static final boolean HIGH = true;
    public static final boolean LOW = false;

    private static ArrayList<Bot> bots = new ArrayList<>();
    private static ArrayList<Bot> outputs = new ArrayList<>(21);

    public static void main(String[] args)
    {
        for(int i = 0; i < 210; i++)
        {
            bots.add(new Bot(i));
        }
        for(int i = 0; i < 21; i++)
        {
            outputs.add(new Bot(i));
        }

        Scanner scan = new Scanner("This is not the Scanner you are looking for");

        try
        {
            scan = new Scanner(new File("input.txt"));
        } catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        // Value scan
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            if(line.startsWith("value "))
            {
                System.out.println(line);
                try
                {
                    int chip = Integer.parseInt(line.substring(6, line.indexOf(" goes")));
                    int bot = Integer.parseInt(line.substring(line.indexOf("bot ") + 4));
                    bots.get(bot).give(chip);
                } catch (Exception e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
            else if(line.startsWith("bot "))
            {
                System.out.println(line);
                try
                {
                    int from = Integer.parseInt(line.substring(4, line.indexOf(" gives")));
                    bots.get(from).giveCommand(line);
                } catch(Exception e)
                {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }

        try
        {
            while(true)
            {
                for(Bot b : bots)
                {
                    b.processCommands();
                    for(Bot b2 : bots)
                    {
                        if(b2.check(17, 61))
                        {
                            System.out.println("BOT " + b2.getNumber() + " COMPARED CHIP-17 AND CHIP-61");
                            System.exit(0);
                        }
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void exchange(int from, int to, boolean highOrLow)
    {
        bots.get(to).give(bots.get(from).take(highOrLow));
    }

    public static void deposit(int from, int to, boolean highOrLow)
    {
        outputs.get(to).give(bots.get(from).take(highOrLow));
    }
}

class Bot
{
    private ArrayList<Integer> chips = new ArrayList<>();
    private ArrayList<String> commands = new ArrayList<>();

    private int number;

    public Bot(int n)
    {
        number = n;
    }

    public void give(int chip)
    {
        chips.add(chip);
        Collections.sort(chips);
    }

    public int take(boolean highOrLow)
    {
        if(highOrLow == Day10a.HIGH)
        {
            return chips.remove(chips.size() - 1);
        }
        else // highOrLow == LOW
        {
            return chips.remove(0);
        }
    }

    public boolean check(int lowChip, int highChip)
    {
        return chips.contains(lowChip) && chips.contains(highChip);
    }

    public void giveCommand(String command)
    {
        commands.add(command);
    }

    public void processCommands() throws Exception
    {
        if(chips.size() == 2)
        {
            for(String command : commands)
            {
                processCommand(command);
            }
        }
    }

    public void processCommand(String command) throws Exception
    {
        System.out.println("COMMAND: " + command);
        if(command.contains("low to output "))
        {
            int toLow = Integer.parseInt(command.substring(command.indexOf("low to output ") + 14, command.indexOf(" and high")));

            Day10a.deposit(number, toLow, Day10a.LOW);
        }
        else if(command.contains("low to bot "))
        {
            int toLow = Integer.parseInt(command.substring(command.indexOf("low to bot ") + 11, command.indexOf(" and high")));

            Day10a.exchange(number, toLow, Day10a.LOW);
        }

        if(command.contains("high to output "))
        {
            int toHigh = Integer.parseInt(command.substring(command.indexOf("high to output ") + 15));

            Day10a.deposit(number, toHigh, Day10a.HIGH);
        }
        else if(command.contains("high to bot "))
        {
            int toHigh = Integer.parseInt(command.substring(command.indexOf("high to bot ") + 12));

            Day10a.exchange(number, toHigh, Day10a.HIGH);
        }
    }

    public int getNumber() { return number; }
}
