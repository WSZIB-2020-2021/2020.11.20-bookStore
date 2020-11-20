package pl.edu.wszib.book.store.database.impl;

import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.IBooksRepository;
import pl.edu.wszib.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BooksRepositoryImpl implements IBooksRepository {
    private final List<Book> books = new ArrayList<>();

    public BooksRepositoryImpl() {
        this.books.add(new Book("Czysty kod. Podręcznik dobrego programisty",
                "Robert C. Martin",
                "978-83-283-0234-1",
                34.5,
                10));

        this.books.add(new Book("Python w zadaniach. Programowanie dla młodzieży. Poziom podstawowy",
                "Urszula Wiejak, Adrian Wojciechowski",
                "978-83-283-7131-6",
                23.94,
                15));

        this.books.add(new Book("Blockchain. Kurs video. Cyfrowa ewolucja w świecie finansów",
                "Tomasz Waszczyk",
                "978-83-283-7573-4",
                119.00,
                20));

        this.books.add(new Book("Amazon Web Services (AWS). Kurs video. Zostań administratorem systemów IT",
                "Piotr Kośka",
                "978-83-283-7629-8",
                199.00,
                5));
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }
}
