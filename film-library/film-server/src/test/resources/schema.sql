create table if not exists authors(
    author_id bigserial,
    full_name varchar(255),
    primary key (author_id)
);

create table if not exists genres(
    genre_id bigserial,
    name varchar(255),
    primary key (genre_id)
);

create table if not exists films(
    film_id bigserial,
    title varchar(255),
    author_id bigint references authors(author_id) on delete cascade,
    genre_id bigint references genres(genre_id) on delete cascade,
    film_year bigserial,
    rating real,
    primary key (film_id)
);