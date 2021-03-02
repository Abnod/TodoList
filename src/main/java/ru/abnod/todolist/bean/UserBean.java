package ru.abnod.todolist.bean;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserBean {

    @NotNull
    @NotEmpty
    private final String name;
    @NotNull
    @NotEmpty
    private final String password;
    @NotNull
    @NotEmpty
    private final String passwordConfirm;
}
