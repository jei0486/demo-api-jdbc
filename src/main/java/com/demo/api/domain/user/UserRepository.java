package com.demo.api.domain.user;

import com.demo.api.util.WithInsert;
import org.springframework.data.repository.CrudRepository;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, String> ,UserRepositoryCustom ,WithInsert<User>{

    @Override
    void deleteById(String s);

    @Override
    void delete(User entity);

    @Override
    void deleteAll(Iterable<? extends User> entities);

    Optional<User> findByIdAndStateIn(String id, Set<UserState> states);

    default Optional<User> findByIdExcludeDeleted(String id){
        return this.findByIdAndStateIn(id, EnumSet.of(UserState.ACTIVE,UserState.LOCKED));
    }
}
