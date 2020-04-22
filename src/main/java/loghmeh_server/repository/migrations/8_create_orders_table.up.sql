create table orders
(
  id int not null auto_increment,
  status enum('Delivered', 'OnTheWay', 'DeliverySearch', 'Ordering'),
  customer_id int,
  restaurant_id varchar(255),
  delivery_id varchar(255),
  estimated_delivery_time double,
  delivery_date date,
  total_price float,
  primary key(id),
  foreign key (customer_id)   references customers(id)    on delete set null,
  foreign key (restaurant_id) references restaurants(id)  on delete set null,
  foreign key (delivery_id)   references deliveries(id)   on delete set null
);