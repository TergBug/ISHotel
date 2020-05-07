use ishotel;
drop table if exists customers_services;
drop table if exists customers_facilities;
drop table if exists customers;
drop table if exists payment_types;
drop table if exists rooms;
drop table if exists room_types;
drop table if exists employees;
drop table if exists services;
drop table if exists facilities;
commit;

-- Services --
create table if not exists services
(
    id    int primary key auto_increment,
    name  varchar(255) not null unique,
    price decimal(10, 2),
    check (price >= 0)
);
create table if not exists facilities
(
    id       int primary key auto_increment,
    name     varchar(255) not null unique,
    quantity int,
    price    decimal(10, 2),
    check (price >= 0 and quantity >= 0)
);

-- Employees --
create table if not exists employees
(
    id          int primary key auto_increment,
    first_name  varchar(255),
    last_name   varchar(255),
    service_id  int,
    facility_id int,
    foreign key (service_id) references services (id),
    foreign key (facility_id) references facilities (id)
);

-- Rooms --
create table if not exists room_types
(
    id            int primary key auto_increment,
    type          varchar(255) not null,
    person_number int          not null,
    check (person_number > 0)
);
create table if not exists rooms
(
    id           int auto_increment,
    type_id      int,
    attendant_id int,
    state        tinyint not null default 0,
    floor        int     not null,
    price        decimal(10, 2),
    foreign key (type_id) references room_types (id),
    foreign key (attendant_id) references employees (id),
    primary key (id, type_id),
    check (price >= 0)
);

-- Customers --
create table if not exists payment_types
(
    id   int primary key auto_increment,
    name varchar(255) not null unique
);
create table if not exists customers
(
    id              int primary key auto_increment,
    first_name      varchar(255),
    last_name       varchar(255),
    passport        varchar(255),
    room_id         int,
    start_date      date,
    end_date        date,
    fees            decimal(10, 2),
    payment_type_id int,
    foreign key (room_id) references rooms (id),
    foreign key (payment_type_id) references payment_types (id),
    check (fees >= 0)
);
create table if not exists customers_services
(
    customer_id int,
    service_id  int,
    foreign key (customer_id) references customers (id),
    foreign key (service_id) references services (id),
    primary key (customer_id, service_id)
);
create table if not exists customers_facilities
(
    customer_id int,
    facility_id int,
    quantity    int,
    foreign key (customer_id) references customers (id),
    foreign key (facility_id) references facilities (id),
    primary key (customer_id, facility_id)
);
commit;