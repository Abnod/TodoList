package ru.abnod.todolist.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.abnod.todolist.model.User;
import ru.abnod.todolist.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword_hash(), getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authList = new ArrayList<>();
        String role;
        if (user.isAdmin()) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }
        SimpleGrantedAuthority sGA = new SimpleGrantedAuthority(role);
        authList.add(sGA);
        return authList;
    }
}