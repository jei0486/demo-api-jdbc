package com.demo.api.domain.board.sql

public class BoardSqls {

    public static String SELECT_BY_SEQ_LIST = """ 
        SELECT SEQ , SUBJECT , CONTENT , HITS , CREATED_ID 
        FROM BOARD
            WHERE SEQ IN (:seqList)
    """;

}
