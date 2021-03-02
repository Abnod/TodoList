package ru.abnod.todolist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.abnod.todolist.config.WebSecure;
import ru.abnod.todolist.db.User;
import ru.abnod.todolist.bean.UserBean;
import ru.abnod.todolist.util.UserDetailService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final UserDetailService userDetailService;
    private final WebSecure webSecure;


    public Map<String, Object> register(UserBean userBean) {
        Map<String, Object> resultMap = new HashMap<>();
        boolean success = false;

        if (userBean != null) {
            if (userBean.getPassword().equals(userBean.getPasswordConfirm())) {
                User user = null;
                try {
                    String password = webSecure.encodePassword(userBean.getPassword());
                    User newUser = new User();
                    newUser.setName(userBean.getName());
                    newUser.setPassword_hash(password);
                    newUser.setAdmin(false);
                    user = userDetailService.create(newUser);
                } catch (Exception e) {
                    log.error("Account creation failed with exception: {}", e.getMessage());
                    resultMap.put("message", "account already exist");
                }
                if (user != null) {
                    try {
                        webSecure.auth(user);
                        success = true;
                    } catch (Exception e) {
                        resultMap.put("message", "auto login failed");
                    }
                }
            } else {
                resultMap.put("message", "passwords do not match");
            }
        } else {
            resultMap.put("message", "form contains empty fields");
        }

        resultMap.put("success", success);
        return resultMap;
    }
}
