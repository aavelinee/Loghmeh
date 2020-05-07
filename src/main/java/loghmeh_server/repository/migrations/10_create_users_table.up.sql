create table users (
  id int not null auto_increment,
  name varchar (50) not null,
  family_name varchar (50) not null,
  email varchar (100) not null,
  password varchar (100) not null,
  primary key (email),
);