-- 1. Create vendor table if not exists
CREATE TABLE IF NOT EXISTS vendor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- 4. Insert a dummy vendor if not exists
INSERT INTO vendor (name)
SELECT * FROM (SELECT 'Default Vendor') AS tmp
WHERE NOT EXISTS (
    SELECT id FROM vendor WHERE name = 'Default Vendor'
) LIMIT 1;
