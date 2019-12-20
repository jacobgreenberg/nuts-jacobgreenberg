import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This is my code! Its goal is to provide a CLI for user
 * CS 312 - Assignment 6
 * @author Jacob Greenberg
 * @version 1.0 11-5-19
 */
public class CLI
{
    protected long startTime = System.currentTimeMillis();
    protected long endTime;

    /*
     * purpose: constructor
     * input: nothing
     * result: nothing
     */
    public CLI()
    {
    }

    /*
     * purpose: prints usage message to console
     * input: nothing
     * result: usage message
     */
    private void usage()
    {
        System.err.println("\nUsage: java Driver [-n, -r, -t, -d]\n" +
                "-n <number list>: sorts numbers of a given number list\n" +
                "-r <count>:       sorts random numbers of a given count\n" +
                "-t (optional):    prints sorting time to console\n" +
                "-d (optional):    debug mode (outputs tree before each extraction)\n");
    }

    /*
     * purpose: sort tree of values from cl
     * input: list of args from shell, index to start parsing at, boolean debug
     * result: sorted values, tree (if debug)
     */
    private void numberList(List<String> args, int start, boolean debug)
    {
        List<Integer> values = new ArrayList<>();

        for (String arg : args.subList(start, args.size()))
            values.add(Integer.parseInt(arg));

        System.out.println("\nSorted Values:\n" + new Tree<>(values, debug).sort());
    }

    /*
     * purpose: sort tree of random values
     * input: list of args from shell, index to start parsing at, boolean debug
     * result: randomly generated values, sorted values, tree (if debug)
     */
    private void randomNumber(List<String> args, int start, boolean debug)
    {
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < Integer.parseInt(args.get(start)); i++)
            values.add(new Random().nextInt(1024));

        System.out.println("Generated Values\n" + values + "\n");
        System.out.println("Sorted Values:\n" + new Tree<>(values, debug).sort());
    }

    /*
     * purpose: record runtime and memory
     * input: start and end time
     * result: runtime and memory used
     */
    private void time(long startTime, long endTime)
    {
        long memoryUsed = Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory();

        System.out.println("\nSorting data took " + (endTime - startTime) +
                " milliseconds to sort using " + memoryUsed / (1024 * 1024)
                + "Mb of memory");
    }

    /*
     * purpose: process args and call tree methods
     * input: args
     * result: return from methods called
     */
    void main(String[] args)
    {
        List<String> argsList = new ArrayList<>(Arrays.asList(args));

        if (argsList.size() < 2)
            usage();

        else if (argsList.get(0).equalsIgnoreCase("-n"))
            numberList(argsList, 1, false);

        else if (argsList.get(0).equalsIgnoreCase("-r"))
            randomNumber(argsList, 1, false);

        else if (argsList.get(0).equalsIgnoreCase("-t") &&
                argsList.get(1).equalsIgnoreCase("-n"))
        {
            numberList(argsList, 2, false);
            endTime = System.currentTimeMillis();
            time(startTime, endTime);
        }

        else if (argsList.get(0).equalsIgnoreCase("-t") &&
                argsList.get(1).equalsIgnoreCase("-r"))
        {
            randomNumber(argsList, 2, false);
            endTime = System.currentTimeMillis();
            time(startTime, endTime);
        }

        else if (argsList.get(0).equalsIgnoreCase("-d") &&
                argsList.get(1).equalsIgnoreCase("-n"))
            numberList(argsList, 2, true);

        else if (argsList.get(0).equalsIgnoreCase("-d") &&
                argsList.get(1).equalsIgnoreCase("-r"))
            randomNumber(argsList, 2, true);

        else if (argsList.get(0).equalsIgnoreCase("-t") &&
                argsList.get(1).equalsIgnoreCase("-d") &&
                argsList.get(2).equalsIgnoreCase("-n"))
        {
            numberList(argsList, 3, true);
            endTime = System.currentTimeMillis();
            time(startTime, endTime);
        }

        else if (argsList.get(0).equalsIgnoreCase("-t") &&
                argsList.get(1).equalsIgnoreCase("-d") &&
                argsList.get(2).equalsIgnoreCase("-r"))
        {
            randomNumber(argsList, 3, true);
            endTime = System.currentTimeMillis();
            time(startTime, endTime);
        }

        else if (argsList.get(0).equalsIgnoreCase("-d") &&
                argsList.get(1).equalsIgnoreCase("-t") &&
                argsList.get(2).equalsIgnoreCase("-n"))
        {
            numberList(argsList, 3, true);
            endTime = System.currentTimeMillis();
            time(startTime, endTime);
        }

        else if (argsList.get(0).equalsIgnoreCase("-d") &&
                argsList.get(1).equalsIgnoreCase("-t") &&
                argsList.get(2).equalsIgnoreCase("-r"))
        {
            randomNumber(argsList, 3, true);
            endTime = System.currentTimeMillis();
            time(startTime, endTime);
        }

        else
            usage();
    }
}
