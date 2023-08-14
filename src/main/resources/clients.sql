create table clients
(
    id          serial
        constraint clients_pk
            primary key,
    telegram_id varchar(30),
    name        varchar(30),
    username    varchar(30),
    phone       varchar(12),
    birthday    varchar(12),
    is_banned   boolean
);

alter table clients
    owner to pvelp;

