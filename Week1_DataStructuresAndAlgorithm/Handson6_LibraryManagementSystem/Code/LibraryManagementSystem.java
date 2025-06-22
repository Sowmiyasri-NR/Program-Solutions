import java.util.Arrays;
import java.util.Comparator;

class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title.toLowerCase();
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return "ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

class Library {
    private Book[] books;

    public Library(Book[] books) {
        this.books = books;
    }

    public void linearSearchByTitle(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found (Linear Search): " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found using linear search.");
        }
    }

    public void binarySearchByTitle(String title) {
        Arrays.sort(books, Comparator.comparing(Book::getTitle));
        int left = 0;
        int right = books.length - 1;
        String key = title.toLowerCase();
        while (left <= right) {
            int mid = (left + right) / 2;
            String midTitle = books[mid].getTitle();
            int cmp = midTitle.compareTo(key);
            if (cmp == 0) {
                System.out.println("Found (Binary Search): " + books[mid]);
                return;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Book not found using binary search.");
    }
}

public class LibraryManagementsystem {
    public static void main(String[] args) {
        Book[] bookArray = {
            new Book(101, "The Alchemist", "Paulo Coelho"),
            new Book(102, "Wings of Fire", "A.P.J. Abdul Kalam"),
            new Book(103, "The Hobbit", "J.R.R. Tolkien"),
            new Book(104, "Zero to One", "Peter Thiel"),
            new Book(105, "1984", "George Orwell")
        };

        Library library = new Library(bookArray);

        library.linearSearchByTitle("The Hobbit");
        library.binarySearchByTitle("The Hobbit");

        library.linearSearchByTitle("Unknown Book");
        library.binarySearchByTitle("Unknown Book");
    }
}
