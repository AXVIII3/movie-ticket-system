package Managers;

import Utilities.Movie;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class BookingManager
{
    public static Movie[] Movies;
    public static int dateIndex;
    public static int timeIndex;

    public static void Initialize()
    {
        String[] movieData = DataManager.ProcessMovieData();
        Movies = new Movie[movieData.length];

        int i = 0;
        for (String data : movieData)
            Movies[i++] = new Movie(data.trim().split("\\r?\\n"));
    }

    public static void BookSeats(Movie movie, int[] seats)
    {
        Path path = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" +
                            movie.dates[dateIndex] + "/" +
                            movie.times[timeIndex].replace(":", "_") + ".txt");
        for (int seat : seats)
        {
            try
            {
                Files.writeString(path, String.valueOf(seat), StandardOpenOption.APPEND);
            }
            catch (Exception e)
            {
                System.out.println("Error Writing Booked Seats In " + path);
                System.exit(0);
            }
        }
    }

    public static ArrayList<Integer> GetBookedSeats(Movie movie)
    {
        ArrayList<Integer> bookedSeats = new ArrayList<>();
        try
        {
            Path file = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" +
                                movie.dates[dateIndex] + "/" +
                                movie.times[timeIndex].replace(":", "_") + ".txt");
            for (String bookedSeat : Files.readString(file).trim().split("\\r?\\n"))
                bookedSeats.add(Integer.parseInt(bookedSeat));
        }
        catch (Exception e)
        {
            System.out.println("Error Getting Booked Seats");
            System.exit(0);
        }

        return bookedSeats;
    }
}
