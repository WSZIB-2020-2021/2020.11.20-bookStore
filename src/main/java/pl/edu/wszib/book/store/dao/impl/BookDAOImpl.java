package pl.edu.wszib.book.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements IBookDAO {

    @Autowired
    Connection connection;

    @Override
    public Book getBookById(int id) {
        String sql = "SELECT * FROM tbook WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("pieces"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE tbook SET title = ?, author = ?, isbn = ?, price = ?, pieces = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setInt(5, book.getPieces());
            preparedStatement.setInt(6, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
}
