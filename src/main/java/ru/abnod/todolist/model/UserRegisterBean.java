package ru.abnod.todolist.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterBean{

    @NotNull
    @NotEmpty
    private final String username;
    @NotNull
    @NotEmpty
    private final String password;
    @NotNull
    @NotEmpty
    private final String passwordConfirm;
}
