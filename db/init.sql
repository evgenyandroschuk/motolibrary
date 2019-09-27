

create table lang(
    id int primary key ,
    description varchar(1000) unique
);

insert into lang values(1, 'English');
insert into lang values(2, 'Russian');

create table resources(
    id bigint primary key,
    description varchar(2000)
);


create table resource_code(
    resource_id bigint references resources (id),
    lang_id int references lang(id),
    description varchar(2000),
    unique (resource_id, lang_id)
);

insert into resources values(1, 'Made');
insert into resource_code values (1, 1, 'Made');
insert into resource_code values (1, 2, 'Марка');

select * from resource_code;

create table manufacture(
    id int primary key,
    resource_id bigint references resources(id),
    description varchar(500) unique,
    country varchar(500)
);


insert into resources values(3, 'Brand');
insert into resource_code values (3, 1, 'Motorcycle library');
insert into resource_code values (3, 2, 'Справочник мотоциклов');

insert into resources values(4, 'Bajaj');
insert into resource_code values (4, 1, 'Bajaj');

insert into resources values(5, 'BMW');
insert into resource_code values (5, 1, 'BMW');

insert into  manufacture
values (1, 4, 'Bajaj', 'India');

insert into  manufacture
values (2, 5, 'BMW', 'Germany');

insert into resources values(6, 'Honda');
insert into resource_code values (6, 1, 'Honda');

insert into  manufacture
values (3, 6, 'Honda', 'Japan');


select * from resources;

create sequence resources_seq start 7;
create sequence manufacture_seq start 4;

create sequence model_seq start 1;

create table model (
    id bigint primary key,
    manufacture_id int references manufacture(id),
    description varchar(500) not null,
    start_year int not null,
    end_year int not null,
    type varchar(200),

    final_drive varchar(200),
    transmission varchar(200),

    cc varchar(100),
    power varchar(100),
    torque varchar(200),
    top_speed varchar(100),
    compression varchar(100),

    rake_angle varchar(100),
    trail varchar(100),

    brakes_front varchar(500),
    brakes_rear varchar(500),

    tires_front varchar(200),
    tires_rear varchar(200),

    length varchar(100),
    width varchar(100),
    height varchar(100),
    seat_height varchar(100),
    wheel_base varchar(100),
    fuel_capacity varchar(100),
    fuel_consumption varchar(200),

    dry_weight varchar(100),
    wet_weight varchar(100),

    unique (manufacture_id, description, start_year, end_year)
);

select * from manufacture;

select id, manufacture_id, description, start_year, end_year, type, final_drive, transmission,
       cc, power, torque, top_speed, compression, rake_angle, trail, brakes_front, brakes_rear,
       tires_front, tires_rear, length, width, height, seat_height, wheel_base,
       fuel_capacity, fuel_consumption, dry_weight, wet_weight
from model;

insert into model(id, manufacture_id, description, start_year, end_year, type, final_drive, transmission,
cc, power, torque, top_speed, compression, rake_angle, trail, brakes_front, brakes_rear,
tires_front, tires_rear, length, width, height, seat_height, wheel_base,
fuel_capacity, fuel_consumption, dry_weight, wet_weight)
values(nextval(model_seq), :manufactureId, :description, :startYear, :endYear, :type, :finalDrive,
:transmission, :cc, :power, :torque, :topSpeed, :compression, :rakeAngle, :trail, :brakesFront, :brakesRear,
:tiresFront, :tiresRear, :length, :width, :height, :seatHeight, :wheelBase,
:fuelCapacity, :fuelConsumption, :dryWeight, :wetWeight
);

select * from model;

update model
set  manufacture_id = :manufactureId, description = :description, start_year = :startYear, end_year = :endYear,
type = :type, final_drive = :finalDrive, transmission = :transmission, cc = :cc, power = :power, torque = :torque,
top_speed = :topSpeed, compression = :compression, rake_angle = :rakeAngle, trail = :trail, brakes_front = :brakesFront,
brakes_rear = :brakesRear, tires_front = :tiresFront, tires_rear = :tiresRear, length = :length, width = :width,
height = :height, seat_height = :seatHeight, wheel_base = :wheelBase, fuel_capacity = :fuelCapacity,
fuel_consumption = :fuelConsumption, dry_weight = :dryWeight, wet_weight = :wetWeight
where id = :id;

select id, manufacture_id, description, start_year, end_year, type, final_drive, transmission,
cc, power, torque, top_speed, compression, rake_angle, trail, brakes_front, brakes_rear,
tires_front, tires_rear, length, width, height, seat_height, wheel_base,
fuel_capacity, fuel_consumption, dry_weight, wet_weight from model
where id = :id;