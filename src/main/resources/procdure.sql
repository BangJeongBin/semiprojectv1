-- 1. 기존 저장 프로시저 제거
DROP PROCEDURE IF EXISTS insert_boards;

-- 2. 프로시저 생성
CREATE PROCEDURE insert_boards()
BEGIN
    DECLARE i INT DEFAULT 1;

    -- 1000 * 3 == 3,000건의 데이터 삽입
    WHILE i <= 1000 DO
            INSERT INTO boards (title, userid, contents)
                VALUES ('', '', '');
            INSERT INTO boards (title, userid, contents)
                VALUES ('', '', '');
            INSERT INTO boards (title, userid, contents)
                VALUES ('', '', '');
            SET i = i + 1;
        END WHILE;
END;

-- 3. 프로시저 호출
CALL insert_boards();