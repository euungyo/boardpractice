package com.example.boardpractice.Entity;

import com.example.boardpractice.DTO.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="board_table")
public class BoardEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String boardPass;
    private int boardHits;

    public static BoardEntity tosaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}

