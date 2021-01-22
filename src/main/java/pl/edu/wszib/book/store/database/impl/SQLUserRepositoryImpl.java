package pl.edu.wszib.book.store.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.IUsersRepository;
import pl.edu.wszib.book.store.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserRepositoryImpl implements IUsersRepository {

    @Autowired
    Connection connection;

    @Override
    public User authenticate(User user) {
        String sql = "SELECT * FROM tuser WHERE login = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User userFromDB = new User();
                userFromDB.setId(resultSet.getInt("id"));
                userFromDB.setLogin(resultSet.getString("login"));
                userFromDB.setPass(resultSet.getString("pass"));
                userFromDB.setRole(User.Role.valueOf(resultSet.getString("role")));

                if(userFromDB.getPass().equals(user.getPass())) {
                    return userFromDB;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean register(User user) {
        if(isLoginInDB(user.getLogin())) {
            return false;
        }

        String sql = "INSERT INTO tuser (login, pass, role) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPass());
            preparedStatement.setString(3, user.getRole().toString());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    private boolean isLoginInDB(String login) {
        String sql = "SELECT * FROM tuser WHERE login = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return true;
        }

        return false;
    }
}
