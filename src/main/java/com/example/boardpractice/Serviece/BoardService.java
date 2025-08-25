package com.example.boardpractice.Serviece;


import com.example.boardpractice.DTO.BoardDTO;
import com.example.boardpractice.Entity.BoardEntity;
import com.example.boardpractice.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity= BoardEntity.tosaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            //: - 오른쪽 객체를 왼쪽의 형(예-BoardEntity)과 동일한 형의 객체를 하나씩 꺼내어 그걸 왼쪽의 이름으로 사용ㅎ할 것.
            //보통 오른쪽은 반복의 대상(배열, 컬렉션)이고 왼쪽은 대입 받을 변수 명
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity =boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }else{
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toupdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardEntity.getId());
    }

    public void delete(Long id){
        boardRepository.deleteById(id); //extends하면 사용할 수 있는 쿼리문들!
    }

}


