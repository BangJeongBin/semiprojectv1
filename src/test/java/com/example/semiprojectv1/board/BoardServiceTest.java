package com.example.semiprojectv1.board;

import com.example.semiprojectv1.domain.BoardDTO;
import com.example.semiprojectv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // 생성자 주입 시 필요
public class BoardServiceTest {

    private final BoardService boardService;

    @Test
    @DisplayName("BoardService readAll test")
    public void readAllTest() {
        // Given

        // When
        List<BoardDTO> results = boardService.readBoard();

        // Then
        assertNotNull(results);
    }
}
