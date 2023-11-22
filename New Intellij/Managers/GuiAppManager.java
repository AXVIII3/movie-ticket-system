package Managers;

import GUI.Dialogs.AuthenticateDialog;
import GUI.Screens.*;
import GUI.Window;
import Utilities.CinemaHall;
import Utilities.Date;
import Utilities.Movie;

import java.util.ArrayList;

public class GuiAppManager
{
    private static Window window;

    public static Movie movie;
    public static CinemaHall cinemaHall;
    public static Date date;
    public static int timeIndex;

    private static SeatsSelectScreen seatSelectScreen;

    public static void Initialize()
    {
        window = new Window();
        window.addScreen(new MainScreen());
        window.addScreen(new MovieSelectScreen());
        for (Movie movie : BookingManager.Movies) window.addScreen(new MovieDetailsScreen(movie));
        seatSelectScreen = new SeatsSelectScreen(window);
        window.addScreen(seatSelectScreen);
        window.addScreen(new EmptyScreen());
    }

    public static void SetData(Movie _movie, String _hall, String dt, String tm)
    {
        movie = _movie;
        for (CinemaHall hall : movie.cinemaHalls)
        {
            if (_hall.trim().equals(hall.name))
            {
                cinemaHall = hall;

                for (Date _date : hall.dates)
                    if (_date.date.equals(dt)) date = _date;

                int i = 0;
                for (String _time : date.times)
                {
                    if (tm.equals(_time)) break;
                    i++;
                }

                timeIndex = i;
            }
        }
    }

    public static void StartGUI()
    {
        window.setVisible(true);
        window.openScreen("Main");
    }

    public static void StartAuthentication()
    {
        if (AccountsManager.currentAccount != null)
        {
            StartBooking();
            return;
        }
        window.openScreen("Empty Screen");
        new AuthenticateDialog(window, movie);
    }
    public static void StartBooking()
    {
        window.openScreen("Seat Select");
        seatSelectScreen.PopulateSeats(movie, cinemaHall, date, timeIndex);
    }

    public static void BookTickets(ArrayList<Integer> seats, int[] ticketsPerSection)
    {
        for (Integer seat : seats) System.out.println(seat.toString());
    }
}
