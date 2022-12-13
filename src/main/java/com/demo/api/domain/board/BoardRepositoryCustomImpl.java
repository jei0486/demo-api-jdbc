package com.demo.api.domain.board;

import com.demo.api.domain.board.sql.BoardSql;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.convert.EntityRowMapper;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final NamedParameterJdbcOperations jdbcOperations;
    private final EntityRowMapper<BoardEntity> rowMapper;

    @SuppressWarnings("unchecked")
    public BoardRepositoryCustomImpl(
            NamedParameterJdbcOperations jdbcOperations,
            RelationalMappingContext mappingContext,
            JdbcConverter jdbcConverter) {

        this.jdbcOperations = jdbcOperations;
        this.rowMapper = new EntityRowMapper<>(
                (RelationalPersistentEntity<BoardEntity>) mappingContext.getRequiredPersistentEntity(BoardEntity.class),
                jdbcConverter);
    }

    @Override
    public Page<BoardEntity> findByParam(Pageable pageable) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("offset", pageable.getOffset())
                .addValue("pageSize", pageable.getPageSize());

        List<BoardEntity> board = this.jdbcOperations.query(
                BoardSql.selectByCreatedIdAndState(pageable.getSort()), parameterSource, this.rowMapper);

        return PageableExecutionUtils.getPage(board, pageable, () ->
                this.jdbcOperations.queryForObject(BoardSql.countByCreatedIdAndState(), parameterSource, Long.class));
    }

    @Override
    public Long countByParam(Pageable pageable) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("offset", pageable.getOffset())
                .addValue("pageSize", pageable.getPageSize());

        return this.jdbcOperations.queryForObject(BoardSql.countByCreatedIdAndState(), parameterSource, Long.class);
    }
}
