CREATE TABLE IF NOT EXISTS country (
id varchar(80) PRIMARY KEY,
name varchar(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS state (
id varchar(80) PRIMARY KEY,
"name" varchar(250) NOT NULL,
country_id varchar(80) NOT NULL,
FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE IF NOT EXISTS "city" (
id varchar(80) PRIMARY KEY,
"name" varchar(250) NOT NULL,
state_id varchar(80) NOT NULL,
FOREIGN KEY (state_id) REFERENCES state (id)
);

CREATE TABLE IF NOT EXISTS document_type (
id varchar(80) PRIMARY KEY,
acronym varchar(5) NOT null,
"name" varchar(100) NOT null
);

CREATE TABLE IF NOT EXISTS customer (
id varchar(80) PRIMARY KEY,
"name" varchar(255) NOT null,
nit varchar(100) NOT null,
status varchar(100) NOT null
);

CREATE TABLE IF NOT EXISTS skill_type (
id varchar(80) PRIMARY KEY,
name varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS skill (
id varchar(80) PRIMARY KEY,
name varchar(100) NOT null,
type_id varchar(80) NOT null,
FOREIGN KEY (type_id) REFERENCES skill_type (id)
);

CREATE TABLE IF NOT EXISTS sofkiano (
id varchar(80) PRIMARY KEY,
document_type_id varchar(80) NOT null,
document_number varchar(50) NOT null,
name varchar(100) NOT null,
last_name varchar(100) NOT null,
entry_date int8 NOT null,
customer_id varchar(80),
status varchar(100) NOT null,
FOREIGN KEY (document_type_id) REFERENCES document_type (id),
FOREIGN KEY (customer_id) REFERENCES customer (id),
CONSTRAINT sofkiano_un_doc UNIQUE (document_type_id, document_number)
);

CREATE TABLE IF NOT EXISTS location (
id varchar(80) PRIMARY KEY,
city_id varchar(80) NOT NULL,
address varchar(255) NOT NULL,
additional_indications varchar(100),
neighborhood varchar(100),
sofkiano_id varchar(80) NOT null,
status varchar(100) NOT NULL,
FOREIGN KEY (city_id) REFERENCES city (id),
FOREIGN KEY (sofkiano_id) REFERENCES sofkiano (id)
);

CREATE TABLE IF NOT EXISTS sofkiano_stack (
id varchar(80) PRIMARY KEY,
sofkiano_id varchar(80) NOT null,
skill_id varchar(80) NOT null,
customer_id varchar(80) DEFAULT (NULL) ,
FOREIGN KEY (sofkiano_id) REFERENCES sofkiano (id),
FOREIGN KEY (skill_id) REFERENCES skill (id),
FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-------------------------------------------------------------
-- Insert data
-------------------------------------------------------------
--Paises
INSERT INTO country
(id, "name")
VALUES('51eae14b-ec8c-4c5d-bb31-cb09df6658a1', 'Colombia'),
('dc4ba9bb-a63d-4c0d-bbce-775603c87f08', 'Uruguay'),
('1d916433-975a-4638-946f-eba73cb1b46a', 'Ecuador');

-- Departamentos/estados
INSERT INTO state
(id, "name", country_id)
VALUES('26adc1e9-65a3-439a-a775-cd29e97afbd2', 'Antioquia', '51eae14b-ec8c-4c5d-bb31-cb09df6658a1'),
('45941757-497b-4571-9b13-07b617cfc023', 'Atlántico', '51eae14b-ec8c-4c5d-bb31-cb09df6658a1'),
('a0c056d0-3bbe-43fa-ba7d-8c5137efd530', 'Cundinamarca', '51eae14b-ec8c-4c5d-bb31-cb09df6658a1'),
('fae2fc39-c430-450d-b141-24c360905d52', 'Paysandú', 'dc4ba9bb-a63d-4c0d-bbce-775603c87f08'),
('a87ffb84-e20b-4942-a82a-bc7e3455cd9a', 'Central', 'dc4ba9bb-a63d-4c0d-bbce-775603c87f08'),
('bc564383-bcfd-4dc2-8989-b2608ec41f15', 'Pichincha', '1d916433-975a-4638-946f-eba73cb1b46a');

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Ciudades
INSERT INTO city
(id, "name", state_id)
VALUES(uuid_generate_v4(), 'Medellin', '26adc1e9-65a3-439a-a775-cd29e97afbd2'),
(uuid_generate_v4(), 'Sabaneta', '26adc1e9-65a3-439a-a775-cd29e97afbd2'),
(uuid_generate_v4(), 'Itagui', '26adc1e9-65a3-439a-a775-cd29e97afbd2'),
(uuid_generate_v4(), 'Caldas', '26adc1e9-65a3-439a-a775-cd29e97afbd2'),
(uuid_generate_v4(), 'Barranquilla', '45941757-497b-4571-9b13-07b617cfc023'),
(uuid_generate_v4(), 'Soledad', '45941757-497b-4571-9b13-07b617cfc023'),
(uuid_generate_v4(), 'Soacha', 'a0c056d0-3bbe-43fa-ba7d-8c5137efd530'),
(uuid_generate_v4(), 'Zipaquirá', 'a0c056d0-3bbe-43fa-ba7d-8c5137efd530'),
(uuid_generate_v4(), 'Bogotá', 'a0c056d0-3bbe-43fa-ba7d-8c5137efd530'),
(uuid_generate_v4(), 'Chía', 'a0c056d0-3bbe-43fa-ba7d-8c5137efd530'),
(uuid_generate_v4(), 'Paysandú', 'fae2fc39-c430-450d-b141-24c360905d52'),
(uuid_generate_v4(), 'San Félix', 'fae2fc39-c430-450d-b141-24c360905d52'),
(uuid_generate_v4(), 'San Lorenzo', 'a87ffb84-e20b-4942-a82a-bc7e3455cd9a'),
(uuid_generate_v4(), 'San Antonio', 'fae2fc39-c430-450d-b141-24c360905d52'),
(uuid_generate_v4(), 'Villeta', 'fae2fc39-c430-450d-b141-24c360905d52'),
(uuid_generate_v4(), 'Quito', 'bc564383-bcfd-4dc2-8989-b2608ec41f15'),
(uuid_generate_v4(), 'Machachi', 'bc564383-bcfd-4dc2-8989-b2608ec41f15'),
(uuid_generate_v4(), 'Tabacundo', 'bc564383-bcfd-4dc2-8989-b2608ec41f15');

-- Tipos de documentos
INSERT INTO document_type
(id, acronym, "name")
VALUES(uuid_generate_v4(), 'CC', 'Cédula de Ciudadanía'),
(uuid_generate_v4(), 'TI', 'Tarjeta de Identidad'),
(uuid_generate_v4(), 'CE', 'Cédula de Extranjería'),
(uuid_generate_v4(), 'PA', 'Pasaporte');

-- Tipos de habilidades
INSERT INTO skill_type
(id, "name")
VALUES('c9dd31c2-94bd-42ae-9a1b-8f3df100ed04', 'Frontend'),
('1a7a6f14-c1f3-4f57-9b05-5e3653967d89', 'Backend'),
('039f49a2-aea8-402f-9658-7996e7394ea5', 'Mobile'),
('1d303744-f869-4a23-a258-895fd750b5bd', 'QA'),
('34dc4fc4-16e7-42f9-8797-3bc5ee205a64', 'Diseño'),
('65800967-97cc-40d9-9da2-239d413edf3d', 'Infraestructura'),
('749b67d3-a73e-434e-9fd3-4b25e04ebec4', 'Bases de datos');

-- Habilidades
INSERT INTO skill
(id, "name", type_id)
VALUES(uuid_generate_v4(), 'Java', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'Go', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'Junit', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'S3-AWS', '65800967-97cc-40d9-9da2-239d413edf3d'),
(uuid_generate_v4(), 'SQS-AWS', '65800967-97cc-40d9-9da2-239d413edf3d'),
(uuid_generate_v4(), 'Lambdas-AWS', '65800967-97cc-40d9-9da2-239d413edf3d'),
(uuid_generate_v4(), 'Firestore-Firebase', '65800967-97cc-40d9-9da2-239d413edf3d'),
(uuid_generate_v4(), 'Rabbit MQ', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'Groovy', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'C#', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'NodeJs', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), '.Net', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'Postgres', '749b67d3-a73e-434e-9fd3-4b25e04ebec4'),
(uuid_generate_v4(), 'MongoDB', '749b67d3-a73e-434e-9fd3-4b25e04ebec4'),
(uuid_generate_v4(), 'Redis', '749b67d3-a73e-434e-9fd3-4b25e04ebec4'),
(uuid_generate_v4(), 'MySQL', '749b67d3-a73e-434e-9fd3-4b25e04ebec4'),
(uuid_generate_v4(), 'Javascript', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'React', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'Angular', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'Vue', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'Jest', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'Typescript', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'Spring Boot', '1a7a6f14-c1f3-4f57-9b05-5e3653967d89'),
(uuid_generate_v4(), 'Ant Design', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'Bootstrap', 'c9dd31c2-94bd-42ae-9a1b-8f3df100ed04'),
(uuid_generate_v4(), 'Flutter', '039f49a2-aea8-402f-9658-7996e7394ea5'),
(uuid_generate_v4(), 'Dart', '039f49a2-aea8-402f-9658-7996e7394ea5'),
(uuid_generate_v4(), 'Swift', '039f49a2-aea8-402f-9658-7996e7394ea5'),
(uuid_generate_v4(), 'Android', '039f49a2-aea8-402f-9658-7996e7394ea5'),
(uuid_generate_v4(), 'React Native', '039f49a2-aea8-402f-9658-7996e7394ea5'),
(uuid_generate_v4(), 'Jenkins', '1d303744-f869-4a23-a258-895fd750b5bd'),
(uuid_generate_v4(), 'Figma', '34dc4fc4-16e7-42f9-8797-3bc5ee205a64'),
(uuid_generate_v4(), 'Sketch', '34dc4fc4-16e7-42f9-8797-3bc5ee205a64');

INSERT INTO customer
(id, "name", nit, status)
VALUES(uuid_generate_v4(), 'Sura', '32165489', 'ACTIVE'),
(uuid_generate_v4(), 'Compensar', '65897555', 'ACTIVE'),
(uuid_generate_v4(), 'Metro de Medellin', '200001631', 'ACTIVE');










