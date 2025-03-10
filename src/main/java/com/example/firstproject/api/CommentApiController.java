package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/api/articles/{articleid}/comment")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleid) {
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleid);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/articles/{articleid}/comment")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleid, @RequestBody CommentDto dto){
        // 서비스에 위임
        CommentDto responseDto = commentService.create(articleid, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    };

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto responseDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에 위임
        CommentDto responseDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
