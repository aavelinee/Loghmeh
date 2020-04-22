create table menus (
  id int auto_increment,
  restaurant_id int,
  foreign key (restaurant_id) references restaurants(id) on delete cascade
);