package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);
    void logout();
    boolean register(RegistrationModel registrationModel);
}
