package Utilities;

import Managers.BookingManager;

public class HallLayout
{
    public static final int FRONT_ROWS = 2;
    public static final int NORMAL_ROWS = 5;
    public static final int PREMIUM_ROWS = 2;
    public static final int COLUMNS = 16;
    public static int TotalSeats() { return (FRONT_ROWS + NORMAL_ROWS + PREMIUM_ROWS) * COLUMNS; }

    public static int[][] GetFrontArrangement(Movie movie)
    {
        int k = 1;
        int[][] frontRow = new int[FRONT_ROWS][COLUMNS];
        for (int i = 0; i < FRONT_ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++, k++)
            {
                if (!BookingManager.GetBookedSeats(movie).contains(k)) frontRow[i][j] = k;
                else frontRow[i][j] = -1;
            }
        }

        return frontRow;
    }

    public static int[][] GetNormalArrangement(Movie movie)
    {
        int k = FRONT_ROWS * COLUMNS;
        int[][] normalRow = new int[NORMAL_ROWS][COLUMNS];
        for (int i = 0; i < NORMAL_ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++, k++)
            {
                if (!BookingManager.GetBookedSeats(movie).contains(k)) normalRow[i][j] = k;
                else normalRow[i][j] = -1;
            }
        }

        return normalRow;
    }

    public static int[][] GetPremiumArrangement(Movie movie)
    {
        int k = (FRONT_ROWS + NORMAL_ROWS) * COLUMNS;
        int[][] premiumRow = new int[PREMIUM_ROWS][COLUMNS];
        for (int i = 0; i < PREMIUM_ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++, k++)
            {
                if (!BookingManager.GetBookedSeats(movie).contains(k)) premiumRow[i][j] = k;
                else premiumRow[i][j] = -1;
            }
        }

        return premiumRow;
    }
}
