create table authorities(
    id serial primary key,
    authority varchar(50)
);

create table users(
    id serial primary key,
    password varchar(255),
    username varchar(255),
    enabled boolean,
    authority_id integer references authorities(id)
);

create table topics(
    id serial primary key,
    name varchar(255),
    status integer,
    author_id integer references users(id)
);

create table posts(
    id serial primary key,
    created timestamp,
    description varchar(255),
    name varchar(255),
    creator_id integer references users(id),
    topic_id integer references topics(id)
);