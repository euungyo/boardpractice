package com.example.boardpractice.Controller;


import com.example.boardpractice.DTO.BoardDTO;
import com.example.boardpractice.DTO.CommentDTO;
import com.example.boardpractice.Serviece.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comment") //requestmapping -> url 지정
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CommentDTO commentDTO) { //<?> -> 와일드카드. if else부분에서 리턴하는 응답 타입이 다름. 와일드 카드로 경우에 따라 다르게 하겠다는 의미
        System.out.println("commentDTO = " + commentDTO);

        Long saveResult = commentService.save(commentDTO);
        if (saveResult != null) {
            // 작성 성공 → 해당 게시글의 댓글 목록 리턴
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
            return ResponseEntity.ok(commentDTOList);
        } else {
            // 실패 → 메시지와 404 응답
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("해당 게시글이 존재하지 않습니다.");
        }
    }

    @GetMapping("/list/{boardId}")
    public ResponseEntity<List<CommentDTO>> findAll(@PathVariable Long boardId) {
        List<CommentDTO> commentDTOList = commentService.findAll(boardId);
        return ResponseEntity.ok(commentDTOList);
    }


    @PutMapping("/update/{id}")
    public CommentDTO update(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        return commentService.update(commentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        commentService.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("deletedId", id);
        return response; // JSON 응답
    }
}
