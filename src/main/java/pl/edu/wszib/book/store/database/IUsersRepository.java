package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.User;

public interface IUsersRepository {
    User authenticate(User user);
    boolean register(User user);
}
