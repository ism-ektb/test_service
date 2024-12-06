drop table if exists weather cascade;
drop table if exists users cascade;
drop sequence if exists user_id_seq;
create sequence user_id_seq start with 1 increment by 1;
create table users (
                       id bigint not null,
                       password varchar(255) not null,
                       role varchar(255) not null check (role in ('ROLE_USER','ROLE_ADMIN')),
                       username varchar(255) not null unique,
                       primary key (id)
);

create table weather (
                         temperature float not null,
                         raining boolean not null,
                         id bigserial not null,
                         user_id bigint,
                         primary key (id)

);
