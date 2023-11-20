package Utilities;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.nio.file.Path;

public class RandomSeatsFiller
{
    private static final int MIN_SEATS_TO_BE_FILLED = 40;
    private static final int MAX_SEATS_THAT_CAN_BE_FILLED = 70;
    private static final int MAX_CONSECUTIVE_SEATS = 8;

    public static void Fill(Path path)
    {
        Random randomGenerator = new Random();
        int seatsToBeFilled = randomGenerator.nextInt(MIN_SEATS_TO_BE_FILLED,
                MAX_SEATS_THAT_CAN_BE_FILLED + 1);

        ArrayList<Integer> chosenSeats = new ArrayList<>(seatsToBeFilled);
        StringBuilder toBeWritten = new StringBuilder();
        int seatsFilled = 0;

        while (seatsFilled < seatsToBeFilled)
        {
            int randomSeat = randomGenerator.nextInt(1, HallLayout.TotalSeats() + 1);

            int numberOfConsecutiveSeats = randomGenerator.nextInt(0, MAX_CONSECUTIVE_SEATS);
            for (int i = 0; i <= numberOfConsecutiveSeats; i++)
            {
                if (chosenSeats.contains(randomSeat + i)) continue;
                chosenSeats.add(randomSeat + i);
                toBeWritten.append(randomSeat + i).append("\n");
                seatsFilled++;
            }
        }

        try
        {
            Files.writeString(path, toBeWritten);
        }
        catch (Exception e)
        {
            System.out.println("Error Writing Random Seats In " + path.toString());
            System.exit(0);
        }
    }
}
