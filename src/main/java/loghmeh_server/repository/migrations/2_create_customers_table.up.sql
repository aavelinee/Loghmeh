create table customers
(
  id int not null auto_increment,
  first_name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  last_name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  phone_number varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci unique,
  email varchar(255),
  credit float,
  location_id int,
  primary key(id),
  foreign key (location_id) references locations(id) on delete set null
);