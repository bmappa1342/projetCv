package com.example.tpDevOps.sec.service;


import com.example.tpDevOps.sec.entities.AppRole;
import com.example.tpDevOps.sec.entities.AppUser;
import com.example.tpDevOps.sec.repository.AppRoleRepository;
import com.example.tpDevOps.sec.repository.AppUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService{
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String repassword) {
        if (!password.equals(repassword)) throw new RuntimeException("password not match");
        String hashedPWD=passwordEncoder.encode(password);
        AppUser appUser =new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw new RuntimeException("Role"+roleName+" existe déjà !");
        appRole =new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=  appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("utilisateur introuvable !");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw new RuntimeException("Role introuvable !");
        appRole.setRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=  appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("utilisateur introuvable !");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw new RuntimeException("Role introuvable !");
        appRole.setRoleName(roleName);
        appUser.getAppRoles().remove(appRole);

    }
    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
