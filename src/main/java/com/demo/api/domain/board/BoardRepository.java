package com.demo.api.domain.board;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends PagingAndSortingRepository<BoardEntity,Long> ,BoardRepositoryCustom{

    @Modifying
    @Query("UPDATE BOARD SET SUBJECT = :subject , CONTENT = :content, modifyId = :modifiedId WHERE SEQ = :seq")
    boolean updateBoard(@Param("seq") Long seq,@Param("subject") String subject,@Param("content") String content,@Param("modifiedId") String modifiedId);
}
