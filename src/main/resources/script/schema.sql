create table User
(
    UserNo int auto_increment comment '사용자 번호',
    UserId VARCHAR(50) null comment '사용자ID',
    UserPw varchar(50) not null comment '사용자 암호',
    constraint User_pk
        primary key (UserNo),
    constraint User_pk
        unique (UserId)
)
    comment '사용자 정보';

