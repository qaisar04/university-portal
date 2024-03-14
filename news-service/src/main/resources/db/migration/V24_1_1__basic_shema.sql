create schema if not exists develop;

create table develop.news
(
    id             bigint primary key,
    title          varchar(255) not null,
    content        text,
    published_date TIMESTAMP
);

create table develop.news_tags
(
    news_id bigint not null,
    tag     varchar(255),
    foreign key (news_id) references develop.news (id)
);

create table develop.file_attachment
(
    id        bigint primary key,
    file_name varchar(255),
    source    varchar(255),
    url       varchar(255),
    news_id   bigint,
    foreign key (news_id) references develop.news (id)
);