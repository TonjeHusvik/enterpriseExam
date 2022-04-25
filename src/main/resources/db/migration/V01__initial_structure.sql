create table users(
    user_id serial primary key,
    user_email varchar(50),
    user_password varchar(100),
    user_created timestamp,
    user_enabled bool
);

create table authorities(
    authority_id serial primary key,
    authority_name varchar(50)
);

create table users_authorities(
    user_id int,
    authority_id int
);

create table animals(
    animal_id serial primary key,
    animal_name varchar(50),
    animal_type varchar(50),
    animal_breed varchar(100),
    animal_age int,
    animal_health varchar(100)
);