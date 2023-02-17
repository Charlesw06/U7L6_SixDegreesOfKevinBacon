import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name  = "";
        System.out.println("Welcome to Six Degrees of Kevin Bacon!\n------------------------------");
        while (!name.equals("q")) {
            ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
            Scanner s = new Scanner(System.in);
            System.out.print("Enter an actor's name or (q) to quit: ");
            name = s.nextLine();

            int baconNum = 0;
            String resultSeq = name;
            ArrayList<String> coStars = MovieDatabaseBuilder.getBaconCoStars("src/KevinBaconActors");
            ArrayList<SimpleMovie> coStarMovies = MovieDatabaseBuilder.getCoStarsMovies("src/KevinBaconActorMovies");

            String commonMovie = "";
            String currentName = name;

            if (!name.equals("q")) {
                while (!currentName.equals("Kevin Bacon") && (baconNum < 3)) {
                    if (coStars.contains(currentName)) {
                        for (SimpleMovie movie : coStarMovies) {
                            if (movie.getActors().contains("Kevin Bacon") && movie.getActors().contains(currentName)) {
                                commonMovie = movie.getTitle();
                            }
                        }
                        resultSeq += " -> " + commonMovie + " -> Kevin Bacon";
                        baconNum += 1;
                        currentName = "Kevin Bacon";
                    }
                    else {
                        for (int i = 0; i < coStarMovies.size(); i++) {
                            for (int c = 0; c < coStars.size(); c++) {
                                if (coStarMovies.get(i).getActors().contains(currentName) && (coStarMovies.get(i).getActors().contains(coStars.get(c))) && !(coStars.contains(currentName))) {
                                    commonMovie = coStarMovies.get(i).getTitle();
                                    currentName = coStars.get(c);
                                    i = coStarMovies.size();
                                    c = coStars.size();
                                }
                            }
                        }
                        if (currentName.equals(name)) {
                            ArrayList<SimpleMovie> actorMovies = new ArrayList<SimpleMovie>();
                            for (SimpleMovie movie : movies) {
                                if (movie.getActors().contains(name)) {
                                    actorMovies.add(movie);
                                }
                            }
                            SimpleMovie maxCastMovie = null;
                            int maxCast = 0;
                            for (SimpleMovie movie : actorMovies) {
                                if (movie.getActors().size() > maxCast) {
                                    maxCast = movie.getActors().size();
                                    maxCastMovie = movie;
                                }
                            }
                            commonMovie = maxCastMovie.getTitle();
                            ArrayList<Integer> actorMovieNum = new ArrayList<Integer>();
                            for (int i = 0; i < maxCastMovie.getActors().size(); i++) {
                                actorMovieNum.add(0);
                            }
                            for (SimpleMovie movie : movies) {
                                for (int i = 0; i < maxCastMovie.getActors().size(); i++) {
                                    if (movie.getActors().contains(maxCastMovie.getActors().get(i))) {
                                        actorMovieNum.set(i, actorMovieNum.get(i)+1);
                                    }
                                }
                            }
                            int maxMovies = 0;
                            for (int i = 0; i < actorMovieNum.size(); i++) {
                                if (actorMovieNum.get(i) > maxMovies) {
                                    maxMovies = actorMovieNum.get(i);
                                    currentName = maxCastMovie.getActors().get(i);
                                }
                            }
                        }
                        resultSeq += " -> " + commonMovie + " -> " + currentName;
                        baconNum += 1;
                    }
                }
            }

            if (!name.equals("q") && (currentName.equals("Kevin Bacon"))) {
                System.out.println("Actor chosen: " + name);
                System.out.println(resultSeq);
                System.out.println("Bacon number of: " + baconNum);
                System.out.println("-------------------------------------");
            }
            if (!(currentName.equals("Kevin Bacon"))) {
                System.out.println("Actor chosen: " + name);
                System.out.println("Actor has Bacon Number over 3 or can not be connected to Kevin Bacon.");
                System.out.println("-------------------------------------");
            }
        }
    }
}