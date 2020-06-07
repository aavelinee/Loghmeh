create table if not exists locations
(
  id int not null auto_increment,
  x float,
  y float,
  primary key(id),
  unique(x, y)
);