CREATE TABLE addresses (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  street varchar(100) NOT NULL,
  city varchar(50) NOT NULL,
  state char(50) NOT NULL,
  zip_code char(9) NOT NULL,
  number smallint(5) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE stores (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  addresses_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_addresses
  FOREIGN KEY (addresses_id)
  REFERENCES addresses(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;