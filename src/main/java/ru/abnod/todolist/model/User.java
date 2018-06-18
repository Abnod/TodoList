package ru.abnod.todolist.model;

import lombok.Data;

@Data
public class User {

    private final Long id;
    private final String login;
    private final String password_hash;
    private final boolean admin;
}
