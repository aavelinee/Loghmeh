create table restaurants (
    id            varchar (255)  not null,
    name          varchar (255)  not null,
    logo          varchar (255)  default null null,
    description   varchar (500)  default null null,
    location_id   int            not null,
    menu_id       int
    primary key (id)
    foreign key (location_id)  references location(id) on delete set null
    foreign key (menu_id)      references menu(id) on delete set null
);