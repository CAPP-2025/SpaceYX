package com.spaceyx.space_tourism.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class RedirectController {

    @GetMapping("/redirect-to-role-page")
    public String redirectToRolePage(Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User has no role"));

        switch (role) {
            case "ROLE_Technicien":
                return "redirect:/navettes";
            case "ROLE_Planificateur":
                return "redirect:/vols";
            case "ROLE_Voyageur":
                return "redirect:/reservations";
            default:
                throw new IllegalStateException("Unexpected role: " + role);
        }
    }
}

