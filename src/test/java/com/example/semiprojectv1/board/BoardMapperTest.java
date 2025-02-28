package com.example.semiprojectv1.board;

import com.example.semiprojectv1.domain.BoardDTO;
import com.example.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@MybatisTest
@RequiredArgsConstructor    // final 필드변수로 생성자로 생성 (final 변수를 쓰기 위한 애노테이션)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입 시 필요
// H2 데이터베이스 사용중지(Test는 default가 H2 데이터베이스) + test/pom.xml 에서도 h2 데이터 베이스 주석 처리
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardMapperTest {

    // @Autowired보다 final을 사용해 생성자를 이용한 의존성 주입이 더 좋음
    private final BoardRepository boardMapper;
    @Value("${board.page-size}") private int pageSize;

    @Test
    @DisplayName("BoardMapper Select test")
    void insertTest() {
        // Given : 테스트에 사용할 데이터 제공
        int stnum = 0;  // 조회 할 게시물 시작 위치

        // When : 데이터로 테스트할 기능 호출
        List<BoardDTO> result = boardMapper.selectBoard(stnum, pageSize);

        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", result);
        assertNotNull(result);
    }


    @Test
    @DisplayName("BoardMapper find test")
    void findTest() {
        // Given : 테스트에 사용할 데이터 제공
        Map<String, Object> params = new HashMap<>(); // 컨테이너 변수
        params.put("stnum", 0);
        params.put("pageSize", 35);
        params.put("findtype", "title");
        params.put("findkey", "오픈");

        // When : 데이터로 테스트할 기능 호출
        /*List<BoardDTO> result = boardMapper.selectFindBoard(0, 35, "title", "오픈");*/
        /*List<BoardDTO> result = boardMapper.selectFindBoard(0, 35, "userid", "abc");*/
        /*List<BoardDTO> result = boardMapper.selectFindBoard(0, 35, "content", "오픈");*/
        // HashMap형태로 검색 관련 데이터 넘기기
        List<BoardDTO> result = boardMapper.selectFindBoard(params);

        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", result);
        assertNotNull(result);  // 널 여부 확인 - 리스트일 경우 의미없는 검사
        assertThat(result).isNotEmpty();    // 비어있는지 여부 확인
        assertThat(result.size()).isGreaterThan(1); // 결과 갯수 확인
    }


    @Test
    @DisplayName("BoardMapper countFind test")
    void countFindTest() {
        // Given : 테스트에 사용할 데이터 제공
        Map<String, Object> params = new HashMap<>(); // 컨테이너 변수
        params.put("pageSize", 35);
        params.put("findtype", "title");
        params.put("findkey", "오픈");

        // When : 데이터로 테스트할 기능 호출
        // HashMap형태로 검색 관련 데이터 넘기기
        int result = boardMapper.countFindBoard(params);

        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", result);
        assertThat(result).isGreaterThan(0); // 결과 갯수 확인
    }
}
