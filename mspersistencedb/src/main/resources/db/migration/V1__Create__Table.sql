CREATE TABLE address (
  id SERIAL PRIMARY KEY,
  city VARCHAR(255),
  country VARCHAR(255),
  latitude_coordinate VARCHAR(255),
  longitude_coordinate VARCHAR(255),
  number VARCHAR(255),
  postal_code VARCHAR(255),
  state VARCHAR(255),
  street VARCHAR(255)
);

CREATE TABLE customers (
  id SERIAL PRIMARY KEY,
  address_id BIGINT UNIQUE,
  cpf VARCHAR(14) UNIQUE,
  cnpj VARCHAR(18) UNIQUE,
  dtype VARCHAR(31) NOT NULL,
  corporate_name VARCHAR(255),
  customer_name VARCHAR(255) UNIQUE,
  phone_number VARCHAR(255) UNIQUE,
  principal_email VARCHAR(255) UNIQUE,
  responsible_employee VARCHAR(255),
  whatsapp VARCHAR(255)
);

CREATE TABLE customers_email_list (
  customers_id BIGINT NOT NULL,
  email_list VARCHAR(255)
);

CREATE TABLE delivery (
  id SERIAL PRIMARY KEY,
  date_solicitation DATE,
  recipient_id BIGINT,
  sender_id BIGINT,
  password_delivery VARCHAR(255) UNIQUE,
  status_delivery VARCHAR(255) CHECK (status_delivery IN ('PENDING','DELIVERED','CANCELED'))
);

CREATE TABLE item (
  id SERIAL PRIMARY KEY,
  quantity INTEGER NOT NULL,
  delivery_id BIGINT,
  product_name VARCHAR(255)
);

CREATE TABLE product (
  id SERIAL PRIMARY KEY,
  quantity_stock INTEGER,
  value NUMERIC(10,2),
  volume FLOAT(53) NOT NULL,
  weight FLOAT(53) NOT NULL,
  name VARCHAR(255),
  position_stock VARCHAR(255)
);

ALTER TABLE customers ADD CONSTRAINT fk_customer_address  FOREIGN KEY (address_id) REFERENCES address(id);
ALTER TABLE customers_email_list ADD CONSTRAINT fk_customers_email_list FOREIGN KEY (customers_id) REFERENCES customers(id);
ALTER TABLE delivery ADD CONSTRAINT fk_recipient_delivery FOREIGN KEY (recipient_id) REFERENCES customers(id);
ALTER TABLE delivery ADD CONSTRAINT fk_sender_delivery FOREIGN KEY (sender_id) REFERENCES customers(id);
ALTER TABLE item ADD CONSTRAINT fk_item_delivery FOREIGN KEY (delivery_id) REFERENCES delivery(id);