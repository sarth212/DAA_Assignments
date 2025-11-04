// Name:Sarthak Deshmukh
// PRN:124B2F004

import java.util.*;

class Movie {
    String title;
    double rating;
    int year, watchTime;

    Movie(String t, double r, int y, int w) {
        title = t; rating = r; year = y; watchTime = w;
    }

    public String toString() {
        return title + " | Rating: " + rating + " | Year: " + year + " | WatchTime: " + watchTime;
    }
}

public class StreamFlix {

    static void quickSort(Movie[] m, int low, int high, String sortBy) {
        if (low < high) {
            int pi = partition(m, low, high, sortBy);
            quickSort(m, low, pi - 1, sortBy);
            quickSort(m, pi + 1, high, sortBy);
        }
    }

    static int partition(Movie[] m, int low, int high, String sortBy) {
        Movie pivot = m[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compare(m[j], pivot, sortBy) < 0) {
                i++;
                Movie temp = m[i]; m[i] = m[j]; m[j] = temp;
            }
        }
        Movie temp = m[i + 1]; m[i + 1] = m[high]; m[high] = temp;
        return i + 1;
    }

    static int compare(Movie a, Movie b, String key) {
        switch (key.toLowerCase()) {
            case "rating": return Double.compare(a.rating, b.rating);
            case "year": return Integer.compare(a.year, b.year);
            case "watchtime": return Integer.compare(a.watchTime, b.watchTime);
            default: return 0;
        }
    }

    public static void main(String[] args) {
        Movie[] movies = {
            new Movie("Inception", 8.8, 2010, 150),
            new Movie("Avengers: Endgame", 8.4, 2019, 180),
            new Movie("Interstellar", 8.6, 2014, 165),
            new Movie("The Dark Knight", 9.0, 2008, 152)
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Sort by (rating/year/watchtime): ");
        String key = sc.nextLine();

        quickSort(movies, 0, movies.length - 1, key);

        System.out.println("\nSorted by " + key + ":");
        for (Movie m : movies) System.out.println(m);
    }
}

