package ru.abnod.todolist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.abnod.todolist.config.WebSecure;
import ru.abnod.todolist.model.User;
import ru.abnod.todolist.model.UserRegisterBean;
import ru.abnod.todolist.util.UserDetailService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final UserDetailService userDetailService;
    private final WebSecure webSecure;


    public Map<String, Object> register(UserRegisterBean userBean) {
        Map<String, Object> resultMap = new HashMap<>();
        boolean success = false;

        if (userBean != null) {
            if (userBean.getPassword().equals(userBean.getPasswordConfirm())) {
                User user = null;
                try {
                    String password = webSecure.encodePassword(userBean.getPassword());
                    user = userDetailService.create(new User(userBean.getUsername(), password, false));
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
