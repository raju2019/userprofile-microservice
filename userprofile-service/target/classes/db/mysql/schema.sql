CREATE DATABASE IF NOT EXISTS jem;
GRANT ALL PRIVILEGES ON userclinic.* TO pc@localhost IDENTIFIED BY 'pc';

USE userclinic;

CREATE TABLE IF NOT EXISTS user_profile (
  user_id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  alias varchar(255) DEFAULT NULL,
  city varchar(58) DEFAULT NULL,
  country varchar(60) DEFAULT NULL,
  postcode varchar(10) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  user_address varchar(50) DEFAULT NULL,
  user_name varchar(100) DEFAULT NULL
) ENGINE=InnoDB;
