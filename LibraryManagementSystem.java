import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title.toLowerCase();
        this.author = author;
    }

    public String toString() {
        return "ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

public class LibraryManagementSystem {
    public static Book linearSearch(List<Book> books, String targetTitle) {
        targetTitle = targetTitle.toLowerCase();
        for (Book book : books) {
            if (book.title.equals(targetTitle)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearch(List<Book> books, String targetTitle) {
        targetTitle = targetTitle.toLowerCase();
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int compare = books.get(mid).title.compareTo(targetTitle);
            if (compare == 0) return books.get(mid);
            else if (compare < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>();

        System.out.print("Enter number of books: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Book " + (i + 1));
            System.out.print("Book ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Author: ");
            String author = scanner.nextLine();

            books.add(new Book(id, title, author));
        }

        System.out.print("Enter title to search: ");
        String searchTitle = scanner.nextLine();

        Book linearResult = linearSearch(books, searchTitle);
        System.out.println("Linear Search Result: " + (linearResult != null ? linearResult : "Book not found"));

        books.sort(Comparator.comparing(b -> b.title));
        Book binaryResult = binarySearch(books, searchTitle);
        System.out.println("Binary Search Result: " + (binaryResult != null ? binaryResult : "Book not found"));

        scanner.close();
    }
}
