CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    login         VARCHAR(100) NOT NULL,
    password_hash VARCHAR(500) NOT NULL,
    admin         BIT(1)       NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX user_name_UNIQUE (login)
);

CREATE TABLE IF NOT EXISTS task
(
    id          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    description VARCHAR(300) NOT NULL,
    user_id     BIGINT(20)   NOT NULL,
    end_date    TIMESTAMP    NULL,
    completed   TINYINT      NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT userid_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS subtask
(
    id          BIGINT(20)  NOT NULL AUTO_INCREMENT,
    task_id     BIGINT(20)  NOT NULL,
    description VARCHAR(30) NOT NULL,
    completed   TINYINT     NOT NULL,
    PRIMARY KEY (id),
    INDEX taskid_fk_idx (task_id),
    CONSTRAINT taskid_fk
        FOREIGN KEY (task_id)
            REFERENCES task (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
