package Cinema.service;

import Cinema.entity.User;

public interface UserService extends PersonService<User> {
    User findByEmail(String email);
}
