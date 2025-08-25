package com.example.boardpractice.Controller;


import com.example.boardpractice.DTO.BoardDTO;
import com.example.boardpractice.Serviece.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public BoardDTO save(@RequestBody BoardDTO boardDTO) {  // @ModelAttribute → @RequestBody
        boardService.save(boardDTO);
        return boardDTO; // 저장된 데이터 그대로 리턴 (혹은 id만 리턴해도 됨)
    }

    @GetMapping("/")
    public List<BoardDTO> findAll() {
        return boardService.findAll();
    }

    @GetMapping("/{id}") //중괄호안에 -> 가변...
    public String findById(@PathVariable Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardDTO", boardDTO);
        return "detail";
    }

    @PutMapping("/update/{id}")
    public BoardDTO update(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        boardDTO.setId(id);
        return boardService.update(boardDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        boardService.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("deletedId", id);
        return response; // JSON 응답
    }
}

//map은 원래 keyvalue자료구조라서 json 객체랑 구조가 1:1매칭
//-> 빠르게 커스텀 json 객체 만들기에 자주 씀
