package managers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
public class DataManager
{
    // FILE PATHS
    protected static final String movieNamesPath = "data/MovieNames.txt";
    protected static final String seatsLeftPath = "data/seats/";

    public static void ResetData ()
    {
        try
        {
            File seatsDirectory = new File(seatsLeftPath);
            for(File file : seatsDirectory.listFiles()) file.delete();

            for (Movie movie : BookingManager.movies)
            {
                File dir = new File(seatsLeftPath + movie.name + "/");
                dir.mkdir();
                for (int date : movie.dates)
                {
                    File file = new File(seatsLeftPath + movie.name + "/" + date + ".txt");
                    file.createNewFile();
                }
            }

            System.out.println("Reset data successfully!");

        } catch (Exception e)
        { System.out.println("Error resetting files at '" + seatsLeftPath + "': " + e.getMessage()); }
    }

    protected static void WriteToFile (String filePath, String text, boolean shouldAppend)
    {
        try
        {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file, shouldAppend);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            writer.write(text + "\n");

            bufferedWriter.close();
            writer.close();

        } catch (Exception e)
        { System.out.println("Error writing to file at '" + filePath + "': " + e.getMessage()); }
    }

    protected static ArrayList<String> ReadFile (String filePath)
    {
        try
        {
            File file = new File(filePath);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            ArrayList<String> data = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) data.add(line);

            bufferedReader.close();
            reader.close();

            return data;

        } catch (Exception e)
        {
            System.out.println("Error reading file at '" + filePath + "': " + e.getMessage());
            return null;
        }
    }
}
