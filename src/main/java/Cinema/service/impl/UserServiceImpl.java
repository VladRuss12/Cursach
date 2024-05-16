package Cinema.service.impl;

import Cinema.entity.User;
import Cinema.repository.UserRepository;
import Cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl extends PersonServiceImpl<User> implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }


    @Override
    public void edit(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + user.getId() + " not found"));
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        // Обновление специфичных для User полей
        userRepository.save(existingUser);

        // Вызов метода edit из суперкласса для обновления общих полей Person
        super.edit(user);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
