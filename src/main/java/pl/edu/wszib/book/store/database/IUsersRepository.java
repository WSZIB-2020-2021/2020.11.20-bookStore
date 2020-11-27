package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.User;

public interface IUsersRepository {
    boolean authenticate(User user);
}
