CREATE TABLE IF NOT EXISTS vendors (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    vendor_code INT NOT NULL UNIQUE,
    vendor_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_code INT NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    stock_quantity INT NOT NULL,
    vendor_code INT NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (vendor_code) REFERENCES vendors (vendor_code)
);

INSERT INTO vendors (vendor_code, vendor_name) VALUES
(101, 'Vendor A'),
(102, 'Vendor B'),
(103, 'Vendor C'),
(104, 'Vendor D'),
(105, 'Vendor E');

INSERT INTO products (product_code, product_name, price, stock_quantity, vendor_code) VALUES
(2001, 'Product 1', 100, 50, 101),
(2002, 'Product 2', 150, 60, 102),
(2003, 'Product 3', 200, 70, 103),
(2004, 'Product 4', 250, 80, 104),
(2005, 'Product 5', 300, 90, 105),
(2006, 'Product 6', 350, 100, 101),
(2007, 'Product 7', 400, 110, 102),
(2008, 'Product 8', 450, 120, 103),
(2009, 'Product 9', 500, 130, 104),
(2010, 'Product 10', 550, 140, 102);
