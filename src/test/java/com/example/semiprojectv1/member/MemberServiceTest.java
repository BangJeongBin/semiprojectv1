package com.example.semiprojectv1.member;

import com.example.semiprojectv1.domain.MemberDTO;
import com.example.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입 시 필요
public class MemberServiceTest {

    private final MemberService memberService;

    @Test
    @DisplayName("MemberService newMember test")
    public void newMemberTest() {
        // Given
        MemberDTO dto = MemberDTO.builder()
                .userid("abc1234")
                .passwd("987xyz")
                .name("abc123")
                .email("abc123@gmail.com")
                .build();

        // When
        boolean result =  memberService.newMember(dto);

        // Then
        assertTrue(result);
    }
}
