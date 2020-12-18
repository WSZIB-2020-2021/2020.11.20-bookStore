package pl.edu.wszib.book.store.database.impl;

import pl.edu.wszib.book.store.database.IUsersRepository;
import pl.edu.wszib.book.store.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryImpl implements IUsersRepository {

    private final List<User> users = new ArrayList<>();

    public UsersRepositoryImpl() {
        this.users.add(new User(0, "mateusz", "mateusz", User.Role.USER));
        this.users.add(new User(0, "admin", "admin", User.Role.ADMIN));
    }

    @Override
    public User authenticate(User user) {
        for(User userFormDatabase : this.users) {
            if(userFormDatabase.getLogin().equals(user.getLogin())
                    && userFormDatabase.getPass().equals(user.getPass())) {
                return userFormDatabase;
            }
        }

        return null;
    }

    @Override
    public boolean register(User user) {
        if(isLoginInDB(user.getLogin())) {
            return false;
        }

        this.users.add(user);
        return true;
    }

    private boolean isLoginInDB(String login) {
        for(User user : users) {
            if(user.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }
}
