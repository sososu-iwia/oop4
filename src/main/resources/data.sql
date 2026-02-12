-- Initial data (only runs on first startup with spring.jpa.defer-datasource-initialization=true)
-- Or run manually after tables are created
INSERT INTO institutions (name, address) 
SELECT 'School #1', 'Street 1, City' WHERE NOT EXISTS (SELECT 1 FROM institutions WHERE name = 'School #1');
INSERT INTO institutions (name, address) 
SELECT 'University ABC', 'Avenue 5, City' WHERE NOT EXISTS (SELECT 1 FROM institutions WHERE name = 'University ABC');
