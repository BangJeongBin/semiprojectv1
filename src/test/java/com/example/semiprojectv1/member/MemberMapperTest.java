package com.example.semiprojectv1.member;

import com.example.semiprojectv1.domain.MemberDTO;
import com.example.semiprojectv1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@MybatisTest
@RequiredArgsConstructor    // final 필드변수로 생성자로 생성 (final 변수를 쓰기 위한 애노테이션)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입 시 필요
public class MemberMapperTest {

    // @Autowired보다 final을 사용해 생성자를 이용한 의존성 주입이 더 좋음
    private final MemberRepository memberMapper;

    @Test
    @DisplayName("MemberMapper Insert test")
    void insertTest() {
        // Given : 테스트에 사용할 데이터 제공
        MemberDTO dto = MemberDTO.builder()
                .userid("abc1234")
                .passwd("987xyz")
                .name("abc123")
                .email("abc123@gmail.com")
                .build();

        // When : 데이터로 테스트할 기능 호출
        int result =  memberMapper.insertMember(dto);

        // Then : 호출되고 난 후 결과값 확인
        log.info("result : {}", result);
        assertThat(result).isEqualTo(1);
    }
}
