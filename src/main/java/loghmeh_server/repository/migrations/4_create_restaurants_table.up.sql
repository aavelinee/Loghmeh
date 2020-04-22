create table restaurants (
    id            varchar (255)  not null,
    name          varchar (255)  CHARACTER SET utf8 COLLATE utf8_general_ci not null,
    logo          varchar (255)  default null null,
    description   varchar (500)  CHARACTER SET utf8 COLLATE utf8_general_ci default null null,
    location_id   int,
    primary key (id),
    foreign key (location_id)  references locations(id) on delete set null
);