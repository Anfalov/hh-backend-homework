create table area
(
    id integer primary key,
    name text not null
);

create table employer
(
    id integer primary key,
    name text not null,
    date_create timestamp not null,
    description text,
    area_id integer not null references area(id),
    comment text,
    views_count integer not null
);

create table vacancy
(
    id integer primary key,
    name text not null,
    area_id integer not null references area(id),
    salary_from integer,
    salary_to integer,
    salary_gross boolean,
    salary_currency text,
    created_at timestamp not null,
    employer_id integer not null references employer(id),
    comment text,
    date_create timestamp not null,
    views_count integer not null
);