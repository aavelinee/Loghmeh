create table if not exists deliveries
(
  id varchar(255) not null,
  velocity float,
  location_id int,
  primary key(id),
  foreign key (location_id) references locations(id) on delete set null
);