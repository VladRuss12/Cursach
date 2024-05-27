package Cinema.service;

import Cinema.entity.User;

import java.util.List;

public interface UserService extends PersonService<User> {
    User findByEmail(String email);
    User getByUsername(String username);
    void getAdmin();
    void create(User user);
    List<User> getTopReviewers();
}
