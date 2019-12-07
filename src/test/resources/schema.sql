CREATE SCHEMA IF NOT EXISTS tourscanner;

CREATE TABLE tourscanner.nation
(
    id SERIAL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "NATIONS_pkey" PRIMARY KEY (id),
    CONSTRAINT "NATION_ID_key" UNIQUE (id),
    CONSTRAINT "NATION_NAME_key" UNIQUE (name)

);

CREATE TABLE tourscanner.tour
(
    common_cash_description text COLLATE pg_catalog."default",
    common_cash_included_services text COLLATE pg_catalog."default",
    duration character varying(30) COLLATE pg_catalog."default",
    id SERIAL,
    included_services text COLLATE pg_catalog."default",
    itinerary text COLLATE pg_catalog."default",
    nation_id integer,
    not_included_services text COLLATE pg_catalog."default",
    title character varying(40) COLLATE pg_catalog."default" NOT NULL,
    price character varying(20) COLLATE pg_catalog."default" NOT NULL,
    company_id integer NOT NULL,
    link_to_tour character varying(40) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "TOUR_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_nation FOREIGN KEY (nation_id)
        REFERENCES nation (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
