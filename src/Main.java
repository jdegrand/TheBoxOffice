import java.util.Scanner;

/**
 * CHESS Health Co-op Code Test 2020 - The Box Office
 * @author degrandjoe@gmail.com
 */
public class Main {
    /**
     * The main function is what handles all of the user input, and runs the main program.
     * First the user is asked to input 5 movies and the number of tickets available for that movie, in the form of
     * movie_name, tickets_available
     * Next it displays all of the movies and the number of tickets they have available, then asks the customer what
     * movie they would like to see and how many tickets they would like to buy.
     * If the user enters blank input then the day is over and an overall report of the day is printed.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BoxOffice boxOffice = new BoxOffice();
        int movieCount = 0;
        System.out.println("Enter 5 movies in the format: name, tickets_available");
        while (movieCount != 5) {       // Takes in user input for 5 movies and their tickets available
            String inp = scanner.nextLine();
            String[] movie_args = inp.split(",");
            if (movie_args.length != 2) {
                System.out.println("Invalid entry!");
            } else {
                Movie movie = new Movie(movie_args[0], Integer.valueOf(movie_args[1].trim()));
                if (boxOffice.addMovie(movie_args[0], movie)) {
                    movieCount++;
                } else {
                    System.out.println("Movie already exists!");
                }
            }
        }
        boolean day_over = false;
        while (!day_over) {         // Repeat until the user enters a blank line
            System.out.println();
            boxOffice.printMovies();
            System.out.println();
            boolean movie_picked = false;
            Movie selected = null;
            while (!movie_picked) {
                System.out.println("What movie would you like to see? (blank to end day)");
                String inp = scanner.nextLine();
                if (inp.length() == 0) {
                    day_over = true;
                    break;
                }
                selected = boxOffice.getMovie(inp);
                if (selected != null) {
                    if (selected.getTickets_available() != 0) {
                        movie_picked = true;
                    } else {
                        System.out.println("There are no seats left for that movie! Please choose a different one...");
                    }
                } else {
                    System.out.println("Movie doesn't exist!");
                }
            }
            if (day_over) {
                break;
            }
            System.out.format("There are currently %d tickets left for that movie. How many would you like to buy?\n", selected.getTickets_available());
            boolean valid_number = false;
            while (!valid_number) {
                String inp = scanner.nextLine();
                int tickets = Integer.valueOf(inp);
                if (tickets > selected.getTickets_available()) {
                    System.out.println("There isn't enough tickets left! Please try a different number of tickets.");
                } else if (tickets == 0) {
                    System.out.println("Please pick at least 1 ticket...");
                } else {
                    System.out.println("Enjoy the movie!");
                    System.out.println();
                    boxOffice.buyTickets(selected.getName(), tickets);
                    valid_number = true;
                }
            }
        }
        boxOffice.printReport();
    }
}
