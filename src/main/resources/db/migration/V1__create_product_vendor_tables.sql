-- 1. Create vendor table if not exists
CREATE TABLE IF NOT EXISTS vendor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- 2. Create product table if not exists
CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-- 3. Alter product table to add vendor_id column and foreign key
ALTER TABLE product
ADD COLUMN vendor_id BIGINT,
ADD CONSTRAINT fk_product_vendor
    FOREIGN KEY (vendor_id)
    REFERENCES vendor(id);

-- 4. Insert a dummy vendor if not exists
INSERT INTO vendor (name)
SELECT * FROM (SELECT 'Default Vendor') AS tmp
WHERE NOT EXISTS (
    SELECT id FROM vendor WHERE name = 'Default Vendor'
) LIMIT 1;

-- 5. Update all product rows to reference the dummy vendor
UPDATE product
SET vendor_id = (
    SELECT id FROM vendor WHERE name = 'Default Vendor'
)
WHERE vendor_id IS NULL;
