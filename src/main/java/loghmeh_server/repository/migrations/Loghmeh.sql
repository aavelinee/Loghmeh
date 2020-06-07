create database if not exists Loghmeh;

use Loghmeh;

create table if not exists locations
(
  id int not null auto_increment,
  x float,
  y float,
  primary key(id),
  unique(x, y)
);

create table if not exists customers
(
  id int not null auto_increment,
  first_name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  last_name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  phone_number varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  email varchar(255) unique,
  credit float,
  location_id int,
  password varchar(255) not null,
  primary key(id),
  foreign key (location_id) references locations(id) on delete set null
);

create table if not exists deliveries
(
  id varchar(255) not null,
  velocity float,
  location_id int,
  primary key(id),
  foreign key (location_id) references locations(id) on delete set null
);

create table if not exists restaurants 
(
    id            varchar (255)  not null,
    name          varchar (255)  CHARACTER SET utf8 COLLATE utf8_general_ci not null,
    logo          varchar (255)  default null null,
    description   varchar (500)  CHARACTER SET utf8 COLLATE utf8_general_ci default null null,
    location_id   int,
    primary key (id),
    foreign key (location_id)  references locations(id) on delete set null
);

create table if not exists menus 
(
  id int auto_increment,
  restaurant_id varchar (255) not null,
  primary key (id),
  foreign key (restaurant_id) references restaurants(id) on delete cascade
);

create table if not exists foods
(
  id int not null auto_increment,
  name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  description varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  price float,
  popularity float,
  image_url varchar(255),
  menu_id int not null,
  primary key(id),
  foreign key (menu_id) references menus(id) on delete cascade
);

create table if not exists foodpartyfoods
(
  food_id int not null,
  count int,
  old_price float,
  expiration_time timestamp,
  primary key(food_id),
  foreign key (food_id) references foods(id) on delete cascade
);

create table if not exists orders
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

create table if not exists orderitems (
  id int not null auto_increment,
  order_id int not null,
  food_id int not null,
  order_count int default 1,
  primary key (id),
  foreign key (order_id) references orders(id) on delete cascade,
  foreign key (food_id) references foods(id) on delete cascade
);
