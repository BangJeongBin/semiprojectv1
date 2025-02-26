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