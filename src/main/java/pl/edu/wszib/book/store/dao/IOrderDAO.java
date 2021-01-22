package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.Order;

public interface IOrderDAO {
    void saveOrder(Order order);
    Order getOrderById(int id);
}
