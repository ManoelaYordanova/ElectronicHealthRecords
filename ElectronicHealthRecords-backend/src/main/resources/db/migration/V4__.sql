ALTER TABLE patient
    DROP COLUMN blood_type;

ALTER TABLE patient
    ADD blood_type VARCHAR(255) NOT NULL;