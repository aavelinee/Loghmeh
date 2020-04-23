create table foodpartyfoods
(
  food_id int not null,
  count int,
  old_price float,
  expiration_time timestamp,
  primary key(food_id),
  foreign key (food_id) references foods(id) on delete cascade
);