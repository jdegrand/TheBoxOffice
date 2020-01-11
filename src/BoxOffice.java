import java.util.HashMap;

/**
 * The BoxOffice class keeps track of the total numbers of sales and the movies currently playing
 * @author degrandjoe@gmail.com
 */
public class BoxOffice {
    private HashMap<String, Movie> movies;
    private int sales;
    private int max_name_length;        // To format printing in movie table

    /**
     * The BoxOffice uses a HashMap to store movies with their name as the key
     */
    public BoxOffice() {
        this.movies = new HashMap<>();
        this.sales = 0;
        this.max_name_length = 0;
    }

    /**
     * Adds a movie to the movie HashMap id it doesn't already exist
     * @param name: The name of the movie to add for the key
     * @param movie: The movie object to store as the value
     * @return: Returns false if the movie already exists and true upon success
     */
    public boolean addMovie(String name, Movie movie) {
        if (this.movies.containsKey(name)) {
            return false;
        } else {
            this.movies.put(name, movie);
            if (name.length() > this.max_name_length) { // Keep track of the movie with the longest name for printing
                this.max_name_length = name.length();
            }
            return true;
        }
    }

    /**
     * Gets a movie from the HashMap, if it exists
     * @param name: The name of the movie to retrieve
     * @return: Returns the movie if it exists in the HashMap, null else wise
     */
    public Movie getMovie(String name) {
        if (this.movies.containsKey(name)) {
            return movies.get(name);
        } else {
            return null;
        }
    }

    /**
     * When a user actually chooses a number of tickets to buy for a movie, this function is called. Helps keep track of
     * all of the sales
     * @param name: The name of the movie the user wants to buy tickets for
     * @param amount: The number of tickets to buy for that movie
     */
    public void buyTickets(String name, int amount) {
        this.sales += amount;
        this.movies.get(name).buySeats(amount);
    }

    /**
     * Prints all of the movies and their respective amounts of tickets available in a table format
     */
    public void printMovies() {
        System.out.format("%s  %" + (this.max_name_length - 5 + 2) + "s\n", "Movie", "Seats");
        for (int i = 0; i < 18; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (HashMap.Entry<String, Movie> item : movies.entrySet()) {
            String name = item.getKey();
            Movie movie = item.getValue();
            System.out.format("%s  %" + (this.max_name_length - name.length() + 2) + "d\n", name, movie.getTickets_available());
        }
    }

    /**
     * Prints how each movie sold that day, with the number of tickets each movie told and the number of tickets each
     * movie had left to sell. Also prints total number of tickets sold all together
     */
    public void printReport() {
        for (HashMap.Entry<String, Movie> item : movies.entrySet()) {
            String name = item.getKey();
            Movie movie = item.getValue();
            System.out.format("%s sold %d tickets today with %d remaining\n", name, movie.getTickets_sold(), movie.getTickets_available());
        }
        System.out.println();
        System.out.format("A total of %d tickets were sold today!\n", this.sales);
        System.out.println();
    }
}
