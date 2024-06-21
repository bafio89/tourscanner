create table tour
(
    id                            serial  not null
        constraint tour_pkey
            primary key,
    title                         varchar not null,
    link_to_tour                  varchar not null
        constraint unique_link
            unique,
    company_id                    integer not null,
    common_cash_description       text,
    common_cash_included_services text,
    duration                      varchar,
    included_services             text,
    itinerary                     text,
    nation_id                     integer,
    not_included_services         text,
    price                         varchar not null,
    active                        varchar default '0'
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

