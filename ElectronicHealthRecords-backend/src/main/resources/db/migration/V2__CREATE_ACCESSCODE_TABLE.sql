CREATE TABLE access_code
(
    accessCode  VARCHAR       NOT NULL,
    used        BOOLEAN       NOT NULL,
    CONSTRAINT pk_accessCode PRIMARY KEY (accessCode)
);