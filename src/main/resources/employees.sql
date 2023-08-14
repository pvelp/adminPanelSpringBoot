create table employees
(
    id          serial
        constraint employees_pk
            primary key,
    email varchar(30),
    username    varchar(30),
    password    varchar
);

alter table employees
    owner to pvelp;

