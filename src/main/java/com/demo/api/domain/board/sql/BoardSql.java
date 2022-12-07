package com.demo.api.domain.board.sql;

import org.springframework.data.domain.Sort;

import static java.util.stream.Collectors.joining;

public class BoardSql {

    public static String select(Sort sort) {
        return new StringBuilder()
                .append("SELECT * ")
                .append(" FROM BOARD")
                .append(" WHERE")
                .append(" CREATED_ID = :createdId")
                .append(" ORDER BY ").append(orderBy(sort))
                .append(" LIMIT :pageSize OFFSET :offset")
                .toString();
    }

    public static String count() {
        return  new StringBuilder()
                .append("SELECT COUNT(*)")
                .append(" FROM BOARD")
                .append(" WHERE")
                .append(" CREATED_ID = :createdId")
                .toString();
    }

    private static String orderBy(Sort sort) {
        return sort.stream()
                .map(order -> order.getProperty() + " " + order.getDirection().name())
                .collect(joining(", "));
    }
}
