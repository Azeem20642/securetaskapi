package com.azeem.securetaskapi.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminMessage() {
        return "Welcome! You are accessing a secured admin service.";
    }
}
