CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE calendar
(
    id          BIGINT       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    date        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    description VARCHAR(255) NOT NULL,
    duration    INTEGER      NOT NULL,
    doctor_egn  VARCHAR(10),
    patient_egn VARCHAR(10),
    CONSTRAINT pk_calendar PRIMARY KEY (id)
);

CREATE TABLE doctor
(
    egn            VARCHAR(10)  NOT NULL,
    name           VARCHAR(255) NOT NULL,
    email          VARCHAR(255) NOT NULL,
    password       VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    CONSTRAINT pk_doctor PRIMARY KEY (egn)
);

CREATE TABLE examination
(
    id          BIGINT       NOT NULL,
    results     VARCHAR(255) NOT NULL,
    date        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    doctor_egn  VARCHAR(10),
    patient_egn VARCHAR(10),
    CONSTRAINT pk_examination PRIMARY KEY (id)
);

CREATE TABLE patient
(
    egn                  VARCHAR(10)  NOT NULL,
    name                 VARCHAR(255) NOT NULL,
    email                VARCHAR(255) NOT NULL,
    allergies            VARCHAR(255) NOT NULL,
    immunization_statute VARCHAR(255) NOT NULL,
    blood_type           CHAR         NOT NULL,
    weight               INTEGER      NOT NULL,
    date_of_birth        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    laboratory_result    VARCHAR(255),
    illness              VARCHAR(255),
    CONSTRAINT pk_patient PRIMARY KEY (egn)
);

ALTER TABLE doctor
    ADD CONSTRAINT uc_doctor_email UNIQUE (email);

ALTER TABLE patient
    ADD CONSTRAINT uc_patient_email UNIQUE (email);

ALTER TABLE calendar
    ADD CONSTRAINT FK_CALENDAR_ON_DOCTOR_EGN FOREIGN KEY (doctor_egn) REFERENCES doctor (egn);

ALTER TABLE calendar
    ADD CONSTRAINT FK_CALENDAR_ON_PATIENT_EGN FOREIGN KEY (patient_egn) REFERENCES patient (egn);

ALTER TABLE examination
    ADD CONSTRAINT FK_EXAMINATION_ON_DOCTOR_EGN FOREIGN KEY (doctor_egn) REFERENCES doctor (egn);

ALTER TABLE examination
    ADD CONSTRAINT FK_EXAMINATION_ON_PATIENT_EGN FOREIGN KEY (patient_egn) REFERENCES patient (egn);