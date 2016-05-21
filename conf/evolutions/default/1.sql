# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table post (
  id                            bigint auto_increment not null,
  post                          varchar(500),
  posted_on                     datetime(6),
  user_id                       varchar(16),
  constraint pk_post primary key (id)
);


create table comment (
  id                            bigint auto_increment not null,
  post_id                       varchar(255),
  comment                       varchar(500),
  commented_on                  datetime(6),
  user_id                       varchar(16),
  constraint pk_comment primary key (id)
);

create table user (
  user_id                       varchar(16),
  pwd                           varchar(255)
);


# --- !Downs

drop table if exists comment;

drop table if exists post;

drop table if exists user;
