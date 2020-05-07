create table orders
(
  id int not null auto_increment,
  status varchar (25),
  customer_id int,
  restaurant_id varchar(255),
  delivery_id varchar(255) default null,
  estimated_delivery_time double default 0,
  delivery_date date default null,
  total_price float,
  primary key(id),
  foreign key (customer_id)   references customers(id)    on delete set null,
  foreign key (restaurant_id) references restaurants(id)  on delete set null,
  foreign key (delivery_id)   references deliveries(id)   on delete set null
);