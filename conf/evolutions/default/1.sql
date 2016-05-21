# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        varchar(255) not null,
  post_id                   varchar(255),
  comment                   varchar(500),
  commented_on              timestamp,
  user_id                   varchar(16),
  constraint pk_comment primary key (id))
;

create table post (
  id                        varchar(255) not null,
  post                      varchar(500),
  posted_on                 timestamp,
  user_id                   varchar(16),
  constraint pk_post primary key (id))
;

create table user (
  user_id                   varchar(16),
  pwd                       varchar(255))
;

create sequence comment_seq;

create sequence post_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comment;

drop table if exists post;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_seq;

drop sequence if exists post_seq;

