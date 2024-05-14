CREATE TABLE payments (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	order_id BIGINT NOT NULL,
	is_payed BOOLEAN,
	payment_status VARCHAR(255),
	payment_mode VARCHAR(255),
	reference_no VARCHAR(255)
);

-- Create index on the 'id' column
CREATE INDEX idx_id ON payments (id);

-- Create index on the 'order_id' column
CREATE INDEX idx_order_id ON payments (order_id);