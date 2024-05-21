package Cinema.service.impl;


import Cinema.entity.Review;
import Cinema.entity.User;
import Cinema.repository.ReviewRepository;
import Cinema.repository.UserRepository;
import Cinema.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }
    @Override
    public User read(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        // Сохраняем пользователя
        userRepository.save(user);

        // Сохраняем каждый отзыв пользователя
        reviewRepository.saveAll(user.getReviews());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        // Удаляем отзывы пользователя
        reviewRepository.deleteAll(user.getReviews());
        // Удаляем пользователя
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void edit(User updatedUser) {
        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + updatedUser.getId() + " not found"));

        // Обновляем информацию о пользователе
        user.setName(updatedUser.getName());
        user.setDateOfBirth(updatedUser.getDateOfBirth());
        user.setGender(updatedUser.getGender());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        // Обновляем отзывы пользователя
        user.setReviews(updatedUser.getReviews());
        reviewRepository.saveAll(user.getReviews());

        // Сохраняем обновленного пользователя
        userRepository.save(user);
    }



    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByDateOfBirth(Date dateOfBirth) {
        return userRepository.findByDateOfBirth(dateOfBirth);
    }

    @Override
    public List<User> findByGender(String gender) {
        return userRepository.findByGender(gender);
    }
}
