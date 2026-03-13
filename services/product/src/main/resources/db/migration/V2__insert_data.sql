INSERT INTO category (id, name, description)
VALUES (1, 'Electronics', 'Electronic gadgets and devices'),
       (2, 'Books', 'Educational and entertainment books'),
       (3, 'Clothing', 'Men and women clothing'),
       (4, 'Home Appliances', 'Appliances for home use'),
       (5, 'Sports', 'Sports equipment and accessories'),
       (6, 'Toys', 'Kids toys and games'),
       (7, 'Furniture', 'Home and office furniture'),
       (8, 'Groceries', 'Daily grocery items'),
       (9, 'Beauty', 'Beauty and personal care products'),
       (10, 'Automotive', 'Vehicle accessories and parts');
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (1, 'Laptop', '15 inch business laptop', 50, 75000.00, 1),
       (2, 'Smartphone', '5G Android smartphone', 120, 45000.00, 1),
       (3, 'Bluetooth Headphones', 'Wireless over-ear headphones', 80, 3500.00, 1),

       (4, 'Spring Boot Guide', 'Complete Spring Boot reference book', 30, 899.00, 2),
       (5, 'Java Programming', 'Advanced Java concepts book', 40, 799.00, 2),

       (6, 'Men T-Shirt', 'Cotton round neck T-shirt', 200, 499.00, 3),
       (7, 'Women Jacket', 'Winter fashion jacket', 60, 1999.00, 3),

       (8, 'Microwave Oven', '25L convection microwave', 20, 12500.00, 4),
       (9, 'Vacuum Cleaner', 'High power vacuum cleaner', 15, 8500.00, 4),

       (10, 'Football', 'Professional size football', 70, 999.00, 5),
       (11, 'Cricket Bat', 'English willow cricket bat', 35, 3500.00, 5),

       (12, 'Remote Control Car', 'Battery powered toy car', 45, 1499.00, 6),
       (13, 'Building Blocks', 'Creative toy blocks set', 100, 899.00, 6),

       (14, 'Office Chair', 'Ergonomic office chair', 25, 6500.00, 7),
       (15, 'Wooden Table', 'Solid wood dining table', 10, 15000.00, 7),

       (16, 'Rice 10kg', 'Premium quality rice bag', 90, 750.00, 8),
       (17, 'Cooking Oil', '1L sunflower oil bottle', 150, 180.00, 8),

       (18, 'Face Cream', 'Hydrating face cream', 60, 399.00, 9),
       (19, 'Shampoo', 'Anti hair fall shampoo', 110, 299.00, 9),

       (20, 'Car Phone Holder', 'Dashboard mobile holder', 75, 499.00, 10);