package Managers;

import Utilities.Date;
import Utilities.HallLayout;
import Utilities.Movie;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class BookingManager
{
    public static Movie[] Movies;
    public static final int NORMAL_EXTRA_COST = 30;
    public static final int PREMIUM_EXTRA_COST = 50;

    public static void Initialize()
    {
        String[] movieData = DataManager.ProcessMovieData();
        Movies = new Movie[movieData.length];

        int i = 0;
        for (String data : movieData)
            Movies[i++] = new Movie(data.trim().split("\\r?\\n"));
    }

    public static void BookSeats(Movie movie, int[] seats, Date date, int timeIndex)
    {
        Path path = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" +
                            date.date + "/" +
                            date.times[timeIndex].replace(":", "_") + ".txt");
        for (int seat : seats)
        {
            try
            {
                Files.writeString(path, String.valueOf(seat), StandardOpenOption.APPEND);
            }
            catch (Exception e)
            {
                System.out.println(e + "\nError Writing Booked Seats In " + path);
                System.exit(0);
            }
        }
    }

    public static boolean AreSeatsAvailableOn(Movie movie, Date date)
    {
        for (int i = 0; i < date.times.length; i++)
            if (BookingManager.GetBookedSeats(movie, date, i).size() < HallLayout.TotalSeats()) return true;

        return false;
    }

    public static boolean AreSeatsAvailableAt(Movie movie, Date date, int timeIndex)
    {
        return !(BookingManager.GetBookedSeats(movie, date, timeIndex).size() >= HallLayout.TotalSeats());
    }

    public static ArrayList<Integer> GetBookedSeats(Movie movie, Date date, int timeIndex)
    {
        ArrayList<Integer> bookedSeats = new ArrayList<>();
        try
        {
            Path file = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" +
                                date.date + "/" +
                                date.times[timeIndex].replace(":", "_") + ".txt");
            for (String bookedSeat : Files.readString(file).trim().split("\\r?\\n"))
                bookedSeats.add(Integer.parseInt(bookedSeat));
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Getting Booked Seats");
            System.exit(0);
        }

        return bookedSeats;
    }
}
