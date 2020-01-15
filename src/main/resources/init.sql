CREATE TABLE person
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(60) NOT NULL,
    age       int         NOT NULL,
    is_active BOOLEAN     NOT NULL DEFAULT TRUE
);