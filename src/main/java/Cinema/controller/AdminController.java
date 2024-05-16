package Cinema.controller;

import Cinema.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController extends UserController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        super(adminService);
        this.adminService = adminService;
    }
    @Override
    public AdminService getService() {
        return this.adminService;
    }
}
