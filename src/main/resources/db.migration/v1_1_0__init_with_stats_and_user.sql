create table user_stats
(
    id                 bigserial
        constraint user_stats_pk
            primary key,
    user_id            bigint not null
        constraint uk_cgfgfs7fk42h7ck71lrs42sou
            unique,
    done_flashcards    integer,
    learned_num        integer,
    correct_flashcards integer
);

alter table user_stats
    owner to postgres;

create unique index user_stats_user_id_uindex
    on user_stats (user_id);

create table time_logs
(
    id               bigserial
        constraint time_logs_pk
            primary key,
    stats_id         bigint
        constraint time_logs_user_stats_id_fk
            references stats.user_stats,
    recorded_day     date,
    recorded_minutes integer
);

alter table time_logs
    owner to postgres;

create table set_stats
(
    id                 bigserial
        constraint set_stats_pk
            primary key,
    set_id             bigint,
    done_flahcards     integer,
    correct_flashcards integer
);

alter table set_stats
    owner to postgres;

create unique index set_stats_set_id_uindex
    on set_stats (set_id);

create table users
(
    id         bigserial
        constraint users_pk
            primary key,
    first_name varchar(255),
    last_name  varchar(255),
    email      varchar(255),
    password   varchar(255)
);

alter table users
    owner to postgres;

