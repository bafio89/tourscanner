create table tourscanner.tour
(
    common_cash_description       text,
    common_cash_included_services text,
    duration                      varchar,
    included_services             text,
    itinerary                     text,
    nation_id                     integer,
    not_included_services         text,
    title                         varchar not null,
    price                         varchar not null,
    company_id                    integer not null,
    link_to_tour                  varchar not null
        constraint unique_link
        unique,
    id                            serial  not null
        constraint tour_pkey
        primary key
);

alter table tourscanner.tour
    owner to postgres;

create table tourscanner.nation
(
    id   serial      not null
        constraint "NATIONS_pkey"
        primary key,
    name varchar(20) not null
        constraint nation_name_unique
        unique
);

alter table tourscanner.nation
    owner to postgres;

