package managers;

import java.util.Scanner;

public class ConsoleManager
{
    public static Scanner scanner;
    private static int movieIndex;

    public static void Start ()
    {
        System.out.flush();
        System.out.println("\nShowtime Hub 🎬");
        System.out.println("Incredible cinematic experiences since 2006!");

        System.out.print("\nPress C to continue or E to exit: ");
        switch (scanner.next().toLowerCase().charAt(0))
        {
            case 'c': SelectMovieMessage(); break;
            case 'e': System.out.println("Exiting program."); break;
            default: System.out.println("Invalid choice! Retry."); Start();
        }
    }

    private static void SelectMovieMessage ()
    {
        System.out.println("\nPlease select a movie");
        int i = 0;
        for ( ; i < BookingManager.movies.length; i++)
        {
            System.out.println((i + 1) + ") " + BookingManager.movies[i].name);
            System.out.println("   Starts at: " + BookingManager.movies[i].time);
            System.out.println("   Duration: " + BookingManager.movies[i].duration);
            System.out.print("   Dates: ");
            for (int j = 0; j < BookingManager.movies[i].formattedDates.length; j++)
                System.out.print((j == 0 ? "" : ", ") + BookingManager.movies[i].formattedDates[j]);
            System.out.println();
            System.out.println("   Cost (per seat): " + BookingManager.movies[i].seatCost);
        }
        System.out.println((i + 1) + ") Exit");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        if (choice == (i + 1)) System.out.println("\nExiting program!");
        else if (choice > 0 && choice <= i)
        {
            movieIndex = choice - 1;
            ConfigureBookingDate();
        }
        else
        {
            System.out.println("Invalid Choice! Retry.");
            SelectMovieMessage();
        }
    }

    private static void ConfigureBookingDate ()
    {
        int dateIndex = 0;
        if (BookingManager.movies[movieIndex].formattedDates.length > 1)
        {
            System.out.println("Which date do you want to book?");
            int i = 1;
            for (; i <= BookingManager.movies[movieIndex].formattedDates.length; i++)
                    System.out.println(i + ") " + BookingManager.movies[movieIndex].formattedDates[i - 1]);
            if (i >= 5) System.out.print("Choice (1/2/.../" + i + "): ");
            else if (i == 4) System.out.print("Choice (1/2/3): ");
            else if (i == 3) System.out.print("Choice (1/2): ");

            dateIndex = scanner.nextInt();
            if (dateIndex >= i || dateIndex <= 0)
            {
                System.out.println("Invalid choice! Retry.");
                ConfigureBookingDate();
                return;
            }

        }

        ConfigureNumberOfSeats(dateIndex);
    }

    private static void ConfigureNumberOfSeats (int dateIndex)
    {
        System.out.print("\nPlease enter the number of seats required (0 to exit): ");
        int numberOfSeats = scanner.nextInt();
        if (numberOfSeats == 0)
        {
            System.out.println("\nExiting program!");
            System.exit(0);
        }

        if (numberOfSeats <= 0 || numberOfSeats > 15)
        {
            System.out.println("Sorry! The " +
                    (numberOfSeats > 15 ? "maximum" : "minimum") +
                    " seat booking limit is " +
                    (numberOfSeats > 15 ? "15!" : "1!"));
            ConfigureNumberOfSeats(dateIndex);
            return;
        }

        ChooseSeats(numberOfSeats, dateIndex);
    }

    private static void ChooseSeats (int numberOfSeats, int dateIndex)
    {
        int[] unavailableSeats = BookingManager.GetUnavailableSeats(BookingManager.movies[movieIndex], dateIndex);

        System.out.println("\nPlease choose your seats (X marked seats are already booked):");
        System.out.println("                                     [Screen this side ↑]");
        int k = 1;
        for (int i = 0; i < BookingManager.seatRows; i++)
        {
            COLUMN_LOOP: for (int j = 0; j < BookingManager.seatColumns; j++, k++)
            {
                for (int unavailableSeat : unavailableSeats)
                {
                    if (unavailableSeat == k)
                    {
                        for (int m = 0;
                             m < (Integer.toString(BookingManager.TotalSeats()).length() - 1);
                             m++)
                            System.out.print(" ");
                        System.out.print("{X} ");
                        continue COLUMN_LOOP;
                    }
                }
                for (int l = 0;
                     l < (Integer.toString(BookingManager.TotalSeats()).length() - Integer.toString(k).length());
                     l++) System.out.print(" ");
                System.out.print("[" + k + "] ");
            }
            System.out.println();
        }

        int[] seats = new int[numberOfSeats];
        for (int i = 1; i <= numberOfSeats; i++)
        {
            System.out.print("Seat " + i + ": ");
            seats[i - 1] = scanner.nextInt();

            if (seats[i - 1] <= 0 || seats[i - 1] > BookingManager.TotalSeats())
            {
                System.out.println("Invalid Seat Number! Retry.");
                i--;
            }
            for (int bookedSeat : BookingManager.GetUnavailableSeats(BookingManager.movies[movieIndex], dateIndex))
                if (seats[i - 1] == bookedSeat)
                {
                    System.out.println("This seat is already booked! Retry.");
                    i--;
                    break;
                }
        }

        FinalScreen(seats, BookingManager.CalculateCost(movieIndex, seats.length), dateIndex);
    }

    private static void FinalScreen (int[] seats, int[] cost, int dateIndex)
    {
        System.out.println("\nBOOKING DETAILS:");
        System.out.println("Movie Name: " + BookingManager.movies[movieIndex].name);
        System.out.println("Duration: " + BookingManager.movies[movieIndex].duration);
        System.out.println("Date and Time: on" + " at " + BookingManager.movies[movieIndex].time);
        System.out.print("Seat Numbers: ");
        for (int seat : seats) System.out.print(seat + " ");
        System.out.println();
        System.out.println("Cost: Rs." + cost[0] + " (Tax: " + cost[1] + "%)");
        System.out.print("Enter Y to confirm and N to cancel: ");

        switch (scanner.next().toLowerCase().charAt(0))
        {
            case 'y': System.out.println("\nBooking confirmed! Enjoy and don't forget your popcorn 🍿");
                      System.out.println("Returning to main menu.");
                      BookingManager.BookTicket(BookingManager.movies[movieIndex], seats, dateIndex);
                      Start();
                      break;
            case 'n': System.out.println("\nBooking cancelled! Returning to movie menu.");
                      SelectMovieMessage();
            default: System.out.println("Invalid choice! Retry.");
                     FinalScreen(seats, cost, dateIndex);
        }
    }
}
