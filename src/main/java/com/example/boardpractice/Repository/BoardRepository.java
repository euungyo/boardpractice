package com.example.boardpractice.Repository;

import com.example.boardpractice.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    //update board_table set board_hits=board_hits+1 where id=?
    @Modifying//아래와 같은 쿼리 실행시 꼭 붙여야 함.
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    //entity를 기준으로 하는 쿼리. 어노테이션 조건 중 nativeQuery를 ture 하면 native query사용 가능
    void updateHits(@Param("id") Long id);
}
