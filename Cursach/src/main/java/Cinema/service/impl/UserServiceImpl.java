package Cinema.service.impl;


import Cinema.entity.Review;
import Cinema.entity.User;
import Cinema.entity.enums.UserRole;
import Cinema.exeption.ResourceNotFoundException;
import Cinema.repository.ReviewRepository;
import Cinema.repository.UserRepository;
import Cinema.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        // Сохраняем пользователя
        userRepository.save(user);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        // Удаляем отзывы пользователя
        reviewRepository.deleteAll(user.getReviews());
        // Удаляем пользователя
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void edit(User updatedUser) {
        if (userRepository.existsByUsername(updatedUser.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (userRepository.existsByEmail(updatedUser.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + updatedUser.getId() + " not found"));

        // Обновляем информацию о пользователе
        user.setUsername(updatedUser.getUsername());
        user.setDateOfBirth(updatedUser.getDateOfBirth());
        user.setGender(updatedUser.getGender());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());


        // Обновляем связи с отзывами
        for (Review review : user.getReviews()) {
            review.setUser(user);
        }
        reviewRepository.saveAll(user.getReviews());

        // Сохраняем обновленного пользователя
        userRepository.save(user);
    }


    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(UserRole.ADMIN);
        save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    @Override
    public List<User> findByDateOfBirth(Date dateOfBirth) {
        return userRepository.findByDateOfBirth(dateOfBirth);
    }

    @Override
    public List<User> findByGender(String gender) {
        return userRepository.findByGender(gender);
    }

    public List<User> getTopReviewers() {
        // Получаем всех пользователей
        List<User> allUsers = userRepository.findAll();

        // Сортируем пользователей по количеству отзывов в порядке убывания
        allUsers.sort((u1, u2) -> Integer.compare(u2.getReviews().size(), u1.getReviews().size()));

        // Возвращаем топ пользователей. Например, топ 10.
        return allUsers.stream().limit(2).collect(Collectors.toList());
    }

}
