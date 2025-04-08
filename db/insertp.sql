INSERT INTO product (
	name, category, brand,
    inventory_qty, price, sku, specifications)
VALUES (
	'Ryzen 7 7800X3D', 'cpu', 'AMD',
    1000, 339.99, 6537139,   
    '[{
		"core_count": 8,
		"core_clock": 4.2,
		"boost_clock": 5,
		"tdp": 120,
		"graphics": "Radeon",
		"smt": true
    }]'
);
    
INSERT INTO product (
	name, category, brand,
    inventory_qty, price, sku, specifications)
VALUES (
  'B650 GAMING X AX', 'motherboard', 'Gigabyte',
  1000, 179.99, 6582206,
  '[{
    "socket": "AM5",
    "form_factor": "ATX",
    "max_memory": 192,
    "memory_slots": 4,
    "color": "Black / Gray"
	}]'
);
  
  
INSERT INTO product (
	name, category, brand,
    inventory_qty, price, sku, specifications)
VALUES (
  'Vengeance 32 GB', 'memory', 'Corsair',
  1500, 113.99, 6562313,
  '[{
	"speed": [5, 5600],
    "modules": [2, 16],
    "price_per_gb": 3.562,
    "color": "Black",
    "first_word_latency": 12.857,
    "cas_latency": 36
	}]'
);

INSERT INTO product (
	name, category, brand,
    inventory_qty, price, sku, specifications)
VALUES (
  'GAMING OC', 'video-card', 'Gigabyte',
  1500, 499.99, 6559282,
  '[{
	"chipset": "Radeon RX 7800 XT",
    "memory": 16,
    "core_clock": 1295,
    "boost_clock": 2565,
    "color": "Black",
    "length": 302
	}]'
);  

INSERT INTO product (
	name, category, brand,
    inventory_qty, price, sku, specifications)
VALUES (
  '990 Pro 2TB', 'interal-storage', 'Samsung',
  750, 173.99, 6523595,
  '[{
    "capacity": 2000,
    "price_per_gb": 0.087,
    "type": "SSD",
    "form_factor": "M.2-2280",
    "interface": "M.2 PCIe 4.0 X4"
	}]'
); 