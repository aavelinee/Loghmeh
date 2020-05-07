create table orderitems (
  id int not null auto_increment,
  order_id int not null,
  food_id int not null,
  order_count int default 1,
  primary key (id),
  foreign key (order_id) references orders(id) on delete cascade,
  foreign key (food_id) references foods(id) on delete cascade
);