CREATE TABLE refunds (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    orders_id bigint,
    order_items_id VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;