CREATE TABLE "Factura" (
  "id" integer PRIMARY KEY,
  "client_id" integer,
  "data_emisio" date,
  "total" numeric,
  "iva" numeric,
  "observacions" varchar NOT NULL
);

CREATE TABLE "Client" (
  "id" integer PRIMARY KEY,
  "nom" varchar,
  "llinatges" varchar,
  "telefon" integer,
  "direccio" varchar,
  "email" varchar NULL
);

CREATE TABLE "Treball" (
  "id" integer PRIMARY KEY,
  "client_id" integer,
  "descripcio" varchar,
  "data" date,
  "estat" enum(pendent,en_curs,finalitzat),
  "preu" numeric,
  "materials" varchar,
  "imatge" bytea
);

CREATE TABLE "FacturaTreball" (
  "id" integer PRIMARY KEY,
  "factura_id" integer,
  "treball_id" integer
);

CREATE TABLE "Calendari" (
  "id" integer PRIMARY KEY,
  "treball_id" integer,
  "data_entrada" date,
  "comentaris" varchar
);

ALTER TABLE "Treball" ADD FOREIGN KEY ("client_id") REFERENCES "Client" ("id");

ALTER TABLE "Factura" ADD FOREIGN KEY ("client_id") REFERENCES "Client" ("id");

ALTER TABLE "FacturaTreball" ADD FOREIGN KEY ("factura_id") REFERENCES "Factura" ("id");

ALTER TABLE "FacturaTreball" ADD FOREIGN KEY ("treball_id") REFERENCES "Treball" ("id");

ALTER TABLE "Entrega" ADD FOREIGN KEY ("treball_id") REFERENCES "Treball" ("id");
