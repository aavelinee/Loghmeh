create table foodparty
(
  food_id int not null,
  count int,
  old_price float,
  primary key(food_id),
  foreign key (food_id) references food(id) on delete cascade
);