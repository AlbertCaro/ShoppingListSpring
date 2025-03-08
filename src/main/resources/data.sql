CREATE SCHEMA IF NOT EXISTS shoppinglist_test;

INSERT IGNORE INTO users (id, email, last_name, name, password, username) VALUES (1, "alberto.caro@gmail.com", "Caro", "Alberto", "$2a$10$.bCuK6EgezwYbKpmAnHPauGAQzYWbbhKMHgxrJtngwywVFe6zlzHa", "alberto.caro")
