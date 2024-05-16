package Cinema.service.impl;

import Cinema.repository.AdminRepository;

import Cinema.repository.UserRepository;
import Cinema.service.AdminService;
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

