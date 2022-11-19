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
    user_id bigint not null
        constraint table_name_users_id_fk
            references users,
    set_id  bigint not null
        constraint table_name_sets_id_fk
            references sets,
    constraint table_name_pkey
        primary key (user_id, set_id)
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
    set_id                  bigint,
    user_id                 bigint,
    constraint played_flashcards_users_sets_user_id_set_id_fk
        foreign key (user_id, set_id) references users_sets
            on update cascade on delete cascade
);

alter table played_flashcards
    owner to postgres;

