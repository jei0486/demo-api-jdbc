package com.demo.api.domain.user;

import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private JdbcAggregateOperations jdbcAggregateOperations;

    public UserRepositoryImpl(JdbcAggregateOperations jdbcAggregateOperations) {
        this.jdbcAggregateOperations = jdbcAggregateOperations;
    }

    @Transactional
    public void deleteById(String id){
        UserEntity userEntity = this.jdbcAggregateOperations.findById(id, UserEntity.class);
        if (userEntity == null){
            throw new TransientDataAccessResourceException("user not exist.id " + id);
        }

        this.delete(userEntity);
    }

    @Transactional
    public void delete (UserEntity entity){
        entity.delete();
        this.jdbcAggregateOperations.update(entity);
    }

    @Transactional
    public void deleteAll(Iterable<? extends UserEntity> entities){
        entities.forEach(this::delete);
    }
}
