create table delivery
(
  id varchar(255) not null,
  velocity float,
  location_id int,
  primary key(id),
  foreign key (location_id) references location(id) on delete set null
);