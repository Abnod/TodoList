package ru.abnod.todolist.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private final Long id;
    @Column(name = "login", nullable = false)
    private final String login;
    @Column(name = "password_hash", nullable = false)
    private final String password_hash;
    @Column(name = "admin", nullable = false)
    private final boolean admin;
}
