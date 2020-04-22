create table customer
(
  id int not null auto_increment,
  first_name varchar(255),
  last_name varchar(255),
  phone_number varchar(255),
  email varchar(255),
  credit float,
  location_id int,
  primary key(id),
  foreign key (location_id) references location(id) on delete set null
);