package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    // 1. 댓글 조회
    public List<CommentDto> comments(Long articleid) {
        /* 개선 전 코드
        // 1. 댓글 조회
        List<Comment> commnetList = commentRepository.findByArticleId(articleid);
        // 2. 엔티티 -> DTO 변환


        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i = 0; i < commnetList.size(); i++) {
            Comment c = commnetList.get(i);
            CommentDto dto = CommentDto.createCommnetDto(c);
            dtos.add(dto);
        }

        // 3. 결과 반환
        return dtos;

        */

        /* 스트림 사용 */
        return commentRepository.findByArticleId(articleid) // 댓글 엔티티 목록 조회
                .stream()// 댓글 엔티티 목록을 스트림으로 변환
//                .map(comment -> CommentDto.createCommnetDto(comment)) // 엔티티를 DTO로 매핑
                .map(CommentDto::createCommnetDto)
                .collect(Collectors.toList()); // 스트림 데이터를 List 자료형으로 변환
    }

    @Transactional
    // 2. 댓글 생성
    public CommentDto create(Long articleid, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleid)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " + "대상 게시글이 없습니다."));
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // 4. DTO로 변환해 반환
        return CommentDto.createCommnetDto(created);
    }

    @Transactional
    // 3. 댓글 수정
    public CommentDto update(Long id, CommentDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패! " + "대상 댓글이 없습니다."));
        comment.patch(dto);
        Comment update = commentRepository.save(comment);
        return CommentDto.createCommnetDto(update);
    }

    @Transactional
    // 4. 댓글 삭제
    public CommentDto delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패! " + "대상 댓글이 없습니다."));
        commentRepository.delete(comment);
        return CommentDto.createCommnetDto(comment);
    }

}
