DROP TABLE IF EXISTS car;
CREATE TABLE car (
  id     INT         NOT NULL AUTO_INCREMENT,
  make   VARCHAR(50) NOT NULL,
  model  VARCHAR(50) NOT NULL,
  year   INT         NOT NULL,
  PRIMARY KEY (id)
);
