drop table if exists security_authorities;
commit;
drop table if exists security_users;
commit;
create table if not exists security_users
(
    username varchar(255) not null,
    password varchar(255) not null,
    enabled  tinyint      not null default 1,
    primary key (username)
);
create table if not exists security_authorities
(
    authority varchar(255) not null,
    primary key (authority)
);
create table if not exists security_user_authority
(
    username  varchar(255) not null,
    authority varchar(255) not null,
    foreign key (username) references security_users (username),
    foreign key (authority) references security_authorities (authority),
    primary key (username, authority)
);
commit;