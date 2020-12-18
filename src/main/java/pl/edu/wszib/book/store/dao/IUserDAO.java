package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean persistUser(User user);
}
