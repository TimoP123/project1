CREATE TABLE IF NOT EXISTS account (
    "ID"        INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    "USERNAME"  VARCHAR(30),
    "PASSWORD"  VARCHAR(30)
);

INSERT INTO account VALUES (null, 'supermember', 'itsMeeee');