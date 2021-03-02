package ru.abnod.todolist.db;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "task")
public class Task {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 300)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "userid_fk")
    )
    private User user;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "completed", nullable = false)
    private boolean completed;
}
