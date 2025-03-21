package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* CASE 1 : 4번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, article , "lucelin", "반지의제왕");
            Comment b = new Comment(2L, article , "dak34224", "해리포터");
            Comment c = new Comment(3L, article , "cionc221", "어벤져스");

            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString());
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* CASE 1 : "lucelin"의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "lucelin";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Article A = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Article B = new Article(5L, "당신의 소울 푸드는?", "댓글 고고");
            Article C = new Article(6L, "당신의 취미는?", "댓글 고고고");
            Comment a = new Comment(1L, A , "lucelin", "반지의제왕");
            Comment b = new Comment(4L, B , "lucelin", "제육볶음");
            Comment c = new Comment(7L, C , "lucelin", "게임");

            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString());
        }
    }
}