CREATE TABLE TB_USER (ID BIGINT  NOT NULL AUTO_INCREMENT,
                      FIRSTNAME VARCHAR(100) NOT NULL,
                      LASTNAME VARCHAR(100) NOT NULL,
                      PASSWORD VARCHAR(100) NOT NULL,
                      BIRTH DATETIME(6) NOT NULL,
                      PHONE INTEGER,
                      EMAIL VARCHAR(100) NOT NULL,
                      PACKAGE_MONTHLY BIT,
                      ROLE VARCHAR(255), PRIMARY KEY (ID)) ENGINE=INNODB;

INSERT INTO TB_USER (FIRSTNAME, LASTNAME, PASSWORD, BIRTH, PHONE, EMAIL, PACKAGE_MONTHLY, ROLE)
VALUES ('Admin', 'lastname', '$2a$10$Yb.6O1lhvE95OoA8mHnV7.tu1SZTZdnV7oZ28dYlP7TifvmhkwgGi', '2000-01-01 00:00:00', NULL, 'admin@exemple.com', FALSE, 'USER');
-------------------------------------------------------------------------------------------------------------
CREATE TABLE TB_SERVICE_PROVIED (ID BIGINT NOT NULL AUTO_INCREMENT,
                                 DATA DATETIME(6) NOT NULL, VALUE INTEGER,
                                 USER_ID BIGINT, PRIMARY KEY (ID)) ENGINE=INNODB;

-------------------------------------------------------------------------------------------------------------

CREATE TABLE TB_APPOINMENT (ID BIGINT NOT NULL AUTO_INCREMENT,
                            DATE_TIME DATETIME(6) NOT NULL,
                            USER_ID BIGINT, PRIMARY KEY (ID)) ENGINE=INNODB;

-------------------------------------------------------------------------------------------------------------
CREATE TABLE SERVICE_PROVIED_SERVICE_TYPE (SERVICE_PROVIED_ID BIGINT NOT NULL,
                                           SERVICE_TYPE INTEGER NOT NULL) ENGINE=INNODB;

CREATE TABLE APPOINTMENT_SERVICE_TYPE (APPOINTMENT_ID BIGINT NOT NULL,
                                       SERVICE_TYPE INTEGER NOT NULL) ENGINE=INNODB;

