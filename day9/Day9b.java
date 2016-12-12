import java.io.*;
import java.util.*;

public class Day9b
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner("BOGUS DECLARATION TO APPEASE THE COMPILER DEMONS");
        try
        {
            scan = new Scanner(new File("sampleInput.txt"));
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        String line = scan.nextLine();
        // String decompressed = "";
        boolean inMarker = false;
        // String marker = "";
        int length = 0;
        ArrayList<Marker> activeMarkers = new ArrayList<>();
        int markerStart = -1;
        for(int i = 0; i < line.length(); i++)
        {
            for(Marker marker : activeMarkers)
            {
                marker.decrementScope();
            }
            if(line.charAt(i) == '(')
            {
                inMarker = true;
                markerStart = i;
            }
            else if(inMarker)
            {
                if(line.charAt(i) == ')')
                {
                    inMarker = false;
                    String marker = line.substring(markerStart + 1, i);
                    String num1 = marker.substring(0, marker.indexOf('x'));
                    String num2 = marker.substring(marker.indexOf('x') + 1);
                    // marker = "";
                    try
                    {
                        int scope = Integer.parseInt(num1);
                        int numRepeats = Integer.parseInt(num2);
                        // int productOfCurrentRepeats = 1;
                        if(activeMarkers.size() > 0)
                        {
                            for(int n = 0; n < activeMarkers.size(); n++)
                            {
                                for(int r = 0; r < activeMarkers.get(n).getRepeats(); r++)
                                {
                                    activeMarkers.add(new Marker(scope, numRepeats - 1));
                                }
                            }
                        }
                        else
                        {
                            activeMarkers.add(new Marker(scope, numRepeats - 1));
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }
            }
            else // not in marker
            {
                int product = 1;
                for(Marker marker : activeMarkers)
                {
                    product *= marker.getRepeats();
                }
                length += product;
            }

            // Now we filter finished markers
                // This wonky structure is to avoid the ConcurrentModificationException!
            ArrayList<Marker> remove = new ArrayList<>();
            for(Marker marker : activeMarkers)
            {
                length += marker.getRepeats();
                if(marker.scopeLeft() == 0)
                {
                    remove.add(marker);
                }
            }
            for(Marker marker : remove)
            {
                activeMarkers.remove(marker);
            }
        }

        System.out.println("" + length);
    }
}

class Marker
{
    private int scope;
    private int numRepeats;

    public Marker(int s, int n)
    {
        scope = s;
        numRepeats = n;
    }

    public void decrementScope()
    {
        scope--;
    }

    public int scopeLeft()
    {
        return scope;
    }

    public int getRepeats()
    {
        return numRepeats;
    }
}
