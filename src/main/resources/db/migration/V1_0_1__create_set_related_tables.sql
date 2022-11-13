create table if not exists flashcards
(
    id    bigserial
        constraint flashcards_pk
            primary key,
    front varchar(255),
    back  varchar(255)
);

alter table flashcards
    owner to postgres;

create table if not exists flashcard_definitions
(
    id               bigserial
        constraint flashcard_definitions_pk
            primary key,
    front_definition varchar(255),
    back_definition  varchar(255),
    flashcard_id     bigint
        constraint flashcard_definitions_flashcards_id_fk
            references flashcards
);

alter table flashcard_definitions
    owner to postgres;

create table if not exists sets
(
    id          bigserial
        constraint sets_pk
            primary key,
    name        varchar(255),
    language    varchar(255),
    is_public   boolean,
    create_date timestamp,
    owner_id    bigint
        constraint sets_users_id_fk
            references users,
    category    varchar(255)
);

alter table sets
    owner to postgres;

create table if not exists users_sets
(
    id      bigint default nextval('flashcards.users_shared_sets_id_seq'::regclass) not null
        constraint users_shared_sets_pk
            primary key,
    user_id bigint
        constraint users_shared_sets_users_id_fk
            references users,
    set_id  bigint
        constraint users_shared_sets_sets_id_fk
            references sets
);

alter table users_sets
    owner to postgres;

create table if not exists played_flashcards
(
    id                      bigint default nextval('flashcards.played_flashcard_id_seq'::regclass) not null
        constraint played_flashcard_pk
            primary key,
    current_level           integer,
    level_change_date       timestamp,
    flashcard_definition_id bigint
        constraint played_flashcard_flashcard_definitions_id_fk
            references flashcard_definitions,
    set_id                  bigint
        constraint played_flashcard_users_sets_id_fk
            references users_sets
);

alter table played_flashcards
    owner to postgres;

