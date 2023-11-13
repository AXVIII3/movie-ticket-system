package managers;

import java.util.ArrayList;

public class BookingManager
{
    // SEATING INFORMATION
    protected static final int seatRows = 9;
    protected static final int seatColumns = 16;
    protected static int TotalSeats() { return seatRows * seatColumns; }

    // COST INFORMATION
    protected static final float tax = 5.0f;

    // MOVIES LIST
    protected static Movie[] movies;

    public static void InitializeMovieList()
    {
        ArrayList<String> data = DataManager.ReadFile(DataManager.movieNamesPath);
        assert data != null;
        movies = new Movie[data.size()];
        for (int i = 0; i < data.size(); i++) movies[i] = new Movie(data.get(i).split("#"));
    }

    protected static int[] GetUnavailableSeats(Movie movie, int dateIndex)
    {
        ArrayList<String> data = DataManager.ReadFile(DataManager.seatsLeftPath + movie.name + "/" + movie.dates[dateIndex] + ".txt");
        if (data == null) return new int[0];
        int[] bookedSeats = new int[data.size()];
        for (int i = 0; i < bookedSeats.length; i++) { bookedSeats[i] = Integer.parseInt(data.get(i)); }
        return bookedSeats;
    }

    protected static int[] CalculateCost (int movieIndex, int numberOfSeats)
    {
        return new int[] {
                (int) (numberOfSeats * (float) movies[movieIndex].seatCost * (100.0f + tax) * 0.01f),
                (int) tax
        };
    }

    protected static void BookTicket (Movie movie, int[] seats, int dateIndex)
    {
        String path = DataManager.seatsLeftPath + movie.name + "/" + movie.dates[dateIndex] + ".txt";
        for (int seat : seats) DataManager.WriteToFile(path , Integer.toString(seat), true);
    }
}

class Movie
{
    protected String name;
    protected String duration;
    protected String time;
    protected int seatCost;
    protected int[] dates;
    protected String[] formattedDates;

    public Movie (String[] details)
    {
        name = details[0];

        int[] du = minutesToHours(Integer.parseInt(details[1])) ;
        duration = du[0] + "hours" + (du[1] > 0 ? ", " + du[1] + "minutes" : "");

        time = formatTime(minutesToHours(Integer.parseInt(details[2])));

        seatCost = Integer.parseInt(details[3]);

        String[] dt = details[4].split(",");
        dates = new int[dt.length];
        formattedDates = new String[dt.length];
        for (int i = 0; i < dates.length; i++)
        {
            dates[i] = Integer.parseInt(dt[i]);
            formattedDates[i] = formatDate(dt[i]);
        }
    }

    private static int[] minutesToHours (int minutes)
    { return new int[] {minutes / 60, minutes % 60}; }
    private static String formatTime (int[] time)
    { return (time[0] > 12 ? time[0] - 12 : time[0]) + ":" + (time[1] < 10 ? "0" + time[1] : time[1]) + (time[0] > 12 ? "pm": "am"); }
    private static String formatDate (String date)
    {
        String day = date.substring(0, 2);
        String month = date.substring(2, 4);

        switch (Byte.parseByte(day))
        {
            case 1: day += "1st"; break;
            case 2: day += "2nd"; break;
            case 3: day += "3rd"; break;
            case 4: day += "4th"; break;
            case 5: day += "5th"; break;
            case 6: day += "6th"; break;
            case 7: day += "7th"; break;
            case 8: day += "8th"; break;
            case 9: day += "9th"; break;
            default: day += "th"; break;
        }
        switch (Byte.parseByte(month))
        {
            case 1: month = "January"; break;
            case 2: month = "February"; break;
            case 3: month = "March"; break;
            case 4: month = "April"; break;
            case 5: month = "May"; break;
            case 6: month = "June"; break;
            case 7: month = "July"; break;
            case 8: month = "August"; break;
            case 9: month = "September"; break;
            case 10: month = "October"; break;
            case 11: month = "November"; break;
            case 12: month = "December"; break;
        }

        return day + " " + month;
    }
}