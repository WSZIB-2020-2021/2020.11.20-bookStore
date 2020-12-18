package pl.edu.wszib.book.store.services;

public interface IBasketService {
    double calculateTotal();
    void addBookByIdToBasket(int id);
}
