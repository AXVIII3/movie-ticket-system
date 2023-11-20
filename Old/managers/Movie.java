package managers;

public class Movie
{
    protected String name;
    protected String duration;
    protected String time;
    protected int seatCost;
    protected int[] dates;
    protected int selectedDate = 0;
    protected String[] formattedDates;
    protected String rating;
    protected String genre;
    protected String description;
    protected String thumbnailFileName;
    protected String cast;
    protected int CalculateCost(int numberOfSeats)
    { return numberOfSeats * seatCost; }

    public Movie(String[] details)
    {
        name = details[0];

        int[] du = minutesToHours(Integer.parseInt(details[1])) ;
        duration = du[0] + "hr" + (du[1] > 0 ? ", " + du[1] + "min" : "");

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

        rating = details[5] + "/10";
        genre = details[6];
        description = details[7];
        thumbnailFileName = details[8];
        cast = details[9];
    }

    private static int[] minutesToHours(int minutes)
    { return new int[] {minutes / 60, minutes % 60}; }
    private static String formatTime(int[] time)
    { return (time[0] > 12 ? time[0] - 12 : time[0]) + ":" + (time[1] < 10 ? "0" + time[1] : time[1]) + (time[0] > 12 ? "pm": "am"); }
    private static String formatDate(String date)
    {
        String day = date.substring(0, 2);
        String month = date.substring(2, 4);

        switch (Byte.parseByte(day))
        {
            case 1: day = "1st"; break;
            case 2: day = "2nd"; break;
            case 3: day = "3rd"; break;
            default: day = Byte.parseByte(day) + "th"; break;
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
