CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  named VARCHAR(100) ,
  lastNamed VARCHAR(100) ,
  secondLastNamed VARCHAR(100) ,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  roles VARCHAR(50) ,
  photoUserd VARCHAR(250) ,
  creation VARCHAR(250) ,
  update VARCHAR(250) ,
  active BOOLEAN,
  UNIQUE (username)
);