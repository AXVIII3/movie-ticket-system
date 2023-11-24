package Managers;

import Utilities.CinemaHall;
import Utilities.Date;
import Utilities.Movie;

import java.util.Scanner;

public class ConsoleAppManager
{
    private static Movie movie;
    private static CinemaHall hall;
    private static Date date;
    private static int timeIndex;

    private static final Scanner scanner = new Scanner(System.in);;

    public static void StartConsole()
    {
        System.out.println("Showtime Hub 🎬");
        System.out.println("Incredible cinematic experiences since 2006!");
        System.out.println("\nEnter B to start booking or E to exit");
        System.out.print("Choice: ");
        switch (Character.toLowerCase(scanner.next().charAt(0)))
        {
            case 'b': StartBooking();
                      break;
            case 'e': break;
            default:
                System.out.println("\nInvalid choice! Retry");
                StartConsole();
        }
    }

    public static void StartBooking()
    {
        System.out.println("\nSelect movie:");
        int i = 1;
        for (Movie movie : BookingManager.Movies)
            System.out.println(i++ + ") " + movie.name);

    }
}
