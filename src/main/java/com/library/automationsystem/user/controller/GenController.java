package com.library.automationsystem.user.controller;

import com.library.automationsystem.user.model.User;
import com.library.automationsystem.user.repo.UserRepo;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GenController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String currentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
