package com.example.tpDevOps.sec.service;

import com.example.tpDevOps.sec.entities.AppRole;
import com.example.tpDevOps.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String repassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
}
