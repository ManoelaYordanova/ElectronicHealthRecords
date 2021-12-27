ALTER TABLE access_code
    ADD access_code VARCHAR(255);

ALTER TABLE access_code
    DROP COLUMN accesscode;

ALTER TABLE patient
    DROP COLUMN laboratory_result;

ALTER TABLE access_code
    ADD CONSTRAINT pk_access_code PRIMARY KEY (access_code);