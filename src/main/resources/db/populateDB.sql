use ishotel;

-- Services --
insert into services (name, price)
values ('Netflix films', 50.00),
       ('Room cleaning', 10.00),
       ('Tesla transport', 100.00),
       ('Food and drinks', 40.00),
       ('Pool and spa', 80.00);
commit;
insert into facilities (name, quantity, price)
values ('Bed and chair', 50, 3.00),
       ('Toiletries', 100, 1.00),
       ('Tableware', 500, 1.50),
       ('Tesla car', 20, 100.00);
commit;

-- Employees --
insert into employees (first_name, last_name, service_id, facility_id)
values ('Albert', 'Ant', 2, 1),
       ('Carl', 'Din', 2, 2),
       ('Phoede', 'Rich', 1, null),
       ('Harry', 'Potter', 3, 4),
       ('Wold', 'Disney', 4, 3),
       ('Joey', 'Sting', 5, null);
commit;

-- Rooms --
insert into room_types (type, person_number)
values ('simple', 1),
       ('simple', 2),
       ('lux', 1),
       ('lux', 2),
       ('president', 6);
commit;
insert into rooms (attendant_id, type_id, floor, price)
values (1, 3, 1, 1200.00),
       (2, 4, 1, 3000.00),
       (1, 3, 1, 1200.00),
       (2, 4, 1, 3000.00),
       (1, 3, 1, 1200.00),
       (2, 4, 1, 3000.00),

       (1, 1, 2, 300.00),
       (2, 2, 2, 600.00),
       (1, 1, 2, 300.00),
       (2, 2, 2, 600.00),
       (1, 1, 2, 300.00),
       (2, 2, 2, 600.00),

       (1, 3, 3, 1200.00),
       (2, 4, 3, 3000.00),
       (1, 3, 3, 1200.00),
       (2, 4, 3, 3000.00),
       (1, 3, 3, 1200.00),
       (2, 4, 3, 3000.00),

       (1, 1, 4, 300.00),
       (2, 2, 4, 600.00),
       (1, 1, 4, 300.00),
       (2, 2, 4, 600.00),
       (1, 1, 4, 300.00),
       (2, 2, 4, 600.00),

       (1, 3, 5, 2000.00),
       (2, 4, 5, 5000.00),
       (1, 3, 5, 2000.00),
       (2, 4, 5, 5000.00),
       (1, 5, 5, 10500.00),
       (2, 5, 5, 10500.00);
commit;

-- Customers --
insert into payment_types (name)
values ('Cash'),
       ('Card'),
       ('Cryptocurrency');
commit;
insert into customers (first_name, last_name, passport, room_id, start_date, end_date, fees, payment_type_id)
values ('Joey', 'Tribbiani', 'qwerty123', 8, '01.02.2020', '01.03.2020', 200.00, 1),
       ('Fhoebe', 'Buffay', 'qwerty123', 3, '01.02.2020', '01.03.2020', 800.00, 1),
       ('Rachel', 'Green', 'qwerty123', 25, '01.02.2020', '01.03.2020', 1000.00, 2),
       ('Ross', 'Geller', 'qwerty123', 16, '01.02.2020', '01.03.2020', 500.00, 3),
       ('Monica', 'Geller', 'qwerty123', 17, '01.02.2020', '01.03.2020', 500.00, 3),
       ('Chandler', 'Bing', 'qwerty123', 29, '01.02.2020', '01.03.2020', 5000.00, 2);
commit;
insert into customers_services (customer_id, service_id)
values (1, 3),
       (2, 4),
       (2, 5),
       (3, 2),
       (4, 1),
       (4, 3),
       (4, 4),
       (5, 3),
       (6, 1),
       (6, 3);
commit;
insert into customers_facilities (customer_id, facility_id, quantity)
values (1, 2, 2),
       (2, 4, 6),
       (3, 2, 2),
       (4, 4, 7),
       (6, 1, 8);
commit;