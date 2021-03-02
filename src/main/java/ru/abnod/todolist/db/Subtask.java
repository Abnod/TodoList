package ru.abnod.todolist.db;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subtask")
public class Subtask {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 300)
    private String description;

    @ManyToOne
    @JoinColumn(name = "task_id",
            foreignKey = @ForeignKey(name = "taskid_fk")
    )
    private Task task;

    @Column(name = "completed", nullable = false)
    private boolean completed;

}
