CREATE DATABASE online_sales;
\c online_sales

CREATE table customer (
    id bigserial not null primary key,
    firstname varchar(64),
    lastname varchar(64),
    address text,
    phone varchar(24),
    mobile varchar(16),
    email varchar(128),
    type varchar(8) default 'customer',
    created timestamp
);
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('John','Doe','somewhere in nowhere','123455','4324234','x@x.com','customer',NOW());
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('Jane','Doe','somewhere in nowhere','123455','4324234','x@x.com','customer',NOW());
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('Jimmy','Doe','somewhere in nowhere','123455','4324234','x@x.com','customer',NOW());
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('Joy','Doe','somewhere in nowhere','123455','4324234','x@x.com','customer',NOW());
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('John','Doe','somewhere in nowhere','123455','4324234','x@x.com','staff',NOW());
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('Jane','Doe','somewhere in nowhere','123455','4324234','x@x.com','staff',NOW());
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('Jimmy','Doe','somewhere in nowhere','123455','4324234','x@x.com','staff',NOW());
INSERT INTO customer (firstname,lastname,address,phone,mobile,email,type,created)
    VALUES ('Joy','Doe','somewhere in nowhere','123455','4324234','x@x.com','staff',NOW());


CREATE table product (
    id bigserial not null primary key,
    name varchar(100),
    price money default '0.0000',
    stock bigint default '999',
    created timestamp
);
insert into product (name,price,created) values ('Shampoo', '9.99', NOW());
insert into product (name,price,created) values ('Soap', '2.99', NOW());
insert into product (name,price,created) values ('Century Tuna', '4.99', NOW());
insert into product (name,price,created) values ('Cornbeef', '4.80', NOW());
insert into product (name,price,created) values ('Diaper', '18.99', NOW());
insert into product (name,price,created) values ('Milk', '3.99', NOW());

CREATE table orders (
    id bigserial not null primary key,
    product_id bigserial not null,
    customer_id bigserial not null,
    sales_id bigserial not null,
    price money default '0.0000',
    quantity bigint default '0',
    paid timestamp default null,
    created timestamp
);
INSERT INTO orders (product_id, customer_id, sales_id, price,quantity,created)
    VALUES (1,2,7,'9.99', 2,NOW());
INSERT INTO orders (product_id, customer_id, sales_id, price,quantity,created)
    VALUES (4,2,7,'4.80', 2,NOW());


SELECT
    o.id, p.name, o.price, o.quantity,
    (o.price * o.quantity) AS total_amount,
    CONCAT(c.firstname, ' ', c.lastname) AS customer_name,
    CONCAT(s.firstname, ' ', s.lastname) AS sales_person,
    o.created AS sales_date, o.customer_id, o.sales_id, o.product_id
FROM orders AS o
    JOIN customer AS c ON c.id = o.customer_id
    JOIN customer AS s ON s.id = o.sales_id
    JOIN product AS p ON p.id = o.product_id;