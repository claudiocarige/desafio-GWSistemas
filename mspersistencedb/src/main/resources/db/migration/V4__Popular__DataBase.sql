INSERT INTO product (name, weight, volume, quantity_stock, position_stock, value)
VALUES ('Chuteira', 1.0, 0.5, 50, 'R3.A1.C10', 10.50),
       ('Camiseta', 0.2, 0.1, 100, 'R1.B2.C5', 5.99),
       ('Calça', 0.5, 0.3, 75, 'R2.A3.C1', 12.75),
       ('Meia', 0.1, 0.05, 200, 'R1.B1.C1', 2.00),
       ('Bola', 0.7, 0.4, 30, 'R3.C2.C8', 8.25),
       ('Luva', 0.25, 0.15, 60, 'R2.B3.C12', 4.50),
       ('Caneleira', 0.3, 0.2, 45, 'R1.A2.C9', 6.75),
       ('Joelheira', 0.4, 0.25, 35, 'R3.B1.C3', 7.99),
       ('Mochila', 1.2, 0.7, 25, 'R2.C1.C6', 15.00),
       ('Boné', 0.15, 0.1, 80, 'R1.C3.C2', 3.99);

INSERT INTO address (street, number, city, state, country, postal_code, latitude_coordinate,
                     longitude_coordinate)
VALUES ('Rua Valeria', '10', 'Salvador', 'Bahia', 'Brasil', '41.000-000', -23.550520, -46.633308),
       ('Av. Brasil', '123', 'Rio de Janeiro', 'Rio de Janeiro', 'Brasil', '20000-000', -22.906847, -43.172896),
       ('Rua da Tecnologia', '500', 'São Paulo', 'São Paulo', 'Brasil', '01000-000', -23.555771, -46.639557),
       ('Rua das Flores', '25', 'Curitiba', 'Paraná', 'Brasil', '80000-000', -25.428420, -49.273311),
       ('Rua das Palmeiras', '02', 'Feira de Santana', 'Bahia', 'Brasil', '40000-000', -25.428420, -49.273311),
       ('Rua Estevam Barbosa', '12', 'Ilheus', 'Bahia', 'Brasil', '41000-000', -25.428420, -49.273311),
       ('Av. Borges de Medeiros', '15', 'Porto Alegre', 'Rio Grande do Sul', 'Brasil', '90000-000', -30.034647,
        -51.217658);

INSERT INTO customers (customer_name, corporate_name, cnpj, phone_number, whatsapp, principal_email,
                       responsible_employee, dtype, address_id)
VALUES ('Axe Meu Rei', 'Banda Axé Meu Rei LTDA', '36.458.554/0001-84', '71992125550', '71991125550',
        'axemeurei@gmail.com', 'Claudio Carige', 'CompanyCustomer',
        (SELECT id FROM address WHERE street = 'Rua Valeria' AND number = '10')),
       ('Copy Imagem', 'Copy Mais LTDA', '43.822.769/0001-05', '21987654321', '21987654322',
        'controle@copyimagem.com.br', 'Gabriel Carigé', 'CompanyCustomer',
        (SELECT id FROM address WHERE street = 'Av. Brasil' AND number = '123')),
       ('Tech Solutions', 'Soluções Tecnológicas Inovadoras S/A', '76.521.987/0001-62', '1133221100', '11998877665',
        'atendimento@copyimagem.com.br', 'Claudio Pimentel', 'CompanyCustomer',
        (SELECT id FROM address WHERE street = 'Rua da Tecnologia' AND number = '500')),
       ('Padaria Pão Quente', 'Delícias da Manhã Padaria e Confeitaria LTDA', '12.345.678/0001-90', '4132109876',
        '41991234567', 'rubensvenicio2023@gmail.com.br', 'Rubens Venicio', 'CompanyCustomer',
        (SELECT id FROM address WHERE street = 'Rua das Flores' AND number = '25')),
       ('CK Produções', 'CK Produções LTDA', '98.765.432/0001-18', '5134567890', '51987650000',
        'ckproducoesculturais@gmail.com', 'Carigé', 'CompanyCustomer',
        (SELECT id FROM address WHERE street = 'Av. Borges de Medeiros' AND number = '15'));

INSERT INTO customers (customer_name, cpf, phone_number, whatsapp, principal_email,
                       responsible_employee, dtype, address_id)
VALUES ('Amauri Junior', '342.773.960-03', '71992200505', '71992200577',
        'kcarige@hotmail.com', 'Amauri Junior', 'IndividualCustomer',
        (SELECT id FROM address WHERE street = 'Rua das Palmeiras' AND number = '02')),
       ('Bell Marques', '616.266.440-63', '21987654338', '21987654333',
        'ccarige@gmail.com', 'Bell Marques', 'IndividualCustomer',
        (SELECT id FROM address WHERE street = 'Rua Estevam Barbosa' AND number = '12'));

INSERT INTO customers_email_list (customers_id, email_list)
VALUES ((SELECT id FROM customers WHERE customer_name = 'Axe Meu Rei'), 'axemeurei@axemeurey.com'),
       ((SELECT id FROM customers WHERE customer_name = 'Copy Imagem'), 'venda@gmail.com'),
       ((SELECT id FROM customers WHERE customer_name = 'Copy Imagem'), 'copyimagem@gmail.com'),
       ((SELECT id FROM customers WHERE customer_name = 'Tech Solutions'), 'jose@techsolutions.com.br'),
       ((SELECT id FROM customers WHERE customer_name = 'Padaria Pão Quente'), 'ana@paoquente.com.br'),
       ((SELECT id FROM customers WHERE customer_name = 'Padaria Pão Quente'), 'pedidos@paoquente.com.br'),
       ((SELECT id FROM customers WHERE customer_name = 'CK Produções'), 'pedro@livrariacultural.com.br');