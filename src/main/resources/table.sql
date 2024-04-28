


 create table app_user (id bigserial not null, email varchar(255), name varchar(255), role varchar(255) check (role in ('USER','ADMIN')), primary key (id));
 create table booking (id bigserial not null, end_date date, start_date date, car_id bigint, client_id bigint, primary key (id));
 create table car (id bigserial not null, brand varchar(255), car_class varchar(255), price_per_day float(53) not null, primary key (id));
 create table payment (id bigserial not null, amount float(53) not null, payment_date date, booking_id bigint, primary key (id));
 alter table if exists booking add constraint FKd9p8qdy5sj4ym0bmksdx7yrwj foreign key (car_id) references car;
 alter table if exists booking add constraint FKiaun29d3vb17t5yr6bkslrlug foreign key (client_id) references app_user;
alter table if exists payment add constraint FKqewrl4xrv9eiad6eab3aoja65 foreign key (booking_id) references booking


-- Добавление 20 машин в таблицу Car
-- Скрипт для добавления 20 машин в таблицу Car
    INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Toyota', 'Sedan', 50.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Honda', 'SUV', 60.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Ford', 'Truck', 70.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Chevrolet', 'Convertible', 80.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('BMW', 'Coupe', 90.0);

 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Audi', 'Sedan', 100.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Mercedes', 'SUV', 110.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Lexus', 'Sedan', 120.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Subaru', 'SUV', 130.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Nissan', 'Sedan', 140.0);

 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Kia', 'Sedan', 50.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Hyundai', 'SUV', 60.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Jeep', 'Truck', 70.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Volkswagen', 'Sedan', 80.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Porsche', 'Convertible', 90.0);

 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Jaguar', 'Coupe', 100.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Ferrari', 'Convertible', 110.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Lamborghini', 'Coupe', 120.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Maserati', 'Sedan', 130.0);


-- Скрипт для добавления 15 новых машин в таблицу Car
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Ford', 'SUV', 55.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Chevrolet', 'Truck', 65.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Cadillac', 'Sedan', 75.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Jeep', 'SUV', 85.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('GMC', 'Truck', 95.0);

 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Tesla', 'Sedan', 105.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Rivian', 'Truck', 115.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Mazda', 'Convertible', 125.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Alfa Romeo', 'Coupe', 135.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Lotus', 'Convertible', 145.0);

 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Mitsubishi', 'SUV', 55.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Fiat', 'Sedan', 65.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Renault', 'Sedan', 75.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Peugeot', 'Convertible', 85.0);
 INSERT INTO Car (brand, car_class, price_per_day) VALUES ('Suzuki', 'Truck', 95.0);



-- Пример изменения внешнего ключа с ON DELETE CASCADE
 ALTER TABLE payment
     DROP CONSTRAINT fkqewrl4xrv9eiad6eab3aoja65;  -- Удаляем старый внешний ключ

 ALTER TABLE payment
     ADD CONSTRAINT fkqewrl4xrv9eiad6eab3aoja65
         FOREIGN KEY (booking_id) REFERENCES booking (id) ON DELETE CASCADE;  -- Добавляем с каскадным удалением




