create table fin_invoices (
	invoice_id INT PRIMARY KEY,
	invoice_amount INT,
	invoice_currency VARCHAR(3),
	invoice_number VARCHAR(50),
	invoice_date DATE
);

create table fin_payments (
	payment_id INT PRIMARY KEY,
	payment_amount INT,
	payment_currency VARCHAR(3),
	payment_number VARCHAR(50),
	payment_date DATE
);

create table mkt_sales (
	sales_id INT PRIMARY KEY,
	sales_amount INT,
	sales_currency VARCHAR(3),
	sales_date DATE,
	customer_email VARCHAR(50)
);

create table mkt_promotions (
	promotion_id INT PRIMARY KEY,
	promotion_name VARCHAR(50),
	promotion_discount_rate INT,
	is_active VARCHAR(50)
);

create table people (
	person_id VARCHAR(50),
	id_card_number VARCHAR(50),
	full_name VARCHAR(50),
	email VARCHAR(50)
);

create table people_addresses (
	address_id VARCHAR(50),
	person_id VARCHAR(50),
	address VARCHAR(50),
	city VARCHAR(50),
	postal_code VARCHAR(50)
);