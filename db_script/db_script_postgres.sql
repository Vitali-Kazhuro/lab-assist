CREATE TABLE "sample" (
	"id" serial NOT NULL,
	"cipher" VARCHAR(255) NOT NULL UNIQUE,
	"series" VARCHAR(255),
	"sampling_report" VARCHAR(255) NOT NULL,
	"quantity" VARCHAR(255) NOT NULL,
	"object_of_study_id" integer NOT NULL,
	"test_report_id" integer NOT NULL,
	CONSTRAINT "sample_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "applicant" (
	"id" serial NOT NULL,
	"organization" VARCHAR(255) NOT NULL,
	"address" VARCHAR(255) NOT NULL,
	"mailing_address" VARCHAR(255) NOT NULL,
	"iban" VARCHAR(255) NOT NULL,
	"bank" VARCHAR(255) NOT NULL,
	"bank_address" VARCHAR(255) NOT NULL,
	"bic" VARCHAR(255) NOT NULL,
	"unn" VARCHAR(255) NOT NULL,
	"okpo" VARCHAR(255) NOT NULL,
	"telephones" VARCHAR(255) NOT NULL,
	"email" VARCHAR(255) NOT NULL,
	"contract_number" VARCHAR(255) NOT NULL,
	"contract_date" DATE NOT NULL,
	"head_position" VARCHAR(255) NOT NULL,
	"head_name" VARCHAR(255) NOT NULL,
	CONSTRAINT "applicant_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "employee" (
	"id" serial NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"position" VARCHAR(255) NOT NULL,
	CONSTRAINT "employee_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "test_report" (
	"id" serial NOT NULL,
	"protocol_number" integer NOT NULL UNIQUE,
	"date" DATE NOT NULL,
	"start_date" DATE NOT NULL,
	"end_date" DATE NOT NULL,
	"applicant_id" integer NOT NULL,
	CONSTRAINT "test_report_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "sampling_authority" (
	"id" serial NOT NULL,
	"applicant_id" integer NOT NULL,
	"title" VARCHAR(255) NOT NULL,
	CONSTRAINT "sampling_authority_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "object_of_study" (
	"id" serial NOT NULL UNIQUE,
	"title" VARCHAR(255) NOT NULL,
	"producer" VARCHAR(255),
	"sampling_authority_id" integer NOT NULL,
	"regulatory_document_id" integer NOT NULL,
	CONSTRAINT "object_of_study_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "regulatory_document" (
	"id" serial NOT NULL UNIQUE,
	"title" VARCHAR(255) NOT NULL,
	CONSTRAINT "regulatory_document_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "element" (
	"id" serial NOT NULL UNIQUE,
	"title" VARCHAR(255) NOT NULL,
	"symbol" VARCHAR(255),
	CONSTRAINT "element_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "norm" (
	"id" serial NOT NULL,
	"regulatory_document_id" integer NOT NULL,
	"element_id" integer NOT NULL,
	"value" VARCHAR(255) NOT NULL,
	"units" VARCHAR(255) NOT NULL,
	CONSTRAINT "norm_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "test_report_doer" (
	"test_report_id" integer NOT NULL,
	"employee_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);

CREATE TABLE "test_method" (
   "id" serial NOT NULL,
   "title" VARCHAR(255) NOT NULL,
   CONSTRAINT "test_method_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "sample_norm" (
	"id" serial NOT NULL,
	"sample_id" integer NOT NULL,
	"norm_id" integer NOT NULL,
	"result1" DOUBLE PRECISION,
	"result2" DOUBLE PRECISION,
	"detection_limit" VARCHAR(255),
	CONSTRAINT "sample_norm_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "weather" (
   "id" serial NOT NULL,
   "date" DATE NOT NULL,
   "k53_10_temperature" REAL NOT NULL,
   "k53_16_temperature" REAL NOT NULL,
   "k42_10_temperature" REAL NOT NULL,
   "k42_16_temperature" REAL NOT NULL,
   "k53_10_humidity" REAL NOT NULL,
   "k53_16_humidity" REAL NOT NULL,
   "k42_10_humidity" REAL NOT NULL,
   "k42_16_humidity" REAL NOT NULL,
   "k53_10_pressure" integer NOT NULL,
   "k53_16_pressure" integer NOT NULL,
   "k42_10_pressure" integer NOT NULL,
   "k42_16_pressure" integer NOT NULL,
   CONSTRAINT "weather_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "test_report_test_method" (
   "test_report_id" integer NOT NULL,
   "test_method_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);

CREATE TABLE "usr"(
	"id"       serial NOT NULL,
	"active"   BOOLEAN NOT NULL,
	"password" VARCHAR(255) NULL,
	"username" VARCHAR(255) NULL,
	CONSTRAINT "urs_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "user_role"(
	"user_id" serial NOT NULL,
	"roles"   VARCHAR(255) NULL
) WITH (
  OIDS=FALSE
);

ALTER TABLE "user_role" ADD CONSTRAINT "user_role_fk0" FOREIGN KEY ("user_id") REFERENCES "usr" ("id")

ALTER TABLE "test_report_test_method" ADD CONSTRAINT "test_report_test_method_fk0" FOREIGN KEY ("test_report_id") REFERENCES "test_report"("id");
ALTER TABLE "test_report_test_method" ADD CONSTRAINT "test_report_test_method_fk1" FOREIGN KEY ("test_method_id") REFERENCES "test_method"("id");

ALTER TABLE "sample" ADD CONSTRAINT "sample_fk0" FOREIGN KEY ("object_of_study_id") REFERENCES "object_of_study"("id");
ALTER TABLE "sample" ADD CONSTRAINT "sample_fk1" FOREIGN KEY ("test_report_id") REFERENCES "test_report"("id");

ALTER TABLE "test_report" ADD CONSTRAINT "test_report_fk0" FOREIGN KEY ("applicant_id") REFERENCES "applicant"("id");

ALTER TABLE "sampling_authority" ADD CONSTRAINT "sampling_authority_fk0" FOREIGN KEY ("applicant_id") REFERENCES "applicant"("id");

ALTER TABLE "object_of_study" ADD CONSTRAINT "object_of_study_fk0" FOREIGN KEY ("sampling_authority_id") REFERENCES "sampling_authority"("id");
ALTER TABLE "object_of_study" ADD CONSTRAINT "object_of_study_fk1" FOREIGN KEY ("regulatory_document_id") REFERENCES "regulatory_document"("id");

ALTER TABLE "norm" ADD CONSTRAINT "norm_fk0" FOREIGN KEY ("regulatory_document_id") REFERENCES "regulatory_document"("id");
ALTER TABLE "norm" ADD CONSTRAINT "norm_fk1" FOREIGN KEY ("element_id") REFERENCES "element"("id");

ALTER TABLE "test_report_doer" ADD CONSTRAINT "test_report_doer_fk0" FOREIGN KEY ("test_report_id") REFERENCES "test_report"("id");
ALTER TABLE "test_report_doer" ADD CONSTRAINT "test_report_doer_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");

ALTER TABLE "sample_norm" ADD CONSTRAINT "sample_norm_fk0" FOREIGN KEY ("sample_id") REFERENCES "sample"("id");
ALTER TABLE "sample_norm" ADD CONSTRAINT "sample_norm_fk1" FOREIGN KEY ("norm_id") REFERENCES "norm"("id");

INSERT INTO "test_method" (title) VALUES ('');

INSERT INTO "usr" (username, password, active) VALUES
                             ('Administrator', '$2y$08$ni59Jix7BLXm8L0m8u33bu8Nmi2F9BWPpNnLWjyelZcf8w4tJ3FRK', true),
                             ('admin',  '$2y$08$nrIJ9s6C76GH4RhxksTsrOxrALVvCPaE1GfvAeFqh933S4NWjv9KG', true);
INSERT INTO "user_role" (user_id, roles) VALUES
                            (1, 'ADMIN'),
                            (2, 'ADMIN');