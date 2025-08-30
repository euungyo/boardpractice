package com.example.boardpractice.Controller;


import com.example.boardpractice.DTO.BoardDTO;
import com.example.boardpractice.Serviece.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "save"; // templates/save.html
    }

    @PostMapping("/save")
    @ResponseBody
    public BoardDTO save(@RequestBody BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return boardDTO; // JSON 응답
    }

    //@GetMapping("/")
    //@ResponseBody
    //public List<BoardDTO> findAll() {
    //    return boardService.findAll(); // 1)JSON 응답
    //}

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "list";  // templates/list.html
    } //2)html보여주고 싶을때

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardDTO", boardDTO);
        return "detail"; // templates/detail.html
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public BoardDTO update(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        boardDTO.setId(id);
        return boardService.update(boardDTO); // JSON 응답
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        boardService.delete(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("deletedId", id);
        return response; // JSON 응답
    }

    //board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page=1) Pageable pageable, Model model) {
       // pageable.getPageNumber();
    Page<BoardDTO> boardList=boardService.paging(pageable);
    int blockLimit =3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 현재 사용자가 1또는 2또는 3페이지에 머물면 1
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return"paging";
    }
}
//map은 원래 keyvalue자료구조라서 json 객체랑 구조가 1:1
