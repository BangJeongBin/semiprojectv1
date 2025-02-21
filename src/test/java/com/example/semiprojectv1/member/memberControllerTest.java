package com.example.semiprojectv1.member;

import com.example.semiprojectv1.domain.Member;
import com.example.semiprojectv1.repository.MemberRepository;
import com.example.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입 시 필요
public class memberControllerTest {

    private final MockMvc mockMvc;
    private final MemberRepository memberMapper;

    @Test
    @DisplayName("/join Post request test")
    public void joinOk() throws Exception {
        // Given
        String userid = "abc1234";
        String passwd = "abc1234";
        String name = "abc1234";
        String email = "abc1234@gmail.com";

        // When
        mockMvc.perform(post("/member/join")
                .param("userid", userid)
                .param("passwd", passwd)
                .param("name", name)
                .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login"));

        // Then
        /*Member member = memberMapper.findByUserid(userid);
        assertThat(member).isNull();
        assertThat(member.getMno()).isNull();
        assertThat(member.getRegdate()).isNull();
        assertThat(member.getName()).isEqualTo("abc1234");
        assertThat(member.getEmail()).isEqualTo("abc1234@gmail.com");*/
    }
}
