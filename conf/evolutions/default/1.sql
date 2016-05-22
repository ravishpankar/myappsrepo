# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                            bigserial not null,
  post_id                       varchar(255),
  comment_msg                   varchar(500),
  commented_on                  timestamp,
  user_id                       varchar(16),
  constraint pk_comment primary key (id)
);

create table post (
  id                            bigserial not null,
  post_msg                      varchar(500),
  posted_on                     timestamp,
  user_id                       varchar(16),
  constraint pk_post primary key (id)
);

create table usr (
  user_id                       varchar(16),
  pwd                           varchar(255)
);


# --- !Downs

drop table if exists comment cascade;

drop table if exists post cascade;

drop table if exists usr cascade;

