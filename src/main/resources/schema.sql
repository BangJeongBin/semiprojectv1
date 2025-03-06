create table if not exists members (
    mno int auto_increment primary key,
    userid varchar(18) unique not null,
    passwd varchar(64) not null,
    name varchar(50) not null,
    email varchar(100) not null,
    regdate datetime default current_timestamp
);

create table if not exists boards (
    bno int auto_increment,
    title varchar(128) not null,
    userid varchar(18) not null,
    regdate datetime default current_timestamp,
    views int default 0,
    thumbs int default 0,
    contents text not null,
    primary key (bno),
    foreign key (userid) references members (userid)
);

create table if not exists replys (
    rno int auto_increment,
    userid varchar(18) not null,
    comments text not null,
    regdate datetime default current_timestamp,
    ref int not null,
    pno int not null,
    primary key (rno)
    -- foreign key (userid) references members (userid)
    -- foreign key (pno) references boards (bno)
);

create table if not exists gallerys (
    gno int auto_increment,
    title varchar(128) not null,
    userid varchar(18) not null,
    regdate datetime default current_timestamp,
    views int default 0,
    thumbs int default 0,
    contents text not null,
    simgname varchar(128) not null, /* 썸네일 이미지 */
    primary key (gno),
    foreign key (userid) references members (userid)
);

create table if not exists gallery_images (
    gino int auto_increment,
    gno int not null,
    imgname varchar(128) not null,
    imgsize int not null,
    regdate datetime default current_timestamp,
    primary key (gino),
    foreign key (gno) references gallerys (gno)
);