package com.azeem.securetaskapi.controller;


import com.azeem.securetaskapi.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private AdminService adminService;

    public TestController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/api/test")
    public String test(){
        return "Spring Security is working!";
    }

    @GetMapping("/api/user/profile")
    public String userProfile() {
        return "Welcome! You are authenticated.";
    }

    @GetMapping("/api/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin! You have access to the admin dashboard.";
    }

    @GetMapping("/api/admin/service")
    public String adminServiceTest() {
        return adminService.getAdminMessage();
    }

}
