package com.demo.api.domain.user;

import com.demo.api.model.User;
import com.demo.api.util.WithInsert;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<UserEntity, String> ,UserRepositoryCustom ,WithInsert<UserEntity>{

    @Modifying
    @Query("UPDATE USER SET LOGIN_ID = :loginId , NAME = :name, PASSWORD = :password WHERE ID = :id")
    boolean updateUserData(String id, String loginId, String name, String password);

    @Override
    void deleteById(String s);

    @Override
    void delete(UserEntity entity);

    @Override
    void deleteAll(Iterable<? extends UserEntity> entities);

    Optional<UserEntity> findByLoginIdAndStateIn(String loginId, Set<UserState> states);

    default Optional<UserEntity> findByLoginIdExcludeDeleted(String loginId){
        return this.findByLoginIdAndStateIn(loginId, EnumSet.of(UserState.ACTIVE,UserState.LOCKED));
    }
    Optional<UserEntity> findByIdAndStateIn(String id, Set<UserState> states);

    default Optional<UserEntity> findByIdExcludeDeleted(String id){
        return this.findByIdAndStateIn(id, EnumSet.of(UserState.ACTIVE,UserState.LOCKED));
    }
}
