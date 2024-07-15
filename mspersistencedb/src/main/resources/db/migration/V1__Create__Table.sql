CREATE TABLE address (
                         id BIGSERIAL PRIMARY KEY,
                         street VARCHAR(255),
                         number VARCHAR(255),
                         city VARCHAR(255),
                         state VARCHAR(255),
                         country VARCHAR(255),
                         postal_code VARCHAR(255),
                         latitude_coordinate VARCHAR(255),
                         longitude_coordinate VARCHAR(255),
                         customer_id BIGINT
);

CREATE TABLE customers (
                           id BIGSERIAL PRIMARY KEY,
                           address_id BIGINT UNIQUE,
                           customer_name VARCHAR(255) UNIQUE,
                           phone_number VARCHAR(255) UNIQUE,
                           whatsapp VARCHAR(255),
                           principal_email VARCHAR(255) UNIQUE,
                           responsible_employee VARCHAR(255),
                           cpf VARCHAR(14) UNIQUE,
                           cnpj VARCHAR(18) UNIQUE,
                           dtype VARCHAR(31) NOT NULL

);

CREATE TABLE customers_email_list (
                                      customers_id BIGINT NOT NULL,
                                      email_list VARCHAR(255)

);

CREATE TABLE delivery (
                          id BIGSERIAL PRIMARY KEY,
                          product_id BIGINT,
                          recipient_id BIGINT,
                          sender_id BIGINT,
                          is_delivered VARCHAR(255) CHECK (is_delivered IN ('PENDING','DELIVERED','CANCELED'))

);

CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255),
                         weight FLOAT NOT NULL,
                         volume FLOAT NOT NULL,
                         value NUMERIC(10,2)
);

ALTER TABLE address
    ADD CONSTRAINT fk_address_customer FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE customers_email_list
    ADD CONSTRAINT fk_customers_email_list FOREIGN KEY (customers_id) REFERENCES customers (id);

ALTER TABLE delivery
    ADD CONSTRAINT fk_product_delivery FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE delivery
    ADD CONSTRAINT fk_recipient_delivery FOREIGN KEY (recipient_id) REFERENCES customers (id);

ALTER TABLE delivery
    ADD CONSTRAINT fk_sender_delivery FOREIGN KEY (sender_id) REFERENCES customers (id);