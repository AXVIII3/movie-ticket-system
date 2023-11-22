package Utilities;

public class Movie
{
    private static final String THUMBNAIL_PATH = "Assets/Thumbnail/";
    private static final String POSTER_PATH = "Assets/Poster/";

    public String name;
    public String tagline;
    public String description;
    public String directors;
    public String writers;
    public String stars;
    public String genre;
    public String rating;
    public String releaseYear;
    public String duration;
    public Date[] dates;
    public String[] availableScreens;
    public int[] screensExtraCost;
    public int[] costs;
    public String thumbnailPath;
    public String posterPath;

    public Movie(String[] data)
    {
        String[] fullName = data[0].trim().split(": ");
        name = fullName[0];
        tagline = fullName.length > 1 ? ": " + fullName[1] : "";

        description = data[1].trim();
        directors = data[2].trim();
        writers = data[3].trim();
        stars = data[4].trim();
        genre = data[5].trim();
        rating = data[6].trim();
        releaseYear = data[7].trim();
        duration = data[8].trim();
        String[] _dates = data[9].trim().split(", ");
        String[] times = data[10].trim().split(" # ");
        dates = new Date[_dates.length];
        if (dates.length != times.length)
        {
            System.out.println("Dates and times are out of sync!");
            System.exit(0);
        }
        for (int i = 0; i < dates.length; i++)
            dates[i] = new Date(_dates[i], times[i]);

        String[] _availableScreens = data[11].trim().split(", ");
        availableScreens = new String[_availableScreens.length];
        screensExtraCost = new int[_availableScreens.length];
        for (int i = 0; i < _availableScreens.length; i++)
        {
            availableScreens[i] = _availableScreens[i].trim().split("#")[0];
            screensExtraCost[i] = Integer.parseInt(_availableScreens[i].trim().split("#")[1]);
        }
        thumbnailPath = THUMBNAIL_PATH + name + ".jpg";
        posterPath = POSTER_PATH + name + ".jpg";

        String[] _costs = data[12].trim().split(", ");
        costs = new int[_costs.length];
        for (int i = 0; i < _costs.length; i++)
            costs[i] = Integer.parseInt(_costs[i]);
    }

    public void DisplayDetails()
    {
        System.out.println("🎥 " + name + tagline + " 🎥");
        System.out.println("📃 Description: " + description);
        System.out.println("🎬 Director(s): " + directors);
        System.out.println("✒️ Writer(s): " + writers);
        System.out.println("🎭 Stars: " + stars);
        System.out.println("📚 Genre: " + genre);
        System.out.println("⭐ Rating: " + rating);
        System.out.println("⏱️ Duration: " + duration);
        System.out.println("📆️ Available Showtimes: ");
        for (int i = 0; i < dates.length; i++)
        {
            System.out.print((i != 0 ? "" : ", ") + dates[i].date + "  (" + dates[i].timesStr + ")");
        }
    }
}
