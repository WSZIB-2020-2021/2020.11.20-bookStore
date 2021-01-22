package pl.edu.wszib.book.store.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateBookDAOImpl implements IBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Book WHERE id = :id");
        query.setParameter("id", id);
        Book book = null;
        try {
            book = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nie znaleziono książki !!");
        }
        session.close();
        return book;
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.edu.wszib.book.store.model.Book");
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }
}
