package pl.edu.wszib.book.store.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.database.IBooksRepository;
import pl.edu.wszib.book.store.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLBookRepositoryImpl implements IBooksRepository {

    @Autowired
    Connection connection;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("pieces")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }

    @Override
    public Book getBookByISBN(String isbn) {
        try {
            String sql = "SELECT * FROM tbook WHERE isbn = ?;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("pieces"));
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
