package com.demo.api.domain.board.sql

public class BoardSqls {

    public static final String SELECT_BY_SEQ_LIST = """ 
        SELECT SEQ , SUBJECT , CONTENT , HITS , CREATED_ID 
        FROM BOARD
            WHERE SEQ IN (:seqList)
    """;

    public static final String SELECT_BY_CreatedIdAndState = """ 
        SELECT b.*  
        FROM BOARD b
             JOIN USER u on b.CREATED_ID = u.LOGIN_ID
        WHERE u.STATE = :state  
          AND b.CREATED_ID = :createdId
        ORDER BY :order : sort
        LIMIT :pageSize OFFSET :offset
    """;

    public static final String countByCreatedIdAndState = """ 
        SELECT COUNT(*)
        FROM BOARD b
             JOIN USER u on b.CREATED_ID = u.LOGIN_ID
        WHERE u.STATE = :state  
          AND b.CREATED_ID = :createdId
    """;


}
