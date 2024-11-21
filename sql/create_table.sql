
-- 创建库
create database if not exists lanqian;

-- 切换库
use lanqian;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;


insert into user (userAccount, userPassword, unionId, mpOpenId, userName, userAvatar, userProfile, userRole, isDelete)
values
-- 普通用户数据
('zhangsan', 'e10adc3949ba59abbe56e057f20f883e', 'wx_union_2001', 'mp_201', '张三', 'https://randomuser.me/api/portraits/men/11.jpg', '热爱编程，正在学习后端开发。', 'user', 0),
('lisi', 'd8578edf8458ce06fbc5bb76a58c5ca4', 'wx_union_2002', 'mp_202', '李四', 'https://randomuser.me/api/portraits/men/12.jpg', '摄影爱好者，喜欢探索世界的每一个角落。', 'user', 0),
('wangxiaoming', '25d55ad283aa400af464c76d713c07ad', null, null, '王小明', 'https://randomuser.me/api/portraits/men/13.jpg', '高中生，梦想成为一名软件工程师。', 'user', 0),
('liuyun', '5f4dcc3b5aa765d61d8327deb882cf99', 'wx_union_2003', 'mp_203', '刘云', 'https://randomuser.me/api/portraits/women/14.jpg', '健身教练，专注于运动与健康管理。', 'user', 0)

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';
