import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class MovieDatabaseBuilder {

    public static ArrayList<SimpleMovie> getMovieDB(String fileName) {
        ArrayList<SimpleMovie> movies = new ArrayList<SimpleMovie>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");
                if (data.length > 1) {
                    SimpleMovie s = new SimpleMovie(data[0], data[1]);
                    movies.add(s);
                }

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return movies;
    }

    public static ArrayList<String> getBaconCoStars(String fileName) {
        ArrayList<String> coStars = new ArrayList<String>();
        try {
            File baconCoStars = new File(fileName);
            Scanner reader = new Scanner(baconCoStars);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                coStars.add(line);
            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return coStars;
    }

    public static ArrayList<SimpleMovie> getCoStarsMovies(String fileName) {
        ArrayList<SimpleMovie> coStarMovies = new ArrayList<SimpleMovie>();
        try {
            File actorData = new File(fileName);
            Scanner reader = new Scanner(actorData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");
                if (data.length > 1) {
                    SimpleMovie s = new SimpleMovie(data[0], data[1]);
                    coStarMovies.add(s);
                }
            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return coStarMovies;
    }
}