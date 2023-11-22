package Utilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class RandomSeatsFiller
{
    private static final int MIN_SEATS_TO_BE_FILLED = 40;
    private static final int MAX_SEATS_THAT_CAN_BE_FILLED = 70;
    private static final int MAX_SEATS_THAT_CAN_BE_FILLED_APPEND = 4;
    private static final int MAX_CONSECUTIVE_SEATS = 8;

    public static void Fill(Path path, int totalSeats, boolean isAppend)
    {
        Random randomGenerator = new Random();
        int seatsToBeFilled = isAppend ?
                              randomGenerator.nextInt(0, MAX_SEATS_THAT_CAN_BE_FILLED_APPEND + 1) :
                              randomGenerator.nextInt(MIN_SEATS_TO_BE_FILLED, MAX_SEATS_THAT_CAN_BE_FILLED + 1);

        try
        {
            Files.writeString(path, randomGenerator.nextInt(1, totalSeats + 1) + "\n",
                    StandardOpenOption.APPEND);
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Writing Random Seats In " + path);
            System.exit(0);
        }

        int seatsFilled = 1;

        while (seatsFilled < seatsToBeFilled)
        {
            int randomSeat = randomGenerator.nextInt(1, totalSeats + 1);

            int numberOfConsecutiveSeats = randomGenerator.nextInt(0, MAX_CONSECUTIVE_SEATS);
            OUTER: for (int i = 0; i <= numberOfConsecutiveSeats; i++)
            {
                try
                {
                    Path file = Path.of(path.toUri());
                    String bookedSeats = Files.readString(file);

                    for (String bookedSeat : bookedSeats.trim().split("\\r?\\n"))
                        if (Integer.parseInt(bookedSeat.trim()) == (randomSeat + i)) continue OUTER;
                }
                catch (Exception e)
                {
                    System.out.println(e + "\nError Reading Seating Data!");
                    System.exit(0);
                }

                try
                {
                    Files.writeString(path, randomSeat + i + "\n", StandardOpenOption.APPEND);
                }
                catch (Exception e)
                {
                    System.out.println(e + "\nError Writing Random Seats In " + path);
                    System.exit(0);
                }
                seatsFilled++;
            }
        }


    }
}
