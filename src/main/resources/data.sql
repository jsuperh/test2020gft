CREATE TABLE IF NOT EXISTS brands (
                                      id BIGINT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS prices (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      brand_id BIGINT NOT NULL,
                                      start_date TIMESTAMP NOT NULL,
                                      end_date TIMESTAMP NOT NULL,
                                      price_list INTEGER NOT NULL,
                                      product_id BIGINT NOT NULL,
                                      priority INTEGER NOT NULL,
                                      price DECIMAL(19, 2) NOT NULL,
                                      curr VARCHAR(3) NOT NULL,
    CONSTRAINT prices_brands_fk FOREIGN KEY (brand_id) REFERENCES brands (id)
    );


INSERT INTO brands (id, name) VALUES (1, 'ZARA');
INSERT INTO brands (id, name) VALUES (2, 'otro');

INSERT INTO prices (product_id, start_date, end_date, price_list, priority, price, curr, brand_id)
VALUES (35455, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 0, 35.50, 'EUR', 1);

INSERT INTO prices (product_id, start_date, end_date, price_list, priority, price, curr, brand_id)
VALUES (35455, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 1, 25.45, 'EUR', 1);

INSERT INTO prices (product_id, start_date, end_date, price_list, priority, price, curr, brand_id)
VALUES (35455, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 1, 30.50, 'EUR', 1);

INSERT INTO prices (product_id, start_date, end_date, price_list, priority, price, curr, brand_id)
VALUES (35455, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 1, 38.95, 'EUR', 1);
