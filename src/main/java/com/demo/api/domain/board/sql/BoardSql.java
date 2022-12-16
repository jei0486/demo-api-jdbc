package com.demo.api.domain.board.sql;

import org.springframework.data.domain.Sort;

import static java.util.stream.Collectors.joining;

public class BoardSql {

    public static final String selectByCreatedIdAndState(Sort sort) {
        return new StringBuilder()
                .append("SELECT b.* ")
                .append(" FROM BOARD b")
                .append(" JOIN USER u on b.CREATED_ID = u.LOGIN_ID")
                .append(" WHERE u.STATE = :state")
                .append(" AND b.CREATED_ID = :createdId")
                .append(" ORDER BY ").append(orderBy(sort))
                .append(" LIMIT :pageSize OFFSET :offset")
                .toString();
    }

    public static final String countByCreatedIdAndState() {
        return  new StringBuilder()
                .append("SELECT COUNT(*)")
                .append(" FROM BOARD b")
                .append(" JOIN USER u on b.CREATED_ID = u.LOGIN_ID")
                .append(" WHERE u.STATE = :state")
                .append(" AND b.CREATED_ID = :createdId")
                .toString();
    }

    public static final String orderBy(Sort sort) {
        return sort.stream()
                .map(order -> order.getProperty() + " " + order.getDirection().name())
                .collect(joining(", "));
    }
}
