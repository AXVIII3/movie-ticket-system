package Managers;

import GUI.Dialogs.AuthenticateDialog;
import GUI.Screens.*;
import GUI.Window;
import Utilities.Date;
import Utilities.Movie;

public class GuiAppManager
{
    private static Window window;

    public static Movie movie;
    public static Date date;
    public static int timeIndex;
    public static int screenTypeIndex;

    public static void Initialize()
    {
        window = new Window();
        window.addScreen(new MainScreen());
        window.addScreen(new MovieSelectScreen());
        for (Movie movie : BookingManager.Movies) window.addScreen(new MovieDetailsScreen(movie));
        window.addScreen(new SeatsSelectScreen());
        window.addScreen(new EmptyScreen());
    }

    public static void SetData(Movie _movie, String dt, String tm, int screenStyle)
    {
        movie = _movie;
        for (Date _date : movie.dates)
            if (_date.date.equals(dt)) date = _date;

        int i = 0;
        for (String _time : date.times)
        {
            if (tm.equals(_time)) break;
            i++;
        }
        timeIndex = i;

        screenTypeIndex = screenStyle;
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
    }
}
