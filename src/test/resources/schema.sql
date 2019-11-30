CREATE TABLE NATION
(
    ID SERIAL,
    NAME character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "NATIONS_pkey" PRIMARY KEY (ID),
    CONSTRAINT "NATION_ID_key" UNIQUE (ID),
    CONSTRAINT "NATION_NAME_key" UNIQUE (NAME)

);

CREATE TABLE TOUR
(
    COMMON_CASH_DESCRIPTION text COLLATE pg_catalog."default",
    COMMON_CASH_INCLUDED_SERVICES text COLLATE pg_catalog."default",
    DURATION character varying(30) COLLATE pg_catalog."default",
    ID SERIAL,
    INCLUDED_SERVICES text COLLATE pg_catalog."default",
    ITINERARY text COLLATE pg_catalog."default",
    NATION_ID integer,
    NOT_INCLUDED_SERVICES text COLLATE pg_catalog."default",
    TITLE character varying(40) COLLATE pg_catalog."default" NOT NULL,
    PRICE character varying(20) COLLATE pg_catalog."default" NOT NULL,
    COMPANY_ID integer NOT NULL,
    LINK_TO_TOUR character varying(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "TOUR_pkey" PRIMARY KEY (ID),
    CONSTRAINT fk_nation FOREIGN KEY (NATION_ID)
        REFERENCES NATION (ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
