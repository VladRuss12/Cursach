package lab4.service.impl;

import lab4.repository.AdminRepository;

import lab4.repository.UserRepository;
import lab4.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository) {
        super(userRepository);
        this.adminRepository = adminRepository;
    }

    // Реализация методов для класса AdminServiceImpl
}

