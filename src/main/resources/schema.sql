CREATE TABLE IF NOT EXISTS test(
    id INT UNSIGNED PRIMARY KEY auto_increment,
    `name` VARCHAR(100) NOT NULL,
    price INT NOT NULL DEFAULT 0,
    reg_date TIMESTAMP
);

