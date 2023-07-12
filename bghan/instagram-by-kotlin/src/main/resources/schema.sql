drop table if exists instagram.users;
drop table if exists instagram.feed;
drop table if exists instagram.file;
drop table if exists instagram.follow;
drop table if exists instagram.likes;
drop table if exists instagram.comment;
drop table if exists instagram.reply;

create table instagram.users
(
    id          bigint auto_increment comment '사용자 ID' primary key,
    email       varchar(32)                        not null comment '사용자 email',
    password    varchar(256)                       not null comment '암호화된 사용자 비밀번호',
    nickname    varchar(16)                        not null comment '사용자 별명',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '데이터 생성 시간',
    modified_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '데이터 수정 시간',
    deleted_at  datetime                           null comment '데이터 삭제 시간',
    constraint users_unique_email
        unique (email),
    constraint users_unique_nickname
        unique (nickname)
)
    comment '사용자 정보 관리';


create table instagram.feed
(
    id          bigint auto_increment comment '게시글 ID'
        primary key,
    user_id     bigint                             not null comment '사용자 ID',
    content     text                               not null comment '게시글',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '데이터 생성 시간',
    modified_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '데이터 수정 시간',
    deleted_at  datetime                           null comment '데이터 삭제 시간'
)
    comment '사용자가 게시한 게시글(= 피드)';

create index feed_user_id_index
    on feed (user_id);

create table instagram.file
(
    id          bigint auto_increment comment 'file ID'
        primary key,
    url         varchar(256)                       not null comment 'file url',
    feed_id     bigint                             not null comment '게시글 ID',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '데이터 생성 시간',
    modified_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '데이터 수정 시간',
    deleted_at  datetime                           null comment '데이터 삭제 시간',
    constraint file_unique_url
        unique (url)
)
    comment '게시글에 업로드한 파일 관리';

create index file_feed_id_index
    on file (feed_id);

create table instagram.follow
(
    id           bigint auto_increment comment 'id'
        primary key,
    from_user_id bigint                             not null comment '팔로우 하는 사람',
    to_user_id   bigint                             not null comment '팔로우 당한 사람',
    created_at   datetime default CURRENT_TIMESTAMP not null comment '생성한 시간',
    modified_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정한 시간',
    deleted_at   datetime                           null comment '삭제한 시간'
)
    comment '팔로우';

create index follow_from_user_id_to_user_id_index
    on follow (from_user_id, to_user_id);

create table instagram.likes
(
    id          bigint auto_increment comment 'id'
        primary key,
    user_id     bigint                             not null comment '사용자 ID',
    feed_id     bigint                             not null comment '게시글 ID',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '생성한 시간',
    modified_at datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '수정한 시간',
    deleted_at  datetime                           null comment '삭제한 시간'
)
    comment '좋아요';

create index likes_feed_id_index
    on likes (feed_id);

create index likes_user_id_index
    on likes (user_id);

create table instagram.comment
(
    id          bigint auto_increment comment 'ID'
        primary key,
    user_id     bigint                             not null comment '사용자 ID',
    feed_id     bigint                             not null comment '게시글 ID',
    content     text                               not null comment '댓글',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '생성한 시간',
    modified_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정한 시간',
    deleted_at  datetime                           null comment '삭제한 시간'
)
    comment '댓글';

create index comment_feed_id_index
    on comment (feed_id);

create index comment_user_id_index
    on comment (user_id);

create table instagram.reply
(
    id          bigint auto_increment comment 'ID'
        primary key,
    user_id     bigint                             not null comment '사용자 ID',
    comment_id  bigint                             not null comment '댓글 ID',
    content     text                               not null comment '대댓글',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '생성한 시간',
    modified_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정한 시간',
    deleted_at  datetime                           null comment '삭제한 시간'
)
    comment '대댓글';

create index reply_comment_id_index
    on reply (comment_id);

create index reply_user_id_index
    on reply (user_id);