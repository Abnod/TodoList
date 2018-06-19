package ru.abnod.todolist;

import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final AuthenticationTrustResolverImpl authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || authenticationTrustResolver.isAnonymous(auth)){
            return "login";
        } else {
            return "redirect:/";
        }
    }
}
