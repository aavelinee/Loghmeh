create table foods
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