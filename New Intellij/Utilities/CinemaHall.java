package Utilities;

import Managers.BookingManager;

public class CinemaHall
{
    public String name;
    public Date[] dates;
    public int frontRowCost;
    public int normalRowCost;
    public int premiumRowCost;
    public int frontRows;
    public int normalRows;
    public int premiumRows;
    public int columns;

    public int TotalSeats() { return (frontRows + normalRows + premiumRows) * columns; }

    public CinemaHall(String _name, String _dates, String _times, String[] _rows, int _cols, String[] costs)
    {
        name = _name;
        String[] dts = _dates.trim().split(", ");
        String[] timesPerDate = _times.split(" # ");
        dates = new Date[dts.length];
        for (int i = 0; i < dates.length; i++)
            dates[i] = new Date(dts[i], timesPerDate[i]);
        frontRows = Integer.parseInt(_rows[0].trim());
        normalRows = Integer.parseInt(_rows[1].trim());
        premiumRows = Integer.parseInt(_rows[2].trim());
        columns = _cols;
        frontRowCost = Integer.parseInt(costs[0].trim());
        normalRowCost = Integer.parseInt(costs[1].trim());
        premiumRowCost = Integer.parseInt(costs[2].trim());
    }

    public int[][] GetFrontRowArrangement()
    {
        return null;
    }
    public int[][] GetNormalRowArrangement()
    {
        return null;
    }
    public int[][] GetPremiumRowArrangement()
    {
        return null;
    }
}
