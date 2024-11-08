INSERT INTO land.weighing_bridges (number, is_available)
VALUES (1, TRUE),
       (2, TRUE),
       (3, FALSE),
       (4, TRUE),
       (5, FALSE),
       (6, TRUE),
       (7, FALSE),
       (8, TRUE),
       (9, TRUE),
       (10, FALSE);

-- Insert statements for trucks
INSERT INTO land.truck (id, license_plate, arrival_time, on_time, location)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'ABC123', NOW(), TRUE, 'ENTERED'),
       ('550e8400-e29b-41d4-a716-446655440001', 'XYZ789', NOW(), FALSE, 'EXITED'),
       ('550e8400-e29b-41d4-a716-446655440002', 'LMN456', NOW(), TRUE, 'DOCK'),
       ('550e8400-e29b-41d4-a716-446655440003', 'IS52MNA', '2024-10-30 14:55:00', TRUE,
        'EXITED'), -- Arrived 5 mins before
       ('550e8400-e29b-41d4-a716-446655440004', 'TRK002', '2024-10-30 15:25:00', FALSE,
        'EXITED'), -- Arrived 5 mins before
       ('550e8400-e29b-41d4-a716-446655440005', 'TRK003', '2024-10-30 15:55:00', TRUE,
        'EXITED'), -- Arrived 5 mins before
       ('550e8400-e29b-41d4-a716-446655440006', 'TRK004', '2024-10-30 16:25:00', FALSE,
        'EXITED'), -- Arrived 5 mins before
       ('550e8400-e29b-41d4-a716-446655440007', 'TRK005', '2024-10-30 16:55:00', FALSE,
        'EXITED'), -- Arrived 5 mins before
       ('550e8400-e29b-41d4-a716-446655440008', 'TRK006', '2024-10-30 17:25:00', TRUE,
        'EXITED'); -- Arrived 5 mins before


INSERT INTO land.payload_delivery_tickets (weight, delivery_time, id, warehouse_id, license_plate, material_type,
                                           truck_id)
VALUES (30, '2024-10-30 15:00:00.000000', '69ea89af-89b9-411b-b300-9a7a7d3b68ad',
        '550e8400-e29b-41d4-a716-446655440004', 'IS52MNA', 'SLAG', '550e8400-e29b-41d4-a716-446655440003'),
       (25, '2024-10-30 15:30:00.000000', '69ea89af-89b9-411b-b300-9a7a7d3b68a1',
        '550e8400-e29b-41d4-a716-446655440002', 'TRK002', 'CEMENT', '550e8400-e29b-41d4-a716-446655440004'),
       (50, '2024-10-30 16:00:00.000000', '69ea89af-89b9-411b-b300-9a7a7d3b68a2',
        '550e8400-e29b-41d4-a716-446655440001', 'TRK003', 'IRON_ORE', '550e8400-e29b-41d4-a716-446655440005'),
       (45, '2024-10-30 16:30:00.000000', '69ea89af-89b9-411b-b300-9a7a7d3b68a3',
        '550e8400-e29b-41d4-a716-446655440003', 'TRK004', 'PETCOKE', '550e8400-e29b-41d4-a716-446655440006'),
       (60, '2024-10-30 17:00:00.000000', '69ea89af-89b9-411b-b300-9a7a7d3b68a4',
        '550e8400-e29b-41d4-a716-446655440004', 'TRK005', 'SLAG', '550e8400-e29b-41d4-a716-446655440007'),
       (35, '2024-10-30 17:30:00.000000', '69ea89af-89b9-411b-b300-9a7a7d3b68a5',
        '550e8400-e29b-41d4-a716-446655440002', 'TRK006', 'CEMENT', '550e8400-e29b-41d4-a716-446655440008');

INSERT INTO land.landside_warehouses (id, fill_percentage, seller_id, material_type)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 10, '012f6507-7214-4b7a-9e28-efe5424245ae', 'GYPSUM'),
       ('550e8400-e29b-41d4-a716-446655440001', 20, '012f6507-7214-4b7a-9e28-efe5424245ae', 'IRON_ORE'),
       ('550e8400-e29b-41d4-a716-446655440002', 30, '012f6507-7214-4b7a-9e28-efe5424245ae', 'CEMENT'),
       ('550e8400-e29b-41d4-a716-446655440003', 40, '012f6507-7214-4b7a-9e28-efe5424245ae', 'PETCOKE'),
       ('550e8400-e29b-41d4-a716-446655440004', 50, '012f6507-7214-4b7a-9e28-efe5424245ae', 'SLAG'),
       ('550e8400-e29b-41d4-a716-446655440005', 60, '123e4567-e89b-12d3-a456-426614174001', 'GYPSUM'),
       ('550e8400-e29b-41d4-a716-446655440006', 70, '123e4567-e89b-12d3-a456-426614174001', 'IRON_ORE'),
       ('550e8400-e29b-41d4-a716-446655440007', 80, '123e4567-e89b-12d3-a456-426614174001', 'CEMENT'),
       ('550e8400-e29b-41d4-a716-446655440008', 90, '123e4567-e89b-12d3-a456-426614174001', 'PETCOKE'),
       ('550e8400-e29b-41d4-a716-446655440009', 100, '123e4567-e89b-12d3-a456-426614174001', 'SLAG'),
       ('550e8400-e29b-41d4-a716-446655440010', 10, '123e4567-e89b-12d3-a456-426614174002', 'GYPSUM'),
       ('550e8400-e29b-41d4-a716-446655440011', 20, '123e4567-e89b-12d3-a456-426614174002', 'IRON_ORE'),
       ('550e8400-e29b-41d4-a716-446655440012', 30, '123e4567-e89b-12d3-a456-426614174002', 'CEMENT'),
       ('550e8400-e29b-41d4-a716-446655440013', 40, '123e4567-e89b-12d3-a456-426614174002', 'PETCOKE'),
       ('550e8400-e29b-41d4-a716-446655440014', 50, '123e4567-e89b-12d3-a456-426614174002', 'SLAG'),
       ('550e8400-e29b-41d4-a716-446655440015', 60, '123e4567-e89b-12d3-a456-426614174003', 'GYPSUM'),
       ('550e8400-e29b-41d4-a716-446655440016', 70, '123e4567-e89b-12d3-a456-426614174003', 'IRON_ORE'),
       ('550e8400-e29b-41d4-a716-446655440017', 80, '123e4567-e89b-12d3-a456-426614174003', 'CEMENT'),
       ('550e8400-e29b-41d4-a716-446655440018', 90, '123e4567-e89b-12d3-a456-426614174003', 'PETCOKE'),
       ('550e8400-e29b-41d4-a716-446655440019', 100, '123e4567-e89b-12d3-a456-426614174003', 'SLAG');

INSERT INTO warehousing.warehouses (id,name, warehouse_id, base_stock, seller_id, material_type, base_stock_timestamp)
VALUES ('cda96b52-858d-4b67-a40c-942ba9a9c120','WH1','550e8400-e29b-41d4-a716-446655440000', 50, '012f6507-7214-4b7a-9e28-efe5424245ae', 'GYPSUM', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c121','WH2','550e8400-e29b-41d4-a716-446655440001', 100, '012f6507-7214-4b7a-9e28-efe5424245ae', 'IRON_ORE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c122','WH3','550e8400-e29b-41d4-a716-446655440002', 150, '012f6507-7214-4b7a-9e28-efe5424245ae', 'CEMENT', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c123','WH4','550e8400-e29b-41d4-a716-446655440003', 200, '012f6507-7214-4b7a-9e28-efe5424245ae', 'PETCOKE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c124','WH5','550e8400-e29b-41d4-a716-446655440004', 250, '012f6507-7214-4b7a-9e28-efe5424245ae', 'SLAG', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c125','WH6','550e8400-e29b-41d4-a716-446655440005', 300, '123e4567-e89b-12d3-a456-426614174001', 'GYPSUM', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c126','WH7','550e8400-e29b-41d4-a716-446655440006', 350, '123e4567-e89b-12d3-a456-426614174001', 'IRON_ORE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c127','WH8','550e8400-e29b-41d4-a716-446655440007', 400, '123e4567-e89b-12d3-a456-426614174001', 'CEMENT', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c128','WH9','550e8400-e29b-41d4-a716-446655440008', 450, '123e4567-e89b-12d3-a456-426614174001', 'PETCOKE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c129','WH10','550e8400-e29b-41d4-a716-446655440009', 500, '123e4567-e89b-12d3-a456-426614174001', 'SLAG', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c130','WH11','550e8400-e29b-41d4-a716-446655440010', 10, '123e4567-e89b-12d3-a456-426614174002', 'GYPSUM', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c140','WH12','550e8400-e29b-41d4-a716-446655440011', 20, '123e4567-e89b-12d3-a456-426614174002', 'IRON_ORE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c150','WH13','550e8400-e29b-41d4-a716-446655440012', 30, '123e4567-e89b-12d3-a456-426614174002', 'CEMENT', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c160','WH14','550e8400-e29b-41d4-a716-446655440013', 200, '123e4567-e89b-12d3-a456-426614174002', 'PETCOKE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c170','WH15','550e8400-e29b-41d4-a716-446655440014', 250, '123e4567-e89b-12d3-a456-426614174002', 'SLAG', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c180','WH16','550e8400-e29b-41d4-a716-446655440015', 300, '123e4567-e89b-12d3-a456-426614174003', 'GYPSUM', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c190','WH17','550e8400-e29b-41d4-a716-446655440016', 350, '123e4567-e89b-12d3-a456-426614174003', 'IRON_ORE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c111','WH18','550e8400-e29b-41d4-a716-446655440017', 400, '123e4567-e89b-12d3-a456-426614174003', 'CEMENT', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c112','WH19','550e8400-e29b-41d4-a716-446655440018', 450, '123e4567-e89b-12d3-a456-426614174003', 'PETCOKE', NOW()),
       ('cda96b52-858d-4b67-a40c-942ba9a9c113','WH20','550e8400-e29b-41d4-a716-446655440019', 500, '123e4567-e89b-12d3-a456-426614174003', 'SLAG', NOW());







