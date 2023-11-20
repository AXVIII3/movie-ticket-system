package managers;

import java.util.ArrayList;

public class BookingManager
{
    // SEATING INFORMATION
    protected static final int seatRows = 9;
    protected static final int seatColumns = 16;
    protected static int TotalSeats() { return seatRows * seatColumns; }

    // MOVIES LIST
    protected static Movie[] movies;

    public static void InitializeMovieList()
    {
        ArrayList<String> data = DataManager.ReadFile(DataManager.movieNamesPath);
        assert data != null;
        movies = new Movie[data.size()];
        for (int i = 0; i < data.size(); i++) movies[i] = new Movie(data.get(i).split("#"));
    }

    protected static int[] GetUnavailableSeats(Movie movie)
    {
        ArrayList<String> data =
            DataManager.ReadFile(
                DataManager.seatsLeftPath +
                        movie.name + "/" + movie.selectedDate + ".txt"
            );
        if (data == null) return new int[0];
        int[] bookedSeats = new int[data.size()];
        for (int i = 0; i < bookedSeats.length; i++) { bookedSeats[i] = Integer.parseInt(data.get(i)); }
        return bookedSeats;
    }

    protected static void BookTicket(Movie movie, int[] seats)
    {
        String path = DataManager.seatsLeftPath + movie.name + "/" + movie.selectedDate + ".txt";
        for (int seat : seats) DataManager.WriteToFile(path , Integer.toString(seat), true);
    }
}
