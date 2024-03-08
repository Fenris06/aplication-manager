 create table applications (
        create_date timestamp(6),
        id bigserial not null,
        user_id bigint not null,
        status varchar(20) not null check (status in ('SENT','DRAFT','ACCEPTED','REJECTED')),
        name varchar(100) not null,
        phone_number varchar(100) not null,
        description varchar(500) not null,
        primary key (id)
);

create table roles (
        id serial not null,
        name varchar(50) not null,
        primary key (id)
);

create table user_roles (
        role_id integer not null,
        user_id bigint not null,
        primary key (role_id, user_id)
);

create table users (
        id bigserial not null,
        user_name varchar(50) not null unique,
        email varchar(60) not null unique,
        password varchar(255) not null,
        primary key (id)
);

alter table if exists applications
       add constraint applications_users_fk
       foreign key (user_id)
       references users;

alter table if exists user_roles
       add constraint user_roles_roles_fk
       foreign key (role_id)
       references roles;

alter table if exists user_roles
       add constraint user_roles_users_fk
       foreign key (user_id)
       references users;