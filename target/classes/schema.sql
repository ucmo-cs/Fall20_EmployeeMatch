DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee(
    userId INT UNIQUE AUTO_INCREMENT,
    firstn CHAR(20),
    lastn CHAR(20),
    email CHAR(50),
    passHash Char(32),
    Primary KEY (userId)
);

DROP TABLE IF EXISTS employer;
CREATE TABLE IF NOT EXISTS employer (
    companyId INT UNIQUE AUTO_INCREMENT,
    companyName CHAR(50),
    email CHAR(50) ,
    Primary KEY (companyId)
    )
DROP TABLE IF EXISTS employeePreferences;
CREATE TABLE IF NOT EXISTS employeePreferences (
    userId INT UNIQUE,
    ew1 int,
    ew2 int,
    ew3 int,
    ew4 int,
    ew5 int,
    eo1 int,
    eo2 int,
    eo3 int,
    eo4 int,
    eo5 int,
    FOREIGN KEY (userId) REFERENCES employee(userId)
    );
DROP TABLE IF EXISTS employerPreferences;
CREATE TABLE IF NOT EXISTS employerPreferences (
    companyId INT UNIQUE,
    ew1 int,
    ew2 int,
    ew3 int,
    ew4 int,
    ew5 int,
    eo1 int,
    eo2 int,
    eo3 int,
    eo4 int,
    eo5 int,
    FOREIGN KEY (companyId) REFERENCES employer(companyId)
    );
DROP TABLE IF EXISTS matches;
CREATE TABLE IF NOT EXISTS matches (
	userId int,
	companyId int,
	FOREIGN KEY (userId) REFERENCES employee(userId),
    FOREIGN KEY (companyId) REFERENCES employer(companyId)
);



