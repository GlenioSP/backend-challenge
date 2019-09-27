CREATE TABLE orders (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    confirmation_date datetime NOT NULL,
    status VARCHAR(255) NOT NULL,
    addresses_id bigint(20) NOT NULL,
    stores_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_orders_addresses FOREIGN KEY (addresses_id) REFERENCES addresses(id),
    CONSTRAINT fk_orders_stores FOREIGN KEY (stores_id) REFERENCES stores(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE order_items (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    quantity bigint(20) NOT NULL,
    unit_price DECIMAL(12,2) NOT NULL,
    orders_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT fk_items_orders
    FOREIGN KEY (orders_id)
    REFERENCES orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE payments (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    payment_date datetime NOT NULL,
    status VARCHAR(255) NOT NULL,
    card_number CHAR(16) NOT NULL,
    orders_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_payments_orders
    FOREIGN KEY (orders_id)
    REFERENCES orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;