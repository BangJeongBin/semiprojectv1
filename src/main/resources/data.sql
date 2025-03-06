-- members 테이블
-- server를 재시작 할 때마다 dummy data가 생성됨
insert into members (userid, passwd, name, email) values
('냐옹냐옹', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '987xyz', '987xyz@987xyz.co.kr'),
('abc123', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', 'abc123', 'abc123@abc123co.kr'),
('987xyz', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '987xyz', '987xyz@987xyz.co.kr');


-- replys 테이블
insert into replys (comments, userid, ref, pno) values ('안녕하세요', 'abc123', 1, 3000);
insert into replys (comments, userid, ref, pno) values ( '방가방가!', '987xyz', 2, 3000);
insert into replys (comments, userid, ref, pno) values ( '오늘춥네여', '냐옹냐옹', 3, 3000);
insert into replys (comments, userid, ref, pno) values ( '날씨는요?', 'abc123', 2, 3000);


-- gallerys 테이블
insert into gallerys (title, userid, contents, simgname) values ('삼삼데이 고기(소갈비살)','abc123','삼삼데이에 애들 데리고 소갈비살 먹었습니다
수입산인데 맛이 괜찮습니다.', 'cowmeat01_small.jpg');

insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat01.jpg',113223);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat02.jpg',189332);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat03.jpg',177652);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat04.jpg',129814);
insert into gallery_images (gno, imgname, imgsize) values (1, 'cowmeat05.jpg',148542);