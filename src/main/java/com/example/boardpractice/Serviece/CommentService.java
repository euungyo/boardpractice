package com.example.boardpractice.Serviece;


import com.example.boardpractice.DTO.CommentDTO;
import com.example.boardpractice.Entity.BoardEntity;
import com.example.boardpractice.Entity.CommentEntity;
import com.example.boardpractice.Repository.BoardRepository;
import com.example.boardpractice.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        //부모 엔티티(BoardEntity)조회
        Optional<BoardEntity> optionalBoardEntity=boardRepository.findById(commentDTO.getBoardId());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity=optionalBoardEntity.get();
            CommentEntity commentEntity= CommentEntity.toSaveEntity(commentDTO,boardEntity);
            return commentRepository.save(commentEntity).getId();
        }else{
            return null;
        }
    }

    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity= boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList=commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        //Entity리스트->DTOList
        List<CommentDTO> commentDTOList=new ArrayList<>();
        for(CommentEntity commentEntity:commentEntityList){
            CommentDTO commentDTO= CommentDTO.toCommentDTO(commentEntity,boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
