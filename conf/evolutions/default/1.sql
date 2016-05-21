# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  comment_id                varchar(255) not null,
  post_id                   varchar(255),
  comment                   varchar(500),
  commented_on              datetime(6),
  user_id                   varchar(16),
  constraint pk_comment primary key (comment_id))
;

create table post (
  post_id                   varchar(255) not null,
  post                      varchar(500),
  posted_on                 datetime(6),
  user_id                   varchar(16),
  constraint pk_post primary key (post_id))
;

create table user (
  user_id                   varchar(16),
  pwd                       varchar(255))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table comment;

drop table post;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

