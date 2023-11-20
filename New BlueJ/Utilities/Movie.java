package Utilities;

public class Movie
{
    private static final String THUMBNAIL_PATH = "Assets/Thumbnail";
    private static final String POSTER_PATH = "Assets/Poster";

    public String name;
    public String tagline;
    public String description;
    public String directors;
    public String writers;
    public String stars;
    public String genre;
    public String rating;
    public String duration;
    public String datesStr;
    public String[] dates;
    public String timesStr;
    public String[] times;
    public String costsStr;
    public int costs[];

    public Movie(String[] data)
    {
        String[] fullName = data[0].trim().split(": ");
        name = fullName[0];
        tagline = fullName.length > 1 ? fullName[1] : "";

        description = data[1].trim();
        directors = data[2].trim();
        writers = data[3].trim();
        stars = data[4].trim();
        genre = data[5].trim();
        rating = data[6].trim();
        duration = data[7].trim();
        datesStr = data[8].trim();
        dates = datesStr.split(", ");
        timesStr = data[9].trim();
        times = timesStr.split(", ");
        costsStr = data[10].trim();

        String[] _costs = costsStr.split(", ");
        costs = new int[_costs.length];
        for (int i = 0; i < _costs.length; i++)
            costs[i] = Integer.parseInt(_costs[i]);
    }

    public void DisplayDetails()
    {
        System.out.println("🎥 " + name.toUpperCase() + " 🎥");
        System.out.println("📃 Description: " + description);
        System.out.println("🎬 Director(s): " + directors);
        System.out.println("✒️ Writer(s): " + writers);
        System.out.println("🎭 Stars: " + stars);
        System.out.println("📚 Genre: " + genre);
        System.out.println("⭐ Rating: " + rating);
        System.out.println("⏱️ Duration: " + duration);
        System.out.println("📆️ Available Date(s): " + datesStr);
        System.out.println("⌚ Play Time(s): " + timesStr);
    }
}
