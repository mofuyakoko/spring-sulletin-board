CREATE TABLE MS_USER(
    USER_ID VARCHAR(50) NOT NULL PRIMARY KEY,
    PASSWD VARCHAR(100) NOT NULL,
    USER_NM VARCHAR(100) NOT NULL,
    BIRTHDAY DATE NOT NULL,
    ROLE VARCHAR(50) NOT NULL,
    CREATE_DATE TIMESTAMP,
    UPDATE_DATE TIMESTAMP
);
CREATE UNIQUE INDEX IDX_MS_USER_1 ON MS_USER (USER_ID);