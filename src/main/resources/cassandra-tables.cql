CREATE KEYSPACE test WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor': 1
    };

USE test;

CREATE TABLE festivals_by_name (
    name text,
    id UUID,
    start_date timestamp,
    end_date timestamp,
    PRIMARY KEY(name)
);

CREATE TABLE festival_shows (
    festival_name text,
    show_id UUID,
    artist_name text,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    PRIMARY KEY ((festival_name), show_id)
);




CREATE TABLE artists_by_name (
    id UUID,
    name text,
    genres Map<UUID, text>,
    PRIMARY KEY (name)
);


CREATE TABLE genres_by_name (
    genre text,
    id UUID,
    PRIMARY KEY(name)
);


