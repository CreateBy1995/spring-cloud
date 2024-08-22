create table product
(
    id           bigint auto_increment
        primary key,
    product_name varchar(64) default '' not null,
    price        varchar(64) default '' not null
);

