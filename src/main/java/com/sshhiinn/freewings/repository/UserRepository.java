package com.sshhiinn.freewings.repository;

import com.sshhiinn.freewings.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
