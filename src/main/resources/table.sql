Hibernate: create table app_user (id bigserial not null, email varchar(255), name varchar(255), role varchar(255) check (role in ('USER','ADMIN')), primary key (id))
    Hibernate: create table booking (id bigserial not null, end_date date, start_date date, car_id bigint, client_id bigint, primary key (id))
    Hibernate: create table car (id bigserial not null, brand varchar(255), car_class varchar(255), price_per_day float(53) not null, primary key (id))
    Hibernate: create table payment (id bigserial not null, amount float(53) not null, payment_date date, booking_id bigint, primary key (id))
    Hibernate: alter table if exists booking add constraint FKd9p8qdy5sj4ym0bmksdx7yrwj foreign key (car_id) references car
    Hibernate: alter table if exists booking add constraint FKiaun29d3vb17t5yr6bkslrlug foreign key (client_id) references app_user
    Hibernate: alter table if exists payment add constraint FKqewrl4xrv9eiad6eab3aoja65 foreign key (booking_id) references booking



    -- Скрипт для добавления 20 машин в таблицу Car

    INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Toyota', 'Sedan', 55.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Honda', 'SUV', 65.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Ford', 'Truck', 75.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Chevrolet', 'Convertible', 85.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('BMW', 'Coupe', 95.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Audi', 'Sedan', 105.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Mercedes', 'SUV', 115.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Lexus', 'Sedan', 125.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Subaru', 'SUV', 135.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Nissan', 'Sedan', 145.0);

INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Kia', 'Sedan', 55.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Hyundai', 'SUV', 65.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Jeep', 'SUV', 75.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Volkswagen', 'Sedan', 85.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Porsche', 'Convertible', 95.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Jaguar', 'Sedan', 105.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Ferrari', 'Coupe', 115.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Lamborghini', 'Convertible', 125.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Maserati', 'Sedan', 135.0);
INSERT INTO Car (brand, carClass, pricePerDay) VALUES ('Bentley', 'SUV', 145.0);
