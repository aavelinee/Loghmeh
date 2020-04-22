create table order
(
  id int not null auto_increment,
  status enum('Delivered', 'OnTheWay', 'DeliverySearch', 'Ordering'),
  customer_id int,
  restaurant_id int,
  delivery_id int,
  estimated_delivery_time double,
  delivery_date date,
  total_price float,
  primary key(id),
  foreign key (customer_id) references customer(id) on delete set null,
  foreign key (restaurant_id) references restaurant(id) on delete set null,
  foreign key (delivery_id) references delivery(id) on delete set null
);