CREATE TABLE collaborator(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    phone VARCHAR(100) NOT NULL
 )ENGINE=innoDB DEFAULT CHARSET=utf8;

CREATE TABLE travel(id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    starting_location VARCHAR(20) NOT NULL,
    destiny_location VARCHAR(20) NOT NULL,
    status VARCHAR(100) NOT NULL,
    id_collaborator BIGINT NOT NULL, FOREIGN KEY (id_collaborator) REFERENCES collaborator(id)
)ENGINE=innoDB DEFAULT CHARSET=utf8;