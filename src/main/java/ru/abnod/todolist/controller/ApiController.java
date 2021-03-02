package ru.abnod.todolist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.abnod.todolist.service.RegisterService;
import ru.abnod.todolist.bean.UserBean;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final RegisterService registerService;


    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody UserBean userBean) {
        return registerService.register(userBean);
    }
}