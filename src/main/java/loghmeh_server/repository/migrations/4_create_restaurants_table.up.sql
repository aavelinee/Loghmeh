create table restaurants (
    id            varchar (255)  not null,
    name          varchar (255)  not null,
    logo          varchar (255)  default null null,
    description   varchar (500)  default null null,
    location_id   int,
    primary key (id),
    foreign key (location_id)  references locations(id) on delete set null
);