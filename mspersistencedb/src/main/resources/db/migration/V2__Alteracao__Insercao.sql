ALTER TABLE delivery
    RENAME COLUMN is_delivered TO status_delivery;

ALTER TABLE delivery
    ADD COLUMN password_delivery VARCHAR(255) UNIQUE,
    ADD COLUMN date_solicitation DATE DEFAULT CURRENT_DATE;

CREATE TABLE item (
                      id BIGSERIAL PRIMARY KEY,
                      product_name VARCHAR(255),
                      quantity INT,
                      delivery_id BIGINT,
                      CONSTRAINT fk_item_delivery FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

ALTER TABLE product
    ADD COLUMN quantity_stock INT,
    ADD COLUMN position_stock VARCHAR(255);

ALTER TABLE customers
    ADD COLUMN corporate_name VARCHAR(255)