/**
 * The Movie class keeps track of how many tickets a movie has available and how many tickets it has already sold
 * @author degrandjoe@gmail.com
 */
public class Movie {
    private String name;
    private int tickets_available;
    private int tickets_sold;

    /**
     * The Movie is created given a name and number of starting tickets available, starting with zero tickets sold
     * @param name: The name to create the movie with
     * @param tickets_available: The number of tickets the movie has left to sell
     */
    public Movie(String name, int tickets_available) {
        this.name = name;
        this.tickets_available = tickets_available;
        this.tickets_sold = 0;
    }

    /**
     * Retrieves the number of tickets that are still available
     * @return: The number of tickets left to sell
     */
    public int getTickets_available() {
        return tickets_available;
    }

    /**
     * Retrieves the number of tickets that have already been sold for this specific movie
     * @return: The number of tickets already sold for this specific movie
     */
    public int getTickets_sold() {
        return tickets_sold;
    }

    /**
     * Retrieves the name of the current Movie
     * @return: Returns the name of this movie
     */
    public String getName() {
        return name;
    }

    /**
     * Buying seats subtracts from the number of seats/tickets available and adds to the number of tickets sold
     * @param amount
     */
    public void buySeats(int amount) {
        this.tickets_available -= amount;
        this.tickets_sold += amount;
    }
}
