package Managers;

import Utilities.Movie;
import Utilities.RandomSeatsFiller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataManager
{
    // FILE PATHS
    private static final String MOVIES_DATA_PATH = "Data/Movies.txt";
    public static final String SEATS_ARRANGEMENT_PATH = "Data/Seating/";

    public static void Initialize()
    {
        try
        {
            File seatArrangementsDir = new File(SEATS_ARRANGEMENT_PATH);
            if (!seatArrangementsDir.exists()) seatArrangementsDir.mkdir();

            for (Movie movie : BookingManager.Movies)
            {
                String movieDirPath = SEATS_ARRANGEMENT_PATH + movie.name + "/";
                File movieDir = new File(movieDirPath);
                if (!movieDir.exists()) movieDir.mkdir();

                for (String date : movie.dates)
                {
                    String dateDirPath = movieDirPath + date + "/";
                    File dateDir = new File(dateDirPath);
                    if (!dateDir.exists()) dateDir.mkdir();

                    for (String time : movie.times)
                    {
                        File timeFile =
                            new File(dateDirPath + time.replace(":", "_") + ".txt");
                        if (!timeFile.exists())
                        {
                            timeFile.createNewFile();
                            RandomSeatsFiller.Fill(timeFile.toPath());
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error Initializing Data!");
            System.exit(0);
        }
    }

    public static String[] ProcessMovieData()
    {
        try
        {
            Path file = Path.of(MOVIES_DATA_PATH);
            String data = Files.readString(file);

            return data.trim().split("\\r?\\n\\r?\\n");
        }
        catch (Exception e)
        {
            System.out.println("Error Processing Movie Data!");
            System.exit(0);
            return null;
        }
    }
}
