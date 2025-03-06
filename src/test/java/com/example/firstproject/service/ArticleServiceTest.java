package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
//    @Transactional
    void create_성공() {
        // 1. 예상 데이터 작성하기
        String title = "라라라라";
        String content = "4444";
        ArticleForm form = new ArticleForm(null, "라라라라", "4444");
        Article expected = new Article(5L, title, content);

        // 2. 실제 데이터 획득하기
        Article articles = articleService.create(form);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void index() {
        // 1. 예상 데이터 작성하기
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        Article d = new Article(4L, "라라라라", "4444");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c, d));

        // 2. 실제 데이터 획득하기
        List<Article> articles = articleService.index();

        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success_존재하는_id_입력() {
        // 1. 예샹 데이터 작성하기
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터 획득하기
        Article articles = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_fail_존재하지_않는_id_입력() {
        // 1. 예샹 데이터 작성하기
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터 획득하기
        Article articles = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected, articles);
    }

    @Test
//    @Transactional
    void create_실패() {
        // 1. 예상 데이터 작성하기
        Long id = 5L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm form = new ArticleForm(id, "라라라라", "4444");
        Article expected = null;

        // 2. 실제 데이터 획득하기
        Article articles = articleService.create(form);

        // 3. 비교 및 검증
        assertEquals(expected, articles);
    }
}