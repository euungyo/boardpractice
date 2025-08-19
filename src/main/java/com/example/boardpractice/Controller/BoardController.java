package com.example.boardpractice.Controller;


import com.example.boardpractice.DTO.BoardDTO;
import com.example.boardpractice.Serviece.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public String save (@ModelAttribute BoardDTO boardDTO){
        boardService.save(boardDTO);
        return "index";
    }
    ;
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}") //중괄호안에 -> 가변...
    public String findById(@PathVariable Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardDTO", boardDTO);
        return "detail";
    }

}
