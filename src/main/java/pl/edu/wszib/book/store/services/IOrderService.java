package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.Order;

public interface IOrderService {
    void saveOrder(Order order);
    Order getOrderById(int id);
}
