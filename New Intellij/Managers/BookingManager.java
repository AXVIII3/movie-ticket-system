package Managers;

import Utilities.CinemaHall;
import Utilities.Date;
import Utilities.Movie;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class BookingManager
{
    public static Movie[] Movies;
    public static final float CGST_PERCENTAGE = 2.5f;
    public static final float SGST_PERCENTAGE = 2.5f;

    public static void Initialize()
    {
        String[] movieData = DataManager.ProcessMovieData();
        Movies = new Movie[movieData.length];

        int i = 0;
        for (String data : movieData)
            Movies[i++] = new Movie(data.trim().split("\\r?\\n"));
    }

    public static int[] BookSeats(Movie movie, CinemaHall hall, Date date, int timeIndex, ArrayList<Integer> seats,
                                  int[] ticketsPerSection)
    {
//        int baseCost =
        int[] returns = {  };

        Path path = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" +
                            hall.name + "/" +
                            date.date + "/" +
                            date.times[timeIndex].replace(":", "_") + ".txt");
        for (Integer seat : seats)
        {
            try
            {
                Files.writeString(path, seat.toString(), StandardOpenOption.APPEND);
            }
            catch (Exception e)
            {
                System.out.println(e + "\nError Writing Booked Seats In " + path);
                System.exit(0);
            }
        }

        return returns;
    }

    public static boolean AreSeatsAvailableIn(Movie movie, CinemaHall hall)
    {
        int k = 0;
        for (int i = 0; i < hall.dates.length; i++)
            if (!AreSeatsAvailableOn(movie, hall, hall.dates[i])) k++;

        return k < hall.dates.length;
    }

    public static boolean AreSeatsAvailableOn(Movie movie, CinemaHall hall, Date date)
    {
        int k = 0;
        for (int i = 0; i < date.times.length; i++)
            if (!AreSeatsAvailableAt(movie, hall, date, i)) k++;

        return k < date.times.length;
    }

    public static boolean AreSeatsAvailableAt(Movie movie, CinemaHall hall, Date date, int timeIndex)
    {
        return BookingManager.GetBookedSeats(movie, hall, date, timeIndex).size() < hall.TotalSeats();
    }

    public static ArrayList<Integer> GetBookedSeats(Movie movie, CinemaHall hall, Date date, int timeIndex)
    {
        ArrayList<Integer> bookedSeats = new ArrayList<>();
        try
        {
            Path file = Path.of(DataManager.SEATS_ARRANGEMENT_PATH + movie.name + "/" +
                                hall.name + "/" +
                                date.date + "/" +
                                date.times[timeIndex].replace(":", "_") + ".txt");
            String[] bookedSeatsRead = Files.readString(file).trim().split("\\r?\\n");

            if (bookedSeatsRead.length == 0) return bookedSeats;

            for (String bookedSeat : bookedSeatsRead)
                if (!bookedSeat.isEmpty() && !bookedSeat.isBlank()) bookedSeats.add(Integer.parseInt(bookedSeat));
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Getting Booked Seats");
            System.exit(0);
        }

        return bookedSeats;
    }
}
