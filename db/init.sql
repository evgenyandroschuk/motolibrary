

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

select * from manufacture;
