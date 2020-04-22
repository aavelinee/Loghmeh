create table menus (
  id int auto_increment,
  restaurant_id varchar (255) not null,
  primary key (id),
  foreign key (restaurant_id) references restaurants(id) on delete cascade
);