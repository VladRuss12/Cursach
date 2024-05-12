package lab4.service;

import lab4.entity.User;

public interface UserService extends PersonService<User> {
    User findByEmail(String email);
}
