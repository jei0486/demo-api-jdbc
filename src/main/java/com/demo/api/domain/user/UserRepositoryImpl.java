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
        User user = this.jdbcAggregateOperations.findById(id,User.class);
        if (user == null){
            throw new TransientDataAccessResourceException("user not exist.id " + id);
        }

        this.delete(user);
    }

    @Transactional
    public void delete (User entity){
        entity.delete();
        this.jdbcAggregateOperations.update(entity);
    }

    @Transactional
    public void deleteAll(Iterable<? extends User> entities){
        entities.forEach(this::delete);
    }
}
