import java.io.*;
import java.util.*;

public class Day10
{
    public static final boolean HIGH = true;
    public static final boolean LOW = false;

    private static ArrayList<Bot> bots = new ArrayList<>(); // stores all bots
    private static ArrayList<Bot> outputs = new ArrayList<>(); // stores all output bins

    /**
     * Kicks off the whole shebang
     * @param args command line arguments -- unused
     */
    public static void main(String[] args)
    {
        // Manually adding each bot. Ugh.
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

        // Read lines
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            if(line.startsWith("value "))
            {
                // The bot listed starts off with the value listed
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
                // Save the command for later, which is when the bot has two chips
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
            while(anyCommandsLeft())
            {
                for(Bot b : bots)
                {
                    b.processCommands();
                    for(Bot b2 : bots)
                    {
                        if(b2.check(17, 61))
                        {
                            System.out.println("BOT " + b2.getNumber() + " COMPARED CHIP-17 AND CHIP-61");
                            // System.exit(0);
                        }
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("" + (outputs.get(0)).getChip(0)*(outputs.get(1)).getChip(0)*(outputs.get(2)).getChip(0));
    }

    /**
     * Gives a chip from FROM bot to TO bot
     * @param from The number of the bot to steal a chip from
     * @param to The number of the bot to give the chip too
     * @param highOrLow whether the FROM bot should give up their higher chip (TRUE) or lower chip (FALSE)
     */
    public static void exchange(int from, int to, boolean highOrLow)
    {
        bots.get(to).give(bots.get(from).take(highOrLow));
    }

    /**
     * Gives a chip from FROM bot to TO output bin
     * @param from The number of the bot to steal a chip from
     * @param to The number of the output bin to give the chip too
     * @param highOrLow whether the FROM bot should give up their higher chip (TRUE) or lower chip (FALSE)
     */
    public static void deposit(int from, int to, boolean highOrLow)
    {
        outputs.get(to).give(bots.get(from).take(highOrLow));
    }

    /**
     * Determines whether any bots have commands left to execute
     * @return true if even one bot has an unexecuted command, false otherwise
     */
    public static boolean anyCommandsLeft()
    {
        for(Bot b : bots)
        {
            if(b.commandsLeft())
            {
                return true;
            }
        }
        return false;
    }
}

/**
 * Handles bots with chips -- also output bins.
 */
class Bot
{
    private ArrayList<Integer> chips = new ArrayList<>(); // the chips this bot current holds
    private ArrayList<String> commands = new ArrayList<>(); // the commands this bot has yet to execute

    private int number; // this bot's ID number

    /**
     * Bot constructor
     * @param n bot's ID number
     * @return constructed Bot object
     */
    public Bot(int n)
    {
        number = n;
    }

    /**
     * Gives a chip to this bot
     * @param chip chip to give
     */
    public void give(int chip)
    {
        chips.add(chip);
        Collections.sort(chips);
    }

    /**
     * Takes a chip from this bot
     * @param  highOrLow whether to take the higher chip (TRUE) or lower chip (FALSE)
     * @return the value of the stolen chip
     */
    public int take(boolean highOrLow)
    {
        if(highOrLow == Day10.HIGH)
        {
            return chips.remove(chips.size() - 1);
        }
        else // highOrLow == LOW
        {
            return chips.remove(0);
        }
    }

    /**
     * Checks whether this bot is currently holding the desired chips
     * @param  lowChip the lower-valued desired chip
     * @param  highChip the higher-valued desired chip
     * @return true if this bot is holding the desired chips, false otherwise
     */
    public boolean check(int lowChip, int highChip)
    {
        return chips.contains(lowChip) && chips.contains(highChip);
    }

    /**
     * Instructs bot to remember a command to execute when it has two chips.
     * @param command command to execute
     */
    public void giveCommand(String command)
    {
        commands.add(command);
    }

    /**
     * Iterates through all commands if the bot has two chips
     * @throws Exception This would be a NumberFormatException from int parsing if I were less lazy
     */
    public void processCommands() throws Exception
    {
        if(chips.size() == 2)
        {
            for(String command : commands)
            {
                processCommand(command);
            }
            commands.clear();
        }
    }

    /**
     * Determines whether this bot has unexecuted commands left
     * @return true if this bot still has commands to execute, false otherwise
     */
    public boolean commandsLeft()
    {
        return commands.size() > 0;
    }

    /**
     * Executes a remembered command
     * @param  command command to execute
     * @throws Exception This would be a NumberFormatException from int parsing if I were less lazy
     */
    public void processCommand(String command) throws Exception
    {
        // TO WHICH OUTPUT BIN OR BOT SHOULD THE LOW CHIP BE GIVEN
        if(command.contains("low to output "))
        {
            int toLow = Integer.parseInt(command.substring(command.indexOf("low to output ") + 14, command.indexOf(" and high")));

            Day10.deposit(number, toLow, Day10.LOW);
        }
        else if(command.contains("low to bot "))
        {
            int toLow = Integer.parseInt(command.substring(command.indexOf("low to bot ") + 11, command.indexOf(" and high")));

            Day10.exchange(number, toLow, Day10.LOW);
        }

        // TO WHICH OUTPUT BIN OR BOT SHOULD THE HIGH CHIP BE GIVEN
        if(command.contains("high to output "))
        {
            int toHigh = Integer.parseInt(command.substring(command.indexOf("high to output ") + 15));

            Day10.deposit(number, toHigh, Day10.HIGH);
        }
        else if(command.contains("high to bot "))
        {
            int toHigh = Integer.parseInt(command.substring(command.indexOf("high to bot ") + 12));

            Day10.exchange(number, toHigh, Day10.HIGH);
        }
    }

    /**
     * Gets the bot's ID number
     * @return the bot's ID number
     */
    public int getNumber() { return number; }

    /**
     * Gets the chip at the given index
     * @param chipIndex index of chip to retrieve
     * @return the chip at the given index
     */
    public int getChip(int chipIndex)
    {
        return chips.get(chipIndex);
    }
}
